package online.yang.cloud.service;

import online.yang.cloud.model.Repair;
import online.yang.cloud.utils.PageInfo;

/**
 * @author 孙嘉
 * created in 2020/2/4 21:55
 */

public interface RepairService extends BaseService<Repair> {

    PageInfo<Repair> findAll(PageInfo<Repair> info);

}
