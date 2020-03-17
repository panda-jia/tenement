package online.yang.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author .
 * created in 2020/1/31 12:22
 */

@Controller
public class UrlController {

    /**
     * 登录界面
     */
    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

    /**
     * 首页
     */
    @GetMapping(value = "/home")
    public String toIndex() {
        return "home";
    }

    /**
     * 业主管理页面
     */
    @GetMapping(value = "/owner")
    public String owner() {
        return "owner";
    }

    /**
     * 楼宇管理页面
     */
    @GetMapping(value = "/building")
    public String building() {
        return "building";
    }

    /**
     * 房产管理页面
     */
    @GetMapping(value = "/house")
    public String house() {
        return "house";
    }

    /**
     * 费用管理页面
     */
    @GetMapping(value = "/cost")
    public String cost() {
        return "cost";
    }

    /**
     * 公告管理页面
     */
    @GetMapping(value = "/notice")
    public String notice() {
        return "notice";
    }

    /**
     * 保修管理页面
     */
    @GetMapping(value = "/repair")
    public String repair() {
        return "repair";
    }

    /**
     * 投诉管理页面
     */
    @GetMapping(value = "/complain")
    public String complain() {
        return "complain";
    }

    /**
     * 车位管理页面
     */
    @GetMapping(value = "/parking")
    public String parking() {
        return "parking";
    }

    /**
     * 物业管理员退出登录
     */
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("emp");
        return "redirect:/login";
    }

    /**
     * 修改密码页面
     */
    @GetMapping(value = "/change")
    public String changePassword() {
        return "change";
    }

    /**
     * 物业管理员界面
     */
    @GetMapping(value = "/admin")
    public String admin() {
        return "admin/login";
    }

    /**
     * 物业管理员页面
     */
    @GetMapping(value = "/admin/manage")
    public String manager() {
        return "manager";
    }

    /**
     * 超级管理员退出登录
     */
    @GetMapping(value = "/admin/logout")
    public String adminLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        return "redirect:/admin";
    }

    /**
     * 物业管理员修改密码页面
     */
    @GetMapping(value = "/admin/change")
    public String adminChangePassword() {
        return "admin/change";
    }

}
