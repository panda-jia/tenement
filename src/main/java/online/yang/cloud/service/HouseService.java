package online.yang.cloud.service;

import online.yang.cloud.model.House;
import online.yang.cloud.utils.PageInfo;

/**
 * @author .
 * created in 2020/2/2 20:55
 */

public interface HouseService extends BaseService<House> {

    PageInfo<House> findAll(PageInfo<House> info);

    House findByNoAndUnit(House house);

    int findByBuilding(String building);

}
