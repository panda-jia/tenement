loadPage();

function loadRepair() {
    isLogin(managerUrl + '/isLogin', '/login');
    loadRepairForm();
    $.get(repairUrl + '/findAll', {page: global_page, limit: global_limit}, repairs => {
        if (repairs.data.length === 0 && global_page > 1) {
            global_page--;
            loadRepair();
        } else {
            loadRepairList(repairs);
            loadRepairPage(repairs);
        }
    })
}

function loadRepairList(repairs) {
    layui.use(['table', 'layer'], () => {
        let table = layui.table,
            layer = layui.layer;
        table.render({
            elem: '#repair',
            data: repairs.data,
            toolbar: '#repair-toolbar',
            cols: [
                [
                    {type: 'checkbox'},
                    {field: 'repairHouse', title: '报修房号'},
                    {field: 'repairContent', title: '报修内容'},
                    {
                        field: 'repairDate', title: '报修时间', templet: data => {
                            return formatDate(new Date(data.repairDate));
                        }
                    },
                    {field: 'repairOperator', title: '经手物业管理员'},
                    {
                        field: 'repairStatus', title: '物业完成状态', templet: data => {
                            return data.repairStatus === 0 ? '检修中' : '已修复';
                        }
                    },
                    {
                        field: 'repairOwnerStatus', title: '业主确认状态', templet: data => {
                            return data.repairOwnerStatus === 0 ? '未完成' : '已完成';
                        }
                    },
                    {field: 'repairComment', title: '备注'},
                    {title: '操作', toolbar: '#repair-tool'}
                ]
            ]
        });
        table.on('toolbar(repair)', elem => {
            if (elem.event === 'add') {
                $('#add-repair input[name=repairOperator]').val($('.layui-nav span').text());
                $('#add-repair input[name=repairOperatorNo]').val($('.layui-nav a span').attr('title'));
                showLayout($('#add-repair'));
            }
        });
        table.on('tool(repair)', elem => {
            let event = elem.event,
                data = elem.data;
            switch (event) {
                case 'finish':
                    if (data.repairStatus === 0) {
                        $.post(repairUrl + '/updateRepair', {repairId: data.repairId, repairStatus: 1}, result => {
                            result = result > 0 ? '成功' : '失败';
                            layer.msg('报修信息修改' + result + '!');
                            loadRepair();
                        });
                    }
                    break;
                case 'clear':
                    if (data.repairStatus === 0) {
                        layer.msg('物业未解决报修问题，业主不能确认!');
                    } else {
                        $.post(repairUrl + '/updateRepair', {repairId: data.repairId, repairOwnerStatus: 1}, result => {
                            result = result > 0 ? '业主已确认' : '业主未确认';
                            layer.msg(result);
                            loadRepair();
                        });
                    }
                    break;
            }
        });
    })
}

function loadRepairPage(repairs) {
    layui.use('laypage', () => {
        layui.laypage.render({
            elem: 'repair-page',
            curr: global_page,
            count: repairs.count,
            limit: global_limit,
            limits: [10, 20, 30, 40, 50],
            layout: ['count', 'prev', 'page', 'next', 'limit'],
            jump: function (obj, first) {
                if (!first) {
                    global_page = obj.curr;
                    global_limit = obj.limit;
                    loadRepair();
                }
            }
        })
    })
}

function loadRepairForm() {
    layui.use(['element', 'form', 'layer'], () => {
        let form = layui.form,
            layer = layui.layer;
        form.on('submit(add)', elem => {
            let field = elem.field;
            let houseInfo = field.repairHouse.split('-');
            $.get(houseUrl + '/findAllByNoAndUnitAndBuilding', {
                houseBuilding: houseInfo[0],
                houseUnit: houseInfo[1],
                houseNo: houseInfo[2]
            }, house => {
                if (house === '') {
                    layer.msg('无此房产信息，请查证!');
                } else {
                    $.post(repairUrl + '/addRepair', field, result => {
                        result = result > 0 ? '成功' : '失败';
                        layer.msg('报修信息添加' + result + '!');
                        hideLayout($('#add-repair'));
                        loadRepair();
                    });
                }
            });
        });
    })
}