package online.yang.cloud.service;

import online.yang.cloud.model.Notice;
import online.yang.cloud.utils.PageInfo;

/**
 * @author 孙嘉
 * created in 2020/2/4 18:33
 */

public interface NoticeService extends BaseService<Notice> {

    PageInfo<Notice> findAll(PageInfo<Notice> info);

    Notice findLastNotice();

}
