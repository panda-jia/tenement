package online.yang.cloud.service;

import online.yang.cloud.model.Complain;
import online.yang.cloud.utils.PageInfo;

import java.util.List;

/**
 * @author 孙嘉
 * created in 2020/2/5 10:15
 */

public interface ComplainService extends BaseService<Complain> {

    PageInfo<Complain> findAll(PageInfo<Complain> info);

    List<Complain> findLastTwo();

}
