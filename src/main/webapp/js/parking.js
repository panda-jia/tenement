loadPage();

function loadParking(parkOwner) {
    isLogin(managerUrl + '/isLogin', '/login');
    loadParkingForm();
    parkOwner = parkOwner === undefined ? '' : parkOwner;
    $.get(parkingUrl + '/findAll', {page: global_page, limit: global_limit, parkOwner: parkOwner}, parks => {
        if (parks.data.length === 0 && global_page > 1) {
            global_page--;
            loadParkingForm();
        } else {
            loadParkingList(parks);
            loadParkingPage(parks);
        }
    })
}

function loadParkingList(parks) {
    layui.use(['table', 'layer'], () => {
        let table = layui.table,
            layer = layui.layer;
        table.render({
            elem: '#parking',
            data: parks.data,
            toolbar: '#parking-toolbar',
            cols: [
                [
                    {type: 'checkbox'},
                    {field: 'parkNo', title: '车位号'},
                    {field: 'parkCarNo', title: '车牌照'},
                    {field: 'parkOwner', title: '业主'},
                    {field: 'parkOperator', title: '操作员'},
                    {field: 'parkComment', title: '备注'},
                    {title: '操作', toolbar: '#parking-tool'}
                ]
            ]
        });
        table.on('toolbar(parking)', elem => {
            switch (elem.event) {
                case 'add':
                    $('#add-parking input[name=parkOperator]').val($('.layui-nav span').attr('title'));
                    showLayout($('#add-parking'));
                    break;
                case 'search':
                    loadParking($('.layout-container input[name=parkOwner]').val());
                    break;
            }
        });
        table.on('tool(parking)', elem => {
            let event = elem.event,
                data = elem.data;
            if (event === 'del') {
                layer.confirm('删除将不可恢复，是否删除?', {
                    btn: ['删除', '不删除']
                }, () => {
                    $.post(parkingUrl + '/delParking/' + data.parkId, result => {
                        result = result > 0 ? '成功' : '失败';
                        layer.msg('车位信息删除' + result + '!');
                        loadParking();
                    });
                });
            }
        });
    })
}

function loadParkingPage(parks) {
    layui.use('laypage', () => {
        layui.laypage.render({
            elem: 'parking-page',
            curr: global_page,
            count: parks.count,
            limit: global_limit,
            limits: [10, 20, 30, 40, 50],
            layout: ['count', 'prev', 'page', 'next', 'limit'],
            jump: function (obj, first) {
                if (!first) {
                    global_page = obj.curr;
                    global_limit = obj.limit;
                    loadParking();
                }
            }
        })
    })
}

function loadParkingForm() {
    layui.use(['element', 'form', 'layer'], () => {
        let form = layui.form,
            layer = layui.layer;
        form.on('submit(add)', elem => {
            let field = elem.field;
            if (!regex.isNumber(field.parkNo)) {
                layer.msg('车位号格式不正确，必须为整数!');
            }else if (!regex.isCarNumber(field.parkCarNo)) {
                layer.msg('牌照格式有误，请重新输入!');
            } else {
                $.get(parkingUrl + '/findByCondition', {parkNo: field.parkNo}, parking => {
                    if (parking === '') {
                        $.get(parkingUrl + '/findByCondition', {parkCarNo: field.parkCarNo}, park => {
                            if (park === '') {
                                $.post(parkingUrl + '/addParking', field, result => {
                                    result = result > 0 ? '成功' : '失败';
                                    layer.msg('车位注册' + result + '!');
                                    hideLayout($('#add-parking'));
                                    loadParking();
                                })
                            } else {
                                layer.msg('该车牌已注册，请勿重复注册!');
                            }
                        })
                    } else {
                        layer.msg('该车位已注册，请勿重复注册!');
                    }
                })
            }
        });
    })
}