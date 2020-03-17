function loadManager(empName) {
    isLogin(adminUrl + '/isLogin', adminUrl + '/login');
    loadManagerForm();
    empName = empName === undefined ? '' : empName;
    $.get(managerUrl + '/findAll', {page: global_page, limit: global_limit, empName: empName}, managers => {
        if (managers.data.length === 0 && global_page > 1) {
            global_page--;
            loadManager();
        } else {
            loadManagerList(managers);
            loadManagerPage(managers);
        }
    })
}

function loadManagerList(managers) {
    layui.use(['table', 'layer'], () => {
        let table = layui.table,
            layer = layui.layer;
        table.render({
            elem: '#manager',
            data: managers.data,
            toolbar: '#manager-toolbar',
            cols: [
                [
                    {type: 'checkbox'},
                    {field: 'empName', title: '管理员姓名'},
                    {
                        field: 'empGender', title: '性别', templet: data => {
                            return data.empGender === 0 ? '男' : '女';
                        }
                    },
                    {field: 'empBorth', title: '出生日期'},
                    {field: 'empPhone', title: '联系电话'},
                    {title: '操作', toolbar: '#manager-tool'}
                ]
            ]
        });
        table.on('toolbar(manager)', elem => {
            switch (elem.event) {
                case 'add':
                    showLayout($('#add-manager'));
                    break;
                case 'search':
                    loadManager($('.body-container input[name=empName]').val());
                    break;
            }
        });
        table.on('tool(manager)', elem => {
            let event = elem.event,
                data = elem.data;
            switch (event) {
                case 'edit':
                    $('#edit-manager input[name=empId]').val(data.empId);
                    $('#edit-manager input[name=empName]').val(data.empName);
                    $('#edit-manager select[name=empGender]').find('option')
                        .eq(data.empGender).attr('selected', 'selected');
                    $('#edit-manager input[name=empBorth]').val(data.empBorth);
                    $('#edit-manager input[name=empPhone]').val(data.empPhone);
                    resetForm();
                    showLayout($('#edit-manager'));
                    break;
                case 'del':
                    layer.confirm('删除将不可恢复，是否删除?', {
                        btn: ['删除', '不删除']
                    }, () => {
                        $.post(managerUrl + '/delManager/' + data.empId, result => {
                            result = result > 0 ? '成功' : '失败';
                            layer.msg('管理员信息删除' + result + '!');
                            loadManager();
                        });
                    });
                    break;
            }
        })
    })
}

function loadManagerPage(managers) {
    layui.use('laypage', () => {
        layui.laypage.render({
            elem: 'manager-page',
            curr: global_page,
            count: managers.count,
            limit: global_limit,
            limits: [10, 20, 30, 40, 50],
            layout: ['count', 'prev', 'page', 'next', 'limit'],
            jump: function (obj, first) {
                if (!first) {
                    global_page = obj.curr;
                    global_limit = obj.limit;
                    loadManager();
                }
            }
        })
    })
}

function loadManagerForm() {
    layui.use(['element', 'form', 'laydate', 'layer'], () => {
        let form = layui.form,
            date = layui.laydate,
            layer = layui.layer;
        form.on('submit(add)', elem => {
            let field = elem.field;
            $.get(managerUrl + '/findByManagerName', {empName: field.empName}, manager => {
                if (manager === '') {
                    if (!regex.phone(field.empPhone)) {
                        layer.msg('电话格式有误，请查证后重新输入!');
                    } else {
                        $.post(managerUrl + '/addManager', field, result => {
                            result = result > 0 ? '成功' : '失败';
                            layer.msg('物业管理员信息添加' + result + '，默认密码为123456!');
                            hideLayout($('#add-manager'));
                            loadManager();
                        });
                    }
                } else {
                    layer.msg('该管理员信息已存在，请勿重复添加!');
                }
            });
        });
        form.on('submit(edit)', elem => {
            let field = elem.field;
            if (!regex.phone(field.empPhone)) {
                layer.msg('电话格式有误，请查证后重新输入!');
            } else {
                $.post(managerUrl + '/updateManager', field, result => {
                    result = result > 0 ? '成功' : '失败';
                    layer.msg('物业管理员信息修改' + result + '!');
                    hideLayout($('#edit-manager'));
                    loadManager();
                })
            }
        });
        date.render({
            elem: 'input[name=empBorth]',
            max: 0
        });
    })
}

function resetForm() {
    layui.use('form', () => {
        layui.form.render();
    })
}