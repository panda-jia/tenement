loadPage();

function loadBuilding(buildName) {
    isLogin(managerUrl + '/isLogin', '/login');
    loadBuildingForm();
    buildName = buildName === undefined ? '' : buildName;
    $.get(buildingUrl + '/findAll', {page: global_page, limit: global_limit, buildName: buildName}, buildings => {
        if (buildings.data.length === 0 && global_page > 1) {
            global_page--;
            loadBuilding();
        } else {
            loadBuildingList(buildings);
            loadBuildingPage(buildings);
        }
    })
}

function loadBuildingList(buildings) {
    layui.use(['table', 'layer'], () => {
        let table = layui.table,
            layer = layui.layer;
        table.render({
            elem: '#building',
            data: buildings.data,
            toolbar: '#building-toolbar',
            cols: [
                [
                    {type: 'checkbox'},
                    {
                        field: 'buildName', title: '楼宇号', templet: data => {
                            return data.buildN
                        }
                    },
                    {
                        field: 'buildLayerCount', title: '楼层数', templet: data => {
                            return data.buildLayerCount + '层';
                        }
                    },
                    {field: 'buildRoomCount', title: '楼层房间数'},
                    {field: 'buildDate', title: '建成时间'},
                    {title: '操作', toolbar: '#building-tool'}
                ]
            ]
        });
        table.on('toolbar(building)', elem => {
            let event = elem.event;
            switch (event) {
                case 'add':
                    showLayout($('#add-building'));
                    break;
                case 'search':
                    loadBuilding($('.layui-body input[name=buildName]').val());
                    break;
            }
        });
        table.on('tool(building)', elem => {
            let event = elem.event,
                data = elem.data;
            switch (event) {
                case 'edit':
                    $('#edit-building .layout-title span').text(data.buildName);
                    $('#edit-building input[name=buildId]').val(data.buildId);
                    $('#edit-building input[name=buildName]').val(data.buildName);
                    $('#edit-building input[name=buildLayerCount]').val(data.buildLayerCount);
                    $('#edit-building input[name=buildRoomCount]').val(data.buildRoomCount);
                    $('#edit-building input[name=buildDate]').val(data.buildDate);
                    showLayout($('#edit-building'));
                    break;
                case 'del':
                    layer.confirm('删除将不可恢复，是否删除?', {
                        btn: ['确认删除', '取消']
                    }, () => {
                        $.get(houseUrl + '/findByBuilding/' + data.buildName, result => {
                            if (result > 0) {
                                layer.msg('房屋信息未删除，无法删除此楼栋信息!');
                            } else {
                                $.post(buildingUrl + '/delBuilding/' + data.buildId, result => {
                                    result = result > 0 ? '成功' : '失败';
                                    layer.msg('楼宇信息删除' + result + '!');
                                    loadBuilding();
                                });
                            }
                        });
                    });
                    break;
            }
        });
    })
}

function loadBuildingPage(buildings) {
    layui.use('laypage', () => {
        layui.laypage.render({
            elem: 'building-page',
            curr: global_page,
            count: buildings.count,
            limit: global_limit,
            limits: [10, 20, 30, 40, 50],
            layout: ['count', 'prev', 'page', 'next', 'limit'],
            jump: function (obj, first) {
                if (!first) {
                    global_page = obj.curr;
                    global_limit = obj.limit;
                    loadBuilding();
                }
            }
        });
    })
}

function loadBuildingForm() {
    layui.use(['element', 'form', 'laydate', 'layer'], () => {
        let form = layui.form,
            laydate = layui.laydate,
            layer = layui.layer;
        form.on('submit(add)', elem => {
            let field = elem.field;
            $.get(buildingUrl + '/findByBuildName/' + field.buildName, building => {
                if (building === '') {
                    if (!regex.isNumber(field.buildLayerCount)) {
                        layer.msg('楼层数必须为整数!');
                    } else if (!regex.isNumber(field.buildRoomCount)) {
                        layer.msg('楼层房间数必须为整数!');
                    } else {
                        $.post(buildingUrl + '/addBuilding', field, result => {
                            result = result > 0 ? '成功' : '失败';
                            layer.msg('楼栋信息添加' + result + '!');
                            hideLayout($('#add-building'));
                            loadBuilding();
                        });
                    }
                } else {
                    layer.msg('该楼栋信息已存在，请勿重复添加!');
                }
            })
        });
        form.on('submit(edit)', elem => {
            let field = elem.field;
            if (!regex.isNumber(field.buildLayerCount)) {
                layer.msg('楼层数必须为整数!');
            } else if (!regex.isNumber(field.buildRoomCount)) {
                layer.msg('楼层房间数必须为整数!');
            } else {
                $.post(buildingUrl + '/updateBuilding', field, result => {
                    result = result > 0 ? '成功' : '失败';
                    layer.msg('楼栋信息修改' + result + '!');
                    hideLayout($('#edit-building'));
                    loadBuilding();
                });
            }
        });
        laydate.render({
            elem: '.buildDate'
        });
    })
}