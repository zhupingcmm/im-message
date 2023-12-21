package com.ocbc.project.cs.servicebus.router;

import com.ocbc.project.cs.entity.tenant.OutsourcingSystem;
import com.ocbc.project.cs.servicebus.router.beijing.BeijingRouter;
import com.ocbc.project.cs.servicebus.router.beijing.dto.BeijingCustomerStaff;
import com.ocbc.project.cs.servicebus.router.hangzhou.HangZhouRouter;
import com.ocbc.project.cs.servicebus.router.shanghai.ShanghaiRouter;
import lombok.val;

public class RouterFactory {

    public static OutsourcingSystemRouter getRouter(OutsourcingSystem outsourcingSystem){
        val systemCode = outsourcingSystem.getSystemName();
        switch (systemCode){
            case "beijing":
                return new BeijingRouter();
            case "shanghai":
                return new ShanghaiRouter();
            default:
                return new HangZhouRouter();
        }
    }
}
