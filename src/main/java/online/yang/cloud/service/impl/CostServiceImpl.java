package online.yang.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import online.yang.cloud.mapper.CostMapper;
import online.yang.cloud.mapper.OwnerMapper;
import online.yang.cloud.model.Cost;
import online.yang.cloud.service.CostService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.stereotype.Service;

/**
 * @author .
 * created in 2020/2/4 9:15
 */

@Service
public class CostServiceImpl implements CostService {

    private CostMapper costMapper;
    private OwnerMapper ownerMapper;

    public CostServiceImpl(CostMapper costMapper, OwnerMapper ownerMapper) {
        this.costMapper = costMapper;
        this.ownerMapper = ownerMapper;
    }

    @Override
    public Cost findById(String id) {
        return costMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(Cost cost) {
        cost.setCostId(IdUtil.simpleUUID());
        cost.setCostStatus(0);
        cost.setCostOwner(ownerMapper.selectByOwnerNo(cost.getCostOwnerNo()).getOwnerName());
        return costMapper.insertSelective(cost);
    }

    @Override
    public int update(Cost cost) {
        return costMapper.updateByPrimaryKeySelective(cost);
    }

    @Override
    public int delete(String id) {
        return costMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Cost> findAll(PageInfo<Cost> info, Cost cost) {
        info.setCount(costMapper.getCount(cost.getCostOwner()));
        info.setData(costMapper.findAll((info.getPage() - 1) * info.getLimit(), info.getLimit(), cost.getCostOwner()));
        return info;
    }

    @Override
    public Cost findByTypeAndOwnerNo(String costType, String costOwnerNo) {
        return costMapper.findByTypeAndOwnerNo(costType, costOwnerNo);
    }
}
