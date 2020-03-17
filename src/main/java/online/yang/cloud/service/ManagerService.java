package online.yang.cloud.service;

import online.yang.cloud.model.Manager;
import online.yang.cloud.utils.PageInfo;

/**
 * @author Sheep
 * created in 2020/2/5 19:41
 */

public interface ManagerService extends BaseService<Manager> {

    PageInfo<Manager> findAll(PageInfo<Manager> info);

    Manager findByManagerName(String empName);

    Manager findByCondition(Manager manager);

}
