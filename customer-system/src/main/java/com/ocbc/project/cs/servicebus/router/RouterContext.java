package com.ocbc.project.cs.servicebus.router;

import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.entity.tenant.OutsourcingSystem;

import java.util.List;

public class RouterContext {

    private final OutsourcingSystemRouter outsourcingSystemRouter;

    public RouterContext(OutsourcingSystemRouter outsourcingSystemRouter){
        this.outsourcingSystemRouter = outsourcingSystemRouter;
    }

    public List<CustomerStaff> fetchOutsourcingSystemCustomerStaff(int pageIndex, int pageSize, String endpoint){

       return outsourcingSystemRouter.fetchOutsourcingSystemCustomerStaff(pageIndex, pageSize, endpoint);
    }
}
