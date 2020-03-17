package online.yang.cloud.mapper;

import online.yang.cloud.model.Owner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 小阳Sheep
 * created in 2020/2/1 11:28
 */

@Repository
public interface OwnerMapper {
    int deleteByPrimaryKey(String ownerId);

    int insert(Owner record);

    int insertSelective(Owner record);

    Owner selectByPrimaryKey(String ownerId);

    int updateByPrimaryKeySelective(Owner record);

    int updateByPrimaryKey(Owner record);

    Owner selectByOwnerNo(String ownerNo);

    int getCount(@Param("ownerNo") String ownerNo, @Param("ownerName") String ownerName);

    List<Owner> findAll(@Param("page") Integer page, @Param("limit") Integer limit,
                        @Param("ownerNo") String ownerNo, @Param("ownerName") String ownerName);

    int getCountByGender(int ownerGender);

}