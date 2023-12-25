package com.ocbc.project.cs.servicebus.filter;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.ocbc.project.cs.entity.CustomerStaff;
import org.junit.jupiter.api.Test;

public class CustomerStaffFilterChainTests {


    @Test
    public void testCustomerStaffFilterChainExecute() {
        CustomerStaff customerStaff = new CustomerStaff();
        customerStaff.setId(1L);
        customerStaff.setStaffName("zp");
        customerStaff.setPhone("1234455");

        CustomerStaffFilterChain chain = new CustomerStaffFilterChain();
        CustomerStaff result = chain.execute(customerStaff);

        System.out.println(result);
    }
}
