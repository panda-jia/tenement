package online.yang.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import online.yang.cloud.mapper.BuildingMapper;
import online.yang.cloud.model.Building;
import online.yang.cloud.service.BuildingService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author .
 * created in 2020/2/2 9:57
 */

@Service
public class BuildingServiceImpl implements BuildingService {

    private BuildingMapper buildingMapper;

    public BuildingServiceImpl(BuildingMapper buildingMapper) {
        this.buildingMapper = buildingMapper;
    }

    @Override
    public Building findById(String id) {
        return buildingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(Building building) {
        building.setBuildId(IdUtil.simpleUUID());
        return buildingMapper.insertSelective(building);
    }

    @Override
    public int update(Building building) {
        return buildingMapper.updateByPrimaryKeySelective(building);
    }

    @Override
    public int delete(String id) {
        return buildingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Building> findAll(PageInfo<Building> info, Building building) {
        info.setCount(buildingMapper.getCount(building.getBuildName()));
        info.setData(buildingMapper.findAll((info.getPage() - 1) * info.getLimit(), info.getLimit(),
                building.getBuildName()));
        return info;
    }

    @Override
    public Building findByBuildName(String buildName) {
        return buildingMapper.findByBuildName(buildName);
    }

    @Override
    public List<Building> findAllByNoPage() {
        return buildingMapper.findAllByNoPage();
    }
}
