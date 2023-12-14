package com.ocbc.project.cs.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocbc.project.cs.entity.CustomerStaff;

public interface CustomerStaffMapper extends BaseMapper<CustomerStaff> {

    default CustomerStaff findCustomerStaffByPhoneNumber(String phoneNumber){
        LambdaQueryWrapper<CustomerStaff> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CustomerStaff::getPhone, phoneNumber);
        lambdaQueryWrapper.eq(CustomerStaff::getIsDeleted, false);
        return selectOne(lambdaQueryWrapper);
    }
}
