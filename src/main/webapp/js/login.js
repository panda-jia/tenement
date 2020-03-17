function loadLoginForm() {
    layui.use(['element', 'form', 'layer'], () => {
        let form = layui.form,
            layer = layui.layer;
        form.on('submit(login)', elem => {
            $.post(managerUrl + '/login', elem.field, result => {
                if (result > 0) {
                    layer.msg('登录成功，正在进行跳转!');
                    setTimeout(function () {
                        // urlArray.index();
                        urlArray.owner()
                    }, 1000);
                } else {
                    layer.msg('用户名或密码错误!');
                }
            })
        })
    })
}

loadLoginForm();