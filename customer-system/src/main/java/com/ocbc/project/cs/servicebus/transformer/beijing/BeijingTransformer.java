package com.ocbc.project.cs.servicebus.transformer.beijing;

import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.servicebus.router.beijing.dto.BeijingCustomerStaff;
import com.ocbc.project.cs.servicebus.transformer.CustomerStaffTransformer;

import java.util.List;

public class BeijingTransformer implements CustomerStaffTransformer<BeijingCustomerStaff> {
    @Override
    public List<CustomerStaff> transformCustomerStaffs(List<BeijingCustomerStaff> customerStaffs) {
        return null;
    }
}
