package online.yang.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import online.yang.cloud.mapper.ParkingMapper;
import online.yang.cloud.model.Parking;
import online.yang.cloud.service.ParkingService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author 孙嘉
 * created in 2020/2/5 14:24
 */

@Service
public class ParkingServiceImpl implements ParkingService {

    private ParkingMapper parkingMapper;

    public ParkingServiceImpl(ParkingMapper parkingMapper) {
        this.parkingMapper = parkingMapper;
    }

    @Override
    public PageInfo<Parking> findAll(PageInfo<Parking> info, String parkOwner) {
        if (StringUtils.isEmpty(parkOwner)) parkOwner = null;
        info.setCount(parkingMapper.getCount(parkOwner));
        info.setData(parkingMapper.selectAll((info.getPage() - 1) * info.getLimit(), info.getLimit(), parkOwner));
        return info;
    }

    @Override
    public Parking findByCondition(Parking parking) {
        return parkingMapper.selectByCondition(parking);
    }

    @Override
    public Parking findById(String id) {
        return parkingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(Parking parking) {
        parking.setParkId(IdUtil.simpleUUID());
        return parkingMapper.insertSelective(parking);
    }

    @Override
    public int update(Parking parking) {
        return parkingMapper.updateByPrimaryKeySelective(parking);
    }

    @Override
    public int delete(String id) {
        return parkingMapper.deleteByPrimaryKey(id);
    }

}
