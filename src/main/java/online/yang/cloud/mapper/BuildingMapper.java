package online.yang.cloud.mapper;

import online.yang.cloud.model.Building;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuildingMapper {
    int deleteByPrimaryKey(String buildId);

    int insert(Building record);

    int insertSelective(Building record);

    Building selectByPrimaryKey(String buildId);

    int updateByPrimaryKeySelective(Building record);

    int updateByPrimaryKey(Building record);

    int getCount(@Param("buildName") String buildName);

    List<Building> findAll(@Param("page") Integer page, @Param("limit") Integer limit,
                           @Param("buildName") String buildName);

    Building findByBuildName(String buildName);

    List<Building> findAllByNoPage();
}