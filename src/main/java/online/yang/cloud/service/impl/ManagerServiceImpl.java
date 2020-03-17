package online.yang.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import online.yang.cloud.mapper.ManagerMapper;
import online.yang.cloud.model.Manager;
import online.yang.cloud.service.ManagerService;
import online.yang.cloud.utils.MD5Tools;
import online.yang.cloud.utils.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Sheep
 * created in 2020/2/5 19:41
 */

@Service
public class ManagerServiceImpl implements ManagerService {

    private ManagerMapper managerMapper;

    public ManagerServiceImpl(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    @Override
    public PageInfo<Manager> findAll(PageInfo<Manager> info) {
        info.setCount(managerMapper.getCount());
        info.setData(managerMapper.selectAll((info.getPage() - 1) * info.getLimit(), info.getLimit()));
        return info;
    }

    @Override
    public Manager findByManagerName(String empName) {
        return managerMapper.selectByManagerName(empName);
    }

    @Override
    public Manager findByCondition(Manager manager) {
        if (!StringUtils.isEmpty(manager.getEmpPwd())) manager.setEmpPwd(MD5Tools.reverseToMd5(manager.getEmpPwd()));
        return managerMapper.selectByCondition(manager);
    }

    @Override
    public Manager findById(String id) {
        return managerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(Manager manager) {
        manager.setEmpId(IdUtil.simpleUUID());
        manager.setEmpPwd(MD5Tools.reverseToMd5("123456"));
        return managerMapper.insertSelective(manager);
    }

    @Override
    public int update(Manager manager) {
        if (!StringUtils.isEmpty(manager.getEmpPwd())) manager.setEmpPwd(MD5Tools.reverseToMd5(manager.getEmpPwd()));
        return managerMapper.updateByPrimaryKeySelective(manager);
    }

    @Override
    public int delete(String id) {
        return managerMapper.deleteByPrimaryKey(id);
    }

}
