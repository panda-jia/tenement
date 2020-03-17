package online.yang.cloud.service.impl;

import online.yang.cloud.mapper.AdminMapper;
import online.yang.cloud.model.Admin;
import online.yang.cloud.service.AdminService;
import online.yang.cloud.utils.MD5Tools;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author 孙嘉
 * created in 2020/2/5 17:49
 */

@Service
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin findByAccountAndPwd(Admin admin) {
        admin.setPassword(MD5Tools.reverseToMd5(admin.getPassword()));
        return adminMapper.selectByAccountAndPwd(admin);
    }

    @Override
    public Admin findByCondition(Admin admin) {
        if (!StringUtils.isEmpty(admin.getPassword())) admin.setPassword(MD5Tools.reverseToMd5(admin.getPassword()));
        return adminMapper.selectByCondition(admin);
    }

    @Override
    public Admin findById(String id) {
        return null;
    }

    @Override
    public int add(Admin admin) {
        return 0;
    }

    @Override
    public int update(Admin admin) {
        if (!StringUtils.isEmpty(admin.getPassword())) admin.setPassword(MD5Tools.reverseToMd5(admin.getPassword()));
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public int delete(String id) {
        return 0;
    }
}
