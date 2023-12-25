package com.ocbc.project.cs.servicebus.filter.impl;

import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.servicebus.filter.AbstractCustomerStaffFiler;
import org.apache.commons.lang3.StringUtils;

public class PhoneFilter extends AbstractCustomerStaffFiler {
    @Override
    public CustomerStaff execute(CustomerStaff customerStaff) {
        if (StringUtils.isEmpty(customerStaff.getPhone())) return null;

        if (getNext() != null) return getNext().execute(customerStaff);
        return customerStaff;
    }
}
