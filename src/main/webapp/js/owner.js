loadPage();

function loadOwner(ownerNo, ownerName) {
    isLogin(managerUrl + '/isLogin', '/login');
    loadOwnerForm();
    ownerNo = ownerNo === undefined ? '' : ownerNo;
    ownerName = ownerName === undefined ? '' : ownerName;
    $.get(ownerUrl + '/findAllByPage', {
        page: global_page,
        limit: global_limit,
        ownerNo: ownerNo,
        ownerName: ownerName
    }, owners => {
        if (owners.data.length === 0 && global_page > 1) {
            global_page--;
            loadOwner();
        } else {
            loadOwnerList(owners);
            loadOwnerPage(owners);
        }
    });
}

function loadOwnerList(owners) {
    layui.use(['table', 'layer'], () => {
        let table = layui.table,
            layer = layui.layer;
        table.render({
            elem: '#owner',
            data: owners.data,
            toolbar: '#owner-toolbar',
            cols: [
                [
                    {type: 'checkbox'},
                    {field: 'ownerNo', title: '业主身份证号'},
                    {field: 'ownerName', title: '姓名'},
                    {
                        field: 'ownerGender', title: '性别', templet: data => {
                            return data.ownerGender === 0 ? '男' : '女';
                        }
                    },
                    {field: 'ownerBorth', title: '生日'},
                    {field: 'ownerWorkstation', title: '联系地址'},
                    {field: 'ownerPhone', title: '联系电话'},
                    {title: '操作', toolbar: '#owner-tool'}
                ]
            ]
        });
        table.on('toolbar(owner)', elem => {
            let event = elem.event;
            switch (event) {
                case 'add':
                    showLayout($('#add-owner'));
                    break;
                case 'search':
                    break;
            }
        });
        table.on('tool(owner)', elem => {
            let event = elem.event,
                data = elem.data;
            switch (event) {
                case 'edit':
                    $('#edit-owner .layout-title span').text(data.ownerName);
                    $('#edit-owner input[name=ownerId]').val(data.ownerId);
                    $('#edit-owner input[name=ownerNo]').val(data.ownerNo);
                    $('#edit-owner input[name=ownerName]').val(data.ownerName);
                    $('#edit-owner input[name=ownerGender]').eq(data.ownerGender).attr('checked', true);
                    $('#edit-owner input[name=ownerBorth]').val(data.ownerBorth);
                    $('#edit-owner input[name=ownerWorkstation]').val(data.ownerWorkstation);
                    $('#edit-owner input[name=ownerPhone]').val(data.ownerPhone);
                    resetForm();
                    showLayout($('#edit-owner'));
                    break;
                case 'del':
                    layer.confirm('删除将不可恢复，是否确认?', {
                        btn: ['确认删除', '取消']
                    }, () => {
                        $.post(ownerUrl + '/delOwner/' + data.ownerId, result => {
                            result = result > 0 ? '成功' : '失败';
                            layer.msg('业主信息删除' + result + '!');
                            loadOwner();
                        });
                    });
                    break;
            }
        });
    })
}

function loadOwnerPage(owners) {
    layui.use('laypage', () => {
        layui.laypage.render({
            elem: 'owner-page',
            curr: global_page,
            count: owners.count,
            limit: global_limit,
            limits: [10, 20, 30, 40, 50, 100],
            layout: ['count', 'prev', 'page', 'next', 'limit'],
            jump: function (obj, first) {
                if (!first) {
                    global_page = obj.curr;
                    global_limit = obj.limit;
                    loadOwner();
                }
            }
        })
    })
}

function loadOwnerForm() {
    layui.use(['element', 'form', 'layer', 'laydate'], () => {
        let form = layui.form,
            layer = layui.layer,
            date = layui.laydate;
        form.on('submit(add)', elem => {
            let field = elem.field;
            $.get(ownerUrl + '/findByOwnerNo/' + field.ownerNo, owner => {
                if (owner === '') {
                    if (!regex.cardNo(field.ownerNo)) {
                        layer.msg('身份证号格式有误，请重新输入!');
                    } else if (!regex.phone(field.ownerPhone)) {
                        layer.msg('联系电话格式有误，请重新输入!');
                    } else {
                        $.post(ownerUrl + '/addOwner', field, result => {
                            result = result > 0 ? '成功' : '失败';
                            layer.msg('业主信息添加' + result + '!');
                            hideLayout($('#add-owner'));
                            loadOwner();
                        })
                    }
                } else {
                    layer.msg('该业主信息已存在，请勿重复添加!');
                }
            });
        });
        form.on('submit(edit)', elem => {
            let field = elem.field;
            if (!regex.cardNo(field.ownerNo)) {
                layer.msg('身份证号格式有误，请重新输入!');
            } else if (!regex.phone(field.ownerPhone)) {
                layer.msg('联系电话格式有误，请重新输入!');
            } else {
                $.post(ownerUrl + '/updateOwner', field, result => {
                    result = result > 0 ? '成功' : '失败';
                    layer.msg('业主信息修改' + result + '!');
                    hideLayout($('#edit-owner'));
                    loadOwner();
                })
            }
        });
        date.render({
            elem: '.ownerBorth',
            max: 0
        });
    });
}

$('#add-owner input[name=ownerNo]').blur(function () {
    let cardNo = $(this).val();
    let year = cardNo.substring(6, 10);
    let month = cardNo.substring(10, 12);
    let day = cardNo.substring(12, 14);
    let date = year + '/' + month + '/' + day;
    $('#add-owner input[name=ownerBorth]').val(date);
});

function resetForm() {
    layui.use('form', () => {
        layui.form.render();
    })
}