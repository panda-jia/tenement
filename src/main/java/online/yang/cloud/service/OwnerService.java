package online.yang.cloud.service;

import online.yang.cloud.model.Owner;
import online.yang.cloud.utils.PageInfo;

import java.util.Map;

/**
 * @author .
 * created in 2020/1/31 18:25
 */

public interface OwnerService extends BaseService<Owner> {

    PageInfo<Owner> findAllByPage(PageInfo<Owner> info, Owner owner);

    Owner findByOwnerNo(String ownerNo);

    Map<String, Object> getEchartsMap();

}
