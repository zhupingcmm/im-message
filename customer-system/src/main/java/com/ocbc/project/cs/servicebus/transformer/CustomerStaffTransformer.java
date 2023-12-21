package com.ocbc.project.cs.servicebus.transformer;

import com.ocbc.project.cs.entity.CustomerStaff;

import java.util.List;

public interface CustomerStaffTransformer <T>{

    List<CustomerStaff> transformCustomerStaffs(List<T> customerStaffs);
}
