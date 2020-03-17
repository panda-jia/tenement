package online.yang.cloud.service;

import online.yang.cloud.model.Admin;

/**
 * @author 孙嘉
 * created in 2020/2/5 17:49
 */

public interface AdminService extends BaseService<Admin> {

    Admin findByAccountAndPwd(Admin admin);

    Admin findByCondition(Admin admin);

}
