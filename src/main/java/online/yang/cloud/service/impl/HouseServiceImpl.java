package online.yang.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import online.yang.cloud.mapper.HouseMapper;
import online.yang.cloud.mapper.OwnerMapper;
import online.yang.cloud.model.House;
import online.yang.cloud.service.HouseService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author .
 * created in 2020/2/2 20:55
 */

@Service
public class HouseServiceImpl implements HouseService {

    private HouseMapper houseMapper;
    private OwnerMapper ownerMapper;

    public HouseServiceImpl(HouseMapper houseMapper, OwnerMapper ownerMapper) {
        this.houseMapper = houseMapper;
        this.ownerMapper = ownerMapper;
    }

    @Override
    public House findById(String id) {
        return houseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(House house) {
        house.setHouseId(IdUtil.simpleUUID());
        return houseMapper.insertSelective(house);
    }

    @Override
    public int update(House house) {
        if (!house.getHouseOwnerNo().equals("-1")) {
            house.setHouseDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            house.setHouseOwner(ownerMapper.selectByOwnerNo(house.getHouseOwnerNo()).getOwnerName());
        }
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int delete(String id) {
        return houseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<House> findAll(PageInfo<House> info) {
        info.setCount(houseMapper.getCount());
        info.setData(houseMapper.findAll((info.getPage() - 1) * info.getLimit(), info.getLimit()));
        return info;
    }

    @Override
    public House findByNoAndUnit(House house) {
        return houseMapper.findByNoAndUnit(house);
    }

    @Override
    public int findByBuilding(String building) {
        return houseMapper.findByBuilding(building);
    }
}
