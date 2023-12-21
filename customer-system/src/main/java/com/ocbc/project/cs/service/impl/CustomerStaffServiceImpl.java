package com.ocbc.project.cs.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.entity.tenant.OutsourcingSystem;
import com.ocbc.project.cs.mapper.CustomerStaffMapper;
import com.ocbc.project.cs.service.ICustomerStaffService;
import com.ocbc.project.cs.service.IOutsourcingSystemService;
import com.ocbc.project.cs.servicebus.endpoint.CustomerStaffEndpoint;
import com.ocbc.project.infrastructure.exception.BizException;
import com.ocbc.project.infrastructure.page.PageObject;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerStaffServiceImpl extends ServiceImpl<CustomerStaffMapper, CustomerStaff> implements ICustomerStaffService {

    private final IOutsourcingSystemService outsourcingSystemService;

    private final CustomerStaffEndpoint customerStaffEndpoint;


    @Override
    public PageObject<CustomerStaff> findCustomerStaffs(Long pageSize, Long pageIndex) {
        return getCustomerStaffPageObject(null, pageSize, pageIndex);
    }

    @Override
    public List<CustomerStaff> findCustomerStaffs() {
        LambdaQueryWrapper<CustomerStaff> wrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(wrapper);
    }

    @Override
    public PageObject<CustomerStaff> findCustomerStaffsByName(String staffName, Long pageSize, Long pageIndex) {
        return getCustomerStaffPageObject(staffName, pageSize, pageIndex);
    }


    private PageObject<CustomerStaff> getCustomerStaffPageObject(String staffName, Long pageSize, Long pageIndex){
        LambdaQueryWrapper<CustomerStaff> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtil.isNotEmpty(staffName)) {
            lambdaQueryWrapper.like(CustomerStaff::getStaffName, staffName);
        }

        lambdaQueryWrapper.orderByDesc(CustomerStaff::getCreateTime);

        IPage<CustomerStaff>page = new Page<>(pageIndex, pageSize);
        val pageResult = baseMapper.selectPage(page, lambdaQueryWrapper);
        PageObject<CustomerStaff> pageObject = new PageObject<>();
        pageObject.buildPage(pageResult.getCurrent(), pageResult.getPages(), pageResult.getTotal(), pageResult.getRecords());
        return pageObject;
    }

    @Override
    public CustomerStaff findCustomerStaffById(Long staffId) {
        return baseMapper.selectById(staffId);
    }

    @Override
    public Boolean createCustomerStaff(CustomerStaff customerStaff) throws BizException {
        return this.save(customerStaff);
    }

    @Override
    public Boolean updateCustomerStaff(CustomerStaff customerStaff) {
        return this.updateById(customerStaff);
    }

    @Override
    public Boolean deleteCustomerStaffById(Long staffId) {
        return this.removeById(staffId);
    }

    @Override
    @Transactional
    public Boolean createCustomerStaff(List<CustomerStaff> customerStaffs) {
        return this.saveBatch(customerStaffs);
    }

    @Override
    public void syncOutsourcingCustomerStaffsBySystemId(String systemId) {
      OutsourcingSystem outsourcingSystem =  outsourcingSystemService.findBySystemId(systemId);
      customerStaffEndpoint.fetchCustomerStaffs(outsourcingSystem);
    }
}
