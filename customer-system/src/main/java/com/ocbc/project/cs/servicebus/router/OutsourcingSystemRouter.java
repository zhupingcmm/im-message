package com.ocbc.project.cs.servicebus.router;

import com.ocbc.project.cs.entity.CustomerStaff;

import java.util.List;

public interface OutsourcingSystemRouter {
    List<CustomerStaff> fetchOutsourcingSystemCustomerStaff(int pageIndex, int pageSize, String endpoint);

    Integer fetchOutsourcingSystemCustomerStaffCount(String endpoint);
}
