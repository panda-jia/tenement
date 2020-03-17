package online.yang.cloud.mapper;

import online.yang.cloud.model.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 孙嘉
 * created in 2020/2/4 23:15
 */

@Repository
public interface NoticeMapper {
    int deleteByPrimaryKey(String noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String noticeId);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    int getCount();

    List<Notice> findAll(@Param("page") int page, @Param("limit") int limit);

    Notice selectLastNotice();
}