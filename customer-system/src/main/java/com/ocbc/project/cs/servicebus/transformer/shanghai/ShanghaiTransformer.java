package com.ocbc.project.cs.servicebus.transformer.shanghai;

import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.servicebus.router.shanghai.dto.ShanghaiCustomerStaff;
import com.ocbc.project.cs.servicebus.transformer.CustomerStaffTransformer;

import java.util.List;

public class ShanghaiTransformer implements CustomerStaffTransformer<ShanghaiCustomerStaff> {
    @Override
    public List<CustomerStaff> transformCustomerStaffs(List<ShanghaiCustomerStaff> customerStaffs) {
        return null;
    }
}
