loadPage();

function loadCost(costOwner) {
    isLogin(managerUrl + '/isLogin', '/login');
    loadCostForm();
    costOwner = costOwner === undefined ? '' : costOwner;
    $.get(costUrl + '/findAll', {page: global_page, limit: global_limit, costOwner: costOwner}, costs => {
        if (costs.data.length === 0 && global_page > 1) {
            global_page--;
            loadCost();
        } else {
            loadCostList(costs);
            loadCostPage(costs);
        }
    })
}

function loadCostList(costs) {
    layui.use(['table', 'layer'], () => {
        let table = layui.table,
            layer = layui.layer;
        table.render({
            elem: '#cost',
            data: costs.data,
            toolbar: '#cost-toolbar',
            cols: [
                [
                    {type: 'checkbox'},
                    {
                        field: 'costType', title: '费用类型', templet: data => {
                            switch (data.costType) {
                                case '0':
                                    return '水费';
                                case '1':
                                    return '电费';
                                case '2':
                                    return '煤气费';
                                case '3':
                                    return '燃气费';
                                case '4':
                                    return '网费';
                            }
                        }
                    },
                    {field: 'costStart', title: '计费开始时间'},
                    {field: 'costEnd', title: '计费结束时间'},
                    {field: 'costSum', title: '总计/元'},
                    {field: 'costOwner', title: '缴费人'},
                    {field: 'costOperator', title: '经手人'},
                    {
                        field: 'costStatus', title: '缴费状态', templet: data => {
                            return data.costStatus === 0 ? '未缴费' : '已缴费';
                        }
                    },
                    {title: '操作', toolbar: '#cost-tool'}
                ]
            ]
        });
        table.on('toolbar(cost)', elem => {
            let event = elem.event;
            switch (event) {
                case 'add':
                    showLayout($('#add-cost'));
                    break;
                case 'search':
                    loadCost($('input[name=costOwner]').val());
                    break;
            }
        });
        table.on('tool(cost)', elem => {
            let event = elem.event,
                data = elem.data;
            switch (event) {
                case 'finish':
                    if (data.costStatus === 1) {
                        layer.msg('已缴费，请勿重复操作!');
                    } else {
                        layer.confirm('是否确定已缴费?', {
                            btn: ['确定', '不确定']
                        }, () => {
                            $.post(costUrl + '/updateCost', {costId: data.costId, costStatus: 1}, result => {
                                result = result > 0 ? '成功' : '失败';
                                layer.msg('缴费' + result + '!');
                                loadCost();
                            });
                        });
                    }
                    break;
                case 'del':
                    layer.confirm('删除将不可恢复，是否删除?', {
                        btn: ['删除', '不删除']
                    }, () => {
                        $.post(costUrl + '/delCost/' + data.costId, result => {
                            result = result > 0 ? '成功' : '失败';
                            layer.msg('缴费记录删除' + result + '!');
                            loadCost();
                        });
                    });
                    break;
            }
        });
    })
}

function loadCostPage(costs) {
    layui.use('laypage', () => {
        layui.laypage.render({
            elem: 'cost-page',
            curr: global_page,
            count: costs.count,
            limit: global_limit,
            limits: [10, 20, 30, 40, 50],
            layout: ['count', 'prev', 'page', 'next', 'limit'],
            jump: function (obj, first) {
                if (!first) {
                    global_page = obj.curr;
                    global_limit = obj.limit;
                    loadCost();
                }
            }
        });
    })
}

function loadCostForm() {
    layui.use(['element', 'form', 'laydate', 'layer'], () => {
        let form = layui.form,
            date = layui.laydate,
            layer = layui.layer;
        form.on('submit(add)', elem => {
            let field = elem.field;
            if (!regex.decimal(field.costSum)) {
                layer.msg('费用只能为整数或小数!');
            } else {
                $.get(ownerUrl + '/findByOwnerNo/' + field.costOwnerNo, owner => {
                    if (owner === '') {
                        layer.msg('该业主信息不存在，请查证!');
                    } else {
                        $.get(costUrl + '/findByTypeAndOwnerNo', {
                            costType: field.costType,
                            costOwnerNo: field.costOwnerNo
                        }, cost => {
                            if (cost === '') {
                                field['costOperatorNo'] = $('.layui-nav .layui-nav-item a span').attr('title');
                                field['costOperator'] = $('.layui-nav .layui-nav-item span').text();
                                $.post(costUrl + '/addCost', field, result => {
                                    result = result > 0 ? '成功' : '失败';
                                    layer.msg('缴费信息添加' + result + '!');
                                    hideLayout($('#add-cost'));
                                    loadCost();
                                });
                            } else {
                                layer.msg('此缴费信息已存在，请勿重复添加!');
                            }
                        });
                    }
                })
            }
        });
        date.render({
            elem: '.costStart'
        });
        date.render({
            elem: '.costEnd'
        });
    })
}