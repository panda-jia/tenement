package online.yang.cloud.service;

import online.yang.cloud.model.Parking;
import online.yang.cloud.utils.PageInfo;

/**
 * @author 孙嘉
 * created in 2020/2/5 14:23
 */

public interface ParkingService extends BaseService<Parking> {

    PageInfo<Parking> findAll(PageInfo<Parking> info, String parkOwner);

    Parking findByCondition(Parking parking);

}
