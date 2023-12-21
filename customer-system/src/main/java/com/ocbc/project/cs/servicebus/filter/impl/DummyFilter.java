package com.ocbc.project.cs.servicebus.filter.impl;

import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.servicebus.filter.AbstractCustomerStaffFiler;

public class DummyFilter extends AbstractCustomerStaffFiler {
    @Override
    public CustomerStaff execute(CustomerStaff customerStaff) {
        return customerStaff;
    }
}
