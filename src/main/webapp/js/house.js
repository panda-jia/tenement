loadPage();

function loadHouse() {
    isLogin(managerUrl + '/isLogin', '/login');
    loadHouseForm();
    $.get(houseUrl + '/findAll', {page: global_page, limit: global_limit}, houses => {
        if (houses.data.length === 0 && global_page > 1) {
            global_page--;
            loadHouse();
        } else {
            loadHouseList(houses);
            loadHousePage(houses);
        }
    })
}

function loadHouseList(houses) {
    layui.use(['table', 'layer'], () => {
        let table = layui.table,
            layer = layui.layer;
        table.render({
            elem: '#house',
            data: houses.data,
            toolbar: '#house-toolbar',
            cols: [
                [
                    {type: 'checkbox'},
                    {field: 'houseNo', title: '房号'},
                    {field: 'houseUnit', title: '单元号'},
                    {field: 'houseBuilding', title: '楼栋号'},
                    {field: 'houseType', title: '房屋类型'},
                    {field: 'houseArea', title: '房屋面积'},
                    {
                        field: 'houseOwner', title: '业主', templet: data => {
                            return data.houseOwner === '-1' ? '' : (data.houseOwner === null ? '' : data.houseOwner);
                        }
                    },
                    {
                        field: 'houseDate', title: '生效时间', templet: data => {
                            return data.houseDate === '-1' ? '' : (data.houseDate === null ? '' : data.houseDate);
                        }
                    },
                    {title: '操作', toolbar: '#house-tool'}
                ]
            ]
        });
        table.on('toolbar(house)', elem => {
            let event = elem.event;
            switch (event) {
                case 'add':
                    $.get(buildingUrl + '/findAllByNoPage', buildings => {
                        $.each(buildings, (i, v) => {
                            let option = '<option value="' + v.buildName + '">' + v.buildName + '栋</option>';
                            $('#add-house select[name=houseBuilding]').append(option);
                        });
                        resetForm();
                    });
                    showLayout($('#add-house'));
                    break;
                case 'search':
                    break;
            }
        });
        table.on('tool(house)', elem => {
            let event = elem.event,
                data = elem.data;
            switch (event) {
                case 'del':
                    if (data.houseOwner === null) {
                        $.post(houseUrl + '/delHouse/' + data.houseId, result => {
                            result = result > 0 ? '成功' : '失败';
                            layer.msg('房产信息删除' + result + '!');
                            loadHouse();
                        });
                    } else {
                        layer.msg('该房产已绑定业主，无法删除!');
                    }
                    break;
                case 'bind':
                    if (data.houseOwner === null || data.houseOwner === '-1') {
                        $('#binding-owner input[name=houseId]').val(data.houseId);
                        showLayout($('#binding-owner'));
                    } else {
                        layer.msg('已绑定业主，请先解绑!');
                    }
                    break;
                case 'unbind':
                    if (data.houseOwner === null || data.houseOwner === '-1') {
                        layer.msg('该房产还未绑定任何业主，请先绑定!');
                    } else {
                        layer.confirm('是否解除业主绑定?', {
                            btn: ['解除绑定', '不接触']
                        }, () => {
                            data['houseDate'] = '-1';
                            data['houseOwner'] = '-1';
                            data['houseOwnerNo'] = '-1';
                            $.post(houseUrl + '/updateHouse', data, result => {
                                result = result > 0 ? '成功' : '失败';
                                layer.msg('房产解绑' + result + '!');
                                loadHouse();
                            });
                        });
                    }
                    break;
            }
        });
    })
}

function loadHousePage(houses) {
    layui.use('laypage', () => {
        layui.laypage.render({
            elem: 'house-page',
            curr: global_page,
            count: houses.count,
            limit: global_limit,
            limits: [10, 20, 30, 40, 50],
            layout: ['count', 'prev', 'page', 'next', 'limit'],
            jump: function (obj, first) {
                if (!first) {
                    global_page = obj.curr;
                    global_limit = obj.limit;
                    loadHouse();
                }
            }
        })
    })
}

function loadHouseForm() {
    layui.use(['element', 'form', 'laydate', 'layer'], () => {
        let form = layui.form,
            layDate = layui.laydate,
            layer = layui.layer;
        form.on('submit(add)', elem => {
            let field = elem.field;
            $.get(houseUrl + '/findAllByNoAndUnitAndBuilding', {
                houseBuilding: field.houseBuilding,
                houseUnit: field.houseUnit,
                houseNo: field.houseNo
            }, house => {
                if (house === '') {
                    if (!regex.isNumber(field.houseUnit)) {
                        layer.msg('单元号不能包含汉字或特殊字符!');
                    } else if (!regex.isNumber(field.houseNo)) {
                        layer.msg('房号不能包含汉字或特殊字符!');
                    } else if (!regex.decimal(field.houseArea)) {
                        layer.msg('面积必须为整数或小数，请重新输入!');
                    } else {
                        $.post(houseUrl + '/addHouse', field, result => {
                            result = result > 0 ? '成功' : '失败';
                            layer.msg('房产信息录入' + result + '!');
                            hideLayout($('#add-house'));
                            loadHouse();
                        });
                    }
                } else {
                    layer.msg('该房产信息已存在，请勿重复录入!');
                }
            });
        });
        form.on('submit(bind)', elem => {
            let field = elem.field;
            $.get(ownerUrl + '/findByOwnerNo/' + field.houseOwnerNo, owner => {
                if (owner === '') {
                    layer.msg('业主不存在或身份证号输入错误，请重新输入!');
                } else {
                    $.post(houseUrl + '/updateHouse', field, result => {
                        result = result > 0 ? '成功' : '失败';
                        layer.msg('绑定' + result + '!');
                        hideLayout($('#binding-owner'));
                        loadHouse();
                    })
                }
            })
        });
        layDate.render({
            elem: '.houseDate'
        })
    })
}

function resetForm() {
    layui.use('form', () => {
        layui.form.render();
    })
}