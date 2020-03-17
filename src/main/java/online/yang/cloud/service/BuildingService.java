package online.yang.cloud.service;

import online.yang.cloud.model.Building;
import online.yang.cloud.utils.PageInfo;

import java.util.List;

/**
 * @author .
 * created in 2020/2/2 9:57
 */

public interface BuildingService extends BaseService<Building> {

    PageInfo<Building> findAll(PageInfo<Building> info, Building building);

    Building findByBuildName(String buildName);

    List<Building> findAllByNoPage();

}
