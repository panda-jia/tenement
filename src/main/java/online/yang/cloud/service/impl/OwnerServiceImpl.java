package online.yang.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import online.yang.cloud.mapper.OwnerMapper;
import online.yang.cloud.model.Owner;
import online.yang.cloud.service.OwnerService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author .
 * created in 2020/1/31 18:25
 */

@Service
public class OwnerServiceImpl implements OwnerService {

    private OwnerMapper ownerMapper;

    public OwnerServiceImpl(OwnerMapper ownerMapper) {
        this.ownerMapper = ownerMapper;
    }

    @Override
    public Owner findById(String id) {
        return ownerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(Owner owner) {
        owner.setOwnerId(IdUtil.simpleUUID());
        owner.setOwnerBorth(owner.getOwnerBorth().replace("/", "-"));
        return ownerMapper.insert(owner);
    }

    @Override
    public int update(Owner owner) {
        return ownerMapper.updateByPrimaryKeySelective(owner);
    }

    @Override
    public int delete(String id) {
        return ownerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Owner> findAllByPage(PageInfo<Owner> info, Owner owner) {
        if (StringUtils.isEmpty(owner.getOwnerNo())) owner.setOwnerNo(null);
        if (StringUtils.isEmpty(owner.getOwnerName())) owner.setOwnerName(null);
        info.setCount(ownerMapper.getCount(owner.getOwnerNo(), owner.getOwnerName()));
        info.setData(ownerMapper.findAll((info.getPage() - 1) * info.getLimit(), info.getLimit(),
                owner.getOwnerNo(), owner.getOwnerName()));
        return info;
    }

    @Override
    public Owner findByOwnerNo(String ownerNo) {
        return ownerMapper.selectByOwnerNo(ownerNo);
    }

    @Override
    public Map<String, Object> getEchartsMap() {
        Map<String, Object> resultMap = new HashMap<>();
        List<String> genderList = new ArrayList<>();
        List<Map<String, Object>> genders = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int count = ownerMapper.getCountByGender(i);
            Map<String, Object> genderMap = new HashMap<>();
            if (i == 0) {
                genderList.add("男");
                genderMap.put("name", "男");
                genderMap.put("value", count);
            } else {
                genderList.add("女");
                genderMap.put("name", "女");
                genderMap.put("value", count);
            }
            genders.add(genderMap);
        }
        resultMap.put("gender", genderList);
        resultMap.put("map", genders);
        return resultMap;
    }
}
