package com.ocbc.project.cs.servicebus.router.beijing;

import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.servicebus.router.OutsourcingSystemRouter;
import com.ocbc.project.cs.servicebus.router.beijing.dto.BeijingCustomerStaff;

import java.util.List;

public class BeijingRouter implements OutsourcingSystemRouter {
    @Override
    public List<CustomerStaff> fetchOutsourcingSystemCustomerStaff(int pageIndex, int pageSize, String endpoint) {
        return null;
    }

    @Override
    public Integer fetchOutsourcingSystemCustomerStaffCount(String endpoint) {
        return null;
    }
}
