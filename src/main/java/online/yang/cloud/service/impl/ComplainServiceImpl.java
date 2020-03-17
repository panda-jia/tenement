package online.yang.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import online.yang.cloud.mapper.ComplainMapper;
import online.yang.cloud.model.Complain;
import online.yang.cloud.service.ComplainService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 孙嘉
 * created in 2020/2/5 10:16
 */

@Service
public class ComplainServiceImpl implements ComplainService {

    private ComplainMapper complainMapper;

    public ComplainServiceImpl(ComplainMapper complainMapper) {
        this.complainMapper = complainMapper;
    }

    @Override
    public PageInfo<Complain> findAll(PageInfo<Complain> info) {
        info.setCount(complainMapper.getCount());
        info.setData(complainMapper.findAll((info.getPage() - 1) * info.getLimit(), info.getLimit()));
        return info;
    }

    @Override
    public List<Complain> findLastTwo() {
        return complainMapper.findLastTwo();
    }

    @Override
    public Complain findById(String id) {
        return complainMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(Complain complain) {
        complain.setComplainId(IdUtil.simpleUUID());
        complain.setComplainDate(new Date());
        complain.setComplainStatus("处理中");
        return complainMapper.insertSelective(complain);
    }

    @Override
    public int update(Complain complain) {
        return complainMapper.updateByPrimaryKeySelective(complain);
    }

    @Override
    public int delete(String id) {
        return complainMapper.deleteByPrimaryKey(id);
    }

}
