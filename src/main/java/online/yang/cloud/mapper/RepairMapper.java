package online.yang.cloud.mapper;

import online.yang.cloud.model.Repair;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 孙嘉
 * created in 2020/2/4 23:19
 */

@Repository
public interface RepairMapper {
    int deleteByPrimaryKey(String repairId);

    int insert(Repair record);

    int insertSelective(Repair record);

    Repair selectByPrimaryKey(String repairId);

    int updateByPrimaryKeySelective(Repair record);

    int updateByPrimaryKey(Repair record);

    int getCount();

    List<Repair> selectAll(@Param("page") int page, @Param("limit") int limit);
}