package online.yang.cloud.mapper;

import online.yang.cloud.model.Admin;
import org.springframework.stereotype.Repository;

/**
 * @author 孙嘉
 * created in 2020/2/5 17:50
 */

@Repository
public interface AdminMapper {
    int deleteByPrimaryKey(String adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectByAccountAndPwd(Admin admin);

    Admin selectByCondition(Admin admin);
}