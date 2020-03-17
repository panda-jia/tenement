package online.yang.cloud.mapper;

import online.yang.cloud.model.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 孙嘉
 * created in 2020/2/5 19:42
 */

@Repository
public interface ManagerMapper {
    int deleteByPrimaryKey(String empId);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(String empId);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    int getCount();

    List<Manager> selectAll(@Param("page") int page, @Param("limit") int limit);

    Manager selectByManagerName(String name);

    Manager selectByCondition(Manager manager);
}