package online.yang.cloud.mapper;

import online.yang.cloud.model.Cost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CostMapper {
    int deleteByPrimaryKey(String costId);

    int insert(Cost record);

    int insertSelective(Cost record);

    Cost selectByPrimaryKey(String costId);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);

    int getCount(@Param("costOwner") String costOwner);

    List<Cost> findAll(@Param("page") int page, @Param("limit") int limit, @Param("costOwner") String costOwner);

    Cost findByTypeAndOwnerNo(@Param("costType") String costType, @Param("costOwnerNo") String costOwnerNo);
}