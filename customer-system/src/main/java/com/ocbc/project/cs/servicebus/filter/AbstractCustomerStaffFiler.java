package com.ocbc.project.cs.servicebus.filter;

import cn.hutool.core.util.ObjectUtil;
import com.ocbc.project.cs.entity.CustomerStaff;

public abstract class AbstractCustomerStaffFiler implements CustomerStaffFiler{

    private CustomerStaffFiler next;
    public CustomerStaffFiler getNext(){
        if (ObjectUtil.isEmpty(next)) return null;
        return next;
    }

    public CustomerStaffFiler addFilter(CustomerStaffFiler customerFiler){
        this.next = customerFiler;
        return this.next;
    }
}
