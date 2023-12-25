package com.ocbc.project.cs.servicebus.filter.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.servicebus.filter.AbstractCustomerStaffFiler;

public class StaffNameFilter extends AbstractCustomerStaffFiler {
    @Override
    public CustomerStaff execute(CustomerStaff customerStaff) {
        if (ObjectUtil.isEmpty(customerStaff.getStaffName())) return null;
        if (getNext() != null) return getNext().execute(customerStaff);
        return customerStaff;
    }
}
