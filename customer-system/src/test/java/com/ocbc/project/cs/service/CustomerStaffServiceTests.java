package com.ocbc.project.cs.service;

import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.mapper.CustomerStaffMapper;
import lombok.val;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerStaffServiceTests {

    @MockBean
    private CustomerStaffMapper customerStaffMapper;

    @Autowired
    private ICustomerStaffService customerStaffService;

    @Test
    public void testFindCustomerStaffById(){
        CustomerStaff customerStaff = new CustomerStaff();
        customerStaff.setStaffName("pzhu");
        customerStaff.setId(1L);
        customerStaff.setPhone("177777777");
        Mockito.when(customerStaffMapper.selectById(1L)).thenReturn(customerStaff);
        val customer = customerStaffService.findCustomerStaffById(1L);
        assertEquals(customer.getStaffName(), "pzhu");
    }


}
