loadPage();

function loadComplain() {
    loadComplainForm();
    $.get(complainUrl + '/findAll', {page: global_page, limit: global_limit}, complains => {
        if (complains.data.length === 0 && global_page > 1) {
            global_page--;
            loadComplain();
        } else {
            loadComplainList(complains);
            loadComplainPage(complains);
        }
    })
}

function loadComplainList(complains) {
    layui.use(['table', 'layer'], () => {
        let table = layui.table,
            layer = layui.layer;
        table.render({
            elem: '#complain',
            data: complains.data,
            toolbar: '#complain-toolbar',
            cols: [
                [
                    {type: 'checkbox'},
                    {field: 'complainTitle', title: '投诉标题'},
                    {field: 'complainContent', title: '投诉内容'},
                    {
                        field: 'complainDate', title: '投诉时间', templet: data => {
                            return formatDate(new Date(data.complainDate));
                        }
                    },
                    {field: 'complainAdminer', title: '被投诉人'},
                    {field: 'complainStatus', title: '处理状态'},
                    {field: 'complainComment', title: '备注'},
                    {title: '操作', toolbar: '#complain-tool'}
                ]
            ]
        });
        table.on('toolbar(complain)', elem => {
            if (elem.event === 'add') {
                $('#add-complain input[name=complainAdminer]').val($('.layui-nav span').text());
                $('#add-complain input[name=complainNo]').val($('.layui-nav a span').attr('title'));
                showLayout($('#add-complain'));
            }
        });
        table.on('tool(complain)', elem => {
            if (elem.event === 'finish') {
                if (elem.data.complainStatus === '已处理') {
                    layer.msg('投诉已处理，请勿重复操作!');
                } else {
                    layer.confirm('是否已经处理完毕?', {
                        btn: ['已处理', '还没有']
                    }, () => {
                        $.post(complainUrl + '/updateComplain', {
                            complainId: elem.data.complainId,
                            complainStatus: '已处理'
                        }, result => {
                            result = result > 0 ? '成功' : '失败';
                            layer.msg('投诉信息处理' + result + '!');
                            loadComplain();
                        })
                    });
                }
            }
        });
    })
}

function loadComplainPage(complains) {
    layui.use('laypage', () => {
        layui.laypage.render({
            elem: 'complain-page',
            curr: global_page,
            count: complains.count,
            limit: global_limit,
            limits: [10, 20, 30, 40, 50],
            layout: ['count', 'prev', 'page', 'next', 'limit'],
            jump: function (obj, first) {
                if (!first) {
                    global_page = obj.curr;
                    global_limit = obj.limit;
                    loadComplain();
                }
            }
        })
    })
}

function loadComplainForm() {
    layui.use(['element', 'form', 'layer'], () => {
        let form = layui.form,
            layer = layui.layer;
        form.on('submit(add)', elem => {
            $.post(complainUrl + '/addComplain', elem.field, result => {
                result = result > 0 ? '成功' : '失败';
                layer.msg('投诉添加' + result + '!');
                hideLayout($('#add-complain'));
                loadComplain();
            })
        })
    })
}