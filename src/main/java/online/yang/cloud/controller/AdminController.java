package online.yang.cloud.controller;

import online.yang.cloud.model.Admin;
import online.yang.cloud.service.AdminService;
import online.yang.cloud.utils.GlobalConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 孙嘉
 * created in 2020/2/5 17:49
 */

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private AdminService adminServiceImpl;

    public AdminController(AdminService adminServiceImpl) {
        this.adminServiceImpl = adminServiceImpl;
    }

    @PostMapping(value = "/login")
    public int login(Admin admin, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Admin loginAdmin = adminServiceImpl.findByAccountAndPwd(admin);
        if (loginAdmin != null) {
            session.setAttribute("admin", loginAdmin);
            return GlobalConstant.SUCCESS;
        }
        return GlobalConstant.ERROR;
    }

    @GetMapping(value = "/isLogin")
    public Admin isLogin(HttpServletRequest request) {
        return (Admin) request.getSession().getAttribute("admin");
    }

    @PostMapping(value = "/updateAdmin")
    public int updateAdmin(Admin admin) {
        return adminServiceImpl.update(admin);
    }

    @GetMapping(value = "/findByCondition")
    public Admin findByCondition(Admin admin) {
        return adminServiceImpl.findByCondition(admin);
    }

}
