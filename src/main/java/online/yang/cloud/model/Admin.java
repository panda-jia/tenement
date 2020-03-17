package online.yang.cloud.model;

/**
 * 管理员信息实体
 * @author 孙嘉
 * created in 2020/2/1 11:28
 */

public class Admin {
    private String adminId;

    private String account;

    private String password;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}