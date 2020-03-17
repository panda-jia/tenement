package online.yang.cloud.mapper;

import online.yang.cloud.model.Parking;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 孙嘉
 * created in 2020/2/5 14:57
 */

@Repository
public interface ParkingMapper {
    int deleteByPrimaryKey(String parkId);

    int insert(Parking record);

    int insertSelective(Parking record);

    Parking selectByPrimaryKey(String parkId);

    int updateByPrimaryKeySelective(Parking record);

    int updateByPrimaryKey(Parking record);

    int getCount(@Param("parkOwner") String parkOwner);

    List<Parking> selectAll(@Param("page") int page, @Param("limit") int limit,
                            @Param("parkOwner") String parkOwner);

    Parking selectByCondition(Parking parking);
}