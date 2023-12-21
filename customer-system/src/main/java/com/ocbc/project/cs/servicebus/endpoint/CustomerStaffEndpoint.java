package com.ocbc.project.cs.servicebus.endpoint;


import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.entity.tenant.OutsourcingSystem;

import java.util.List;

public interface CustomerStaffEndpoint {

   void fetchCustomerStaffs(OutsourcingSystem outsourcingSystem);

}
