function loadChange() {
    isLogin(adminUrl + '/isLogin', adminUrl);
    layui.use(['element', 'form', 'layer'], () => {
        let form = layui.form,
            layer = layui.layer;
        form.on('submit(change)', elem => {
            let field = elem.field,
                adminId = $('.layui-nav span').attr('title');
            $.get(adminUrl + '/findByCondition', {adminId: adminId, password: field.oldPwd}, manager => {
                if (manager === '') {
                    layer.msg('原密码不正确，请重新输入!');
                } else {
                    if (field.newPwd === field.verifyPwd && field.newPwd.length >= 8) {
                        $.post(adminUrl + '/updateAdmin', {
                            adminId: adminId,
                            password: field.newPwd
                        }, result => {
                            if (result > 0) {
                                layer.msg('密码修改成功，请重新登录!');
                                setTimeout(function () {
                                    urlArray.adminLogout()
                                }, 1000)
                            } else {
                                layer.msg('密码修改失败，请重试或联系管理员!');
                            }
                        })
                    } else {
                        layer.msg('两次输入密码不一致且小于8位，请重新输入!');
                    }
                }
            })
        })
    })
}