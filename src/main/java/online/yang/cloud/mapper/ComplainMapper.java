package online.yang.cloud.mapper;

import online.yang.cloud.model.Complain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplainMapper {
    int deleteByPrimaryKey(String complainId);

    int insert(Complain record);

    int insertSelective(Complain record);

    Complain selectByPrimaryKey(String complainId);

    int updateByPrimaryKeySelective(Complain record);

    int updateByPrimaryKey(Complain record);

    int getCount();

    List<Complain> findAll(@Param("page") int page, @Param("limit") int limit);

    List<Complain> findLastTwo();
}