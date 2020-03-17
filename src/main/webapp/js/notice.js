loadPage();

function loadNotice() {
    isLogin(managerUrl + '/isLogin', '/login');
    loadNoticeForm();
    $.get(noticeUrl + '/findAll', {page: global_page, limit: global_limit}, notices => {
        if (notices.data.length === 0 && global_page > 1) {
            global_page--;
            loadNotice();
        } else {
            loadNoticeList(notices);
            loadNoticePage(notices);
        }
    })
}

function loadNoticeList(notices) {
    layui.use(['table', 'layer'], () => {
        let table = layui.table,
            layer = layui.layer;
        table.render({
            elem: '#notice',
            data: notices.data,
            toolbar: '#notice-toolbar',
            cols: [
                [
                    {type: 'checkbox'},
                    {field: 'noticeId', title: '公告编号'},
                    {field: 'noticeContent', title: '公告内容'},
                    {
                        field: 'noticeDate', title: '公告发布日期', templet: data => {
                            return formatDate(new Date(data.noticeDate));
                        }
                    },
                    {field: 'noticeCreater', title: '公告发布人'},
                    {title: '操作', toolbar: '#notice-tool'}
                ]
            ]
        });
        table.on('toolbar(notice)', elem => {
            switch (elem.event) {
                case 'add':
                    $('#add-notice input[name=noticeCreaterNo]').val($('.layui-nav span').attr('title'));
                    showLayout($('#add-notice'));
                    break;
            }
        });
        table.on('tool(notice)', elem => {
            let event = elem.event,
                data = elem.data;
            switch (event) {
                case 'edit':
                    $('#edit-notice input[name=noticeId]').val(data.noticeId);
                    $("#edit-notice textarea[name=noticeContent]").val(data.noticeContent);
                    showLayout($('#edit-notice'));
                    break;
                case 'del':
                    layer.confirm('删除将不可恢复，是否删除?', {
                        btn: ['删除', '不删除']
                    }, () => {
                        $.post(noticeUrl + '/delNotice/' + data.noticeId, result => {
                            result = result > 0 ? '成功' : '失败';
                            layer.msg('公告删除' + result + '!');
                            loadNotice();
                        })
                    });
                    break;
            }
        });
    })
}

function loadNoticePage(notices) {
    layui.use('laypage', () => {
        layui.laypage.render({
            elem: 'notice-page',
            curr: global_page,
            count: notices.count,
            limit: global_limit,
            limits: [10, 20, 30, 40, 50],
            layout: ['count', 'prev', 'page', 'next', 'limit'],
            jump: function (obj, first) {
                if (!first) {
                    global_page = obj.curr;
                    global_limit = obj.limit;
                    loadNotice();
                }
            }
        })
    })
}

function loadNoticeForm() {
    layui.use(['element', 'form', 'layer'], () => {
        let form = layui.form,
            layer = layui.layer;
        form.on('submit(add)', elem => {
            if (elem.field.noticeContent.trim() === '') {
                layer.msg('公告内容不能为空!');
            } else {
                $.post(noticeUrl + '/addNotice', elem.field, result => {
                    result = result > 0 ? '成功' : '失败';
                    layer.msg('公告内容发布' + result + '!');
                    hideLayout($('#add-notice'));
                    loadNotice();
                })
            }
        });
        form.on('submit(edit)', elem => {
            if (elem.field.noticeContent.trim() === '') {
                layer.msg('公告内容不能为空!');
            } else {
                $.post(noticeUrl + '/updateNotice', elem.field, result => {
                    result = result > 0 ? '成功' : '失败';
                    layer.msg('公告内容修改' + result + '!');
                    hideLayout($('#edit-notice'));
                    loadNotice();
                })
            }
        });
    })
}