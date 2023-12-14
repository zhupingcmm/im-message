package com.ocbc.project.cs.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@MybatisPlusTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerStaffMapperTests {

    @Autowired
    private CustomerStaffMapper customerStaffMapper;

    @Test
    public void testGetCustomerStaffById(){
        val customerStaff = customerStaffMapper.selectById(1L);
        assertNotNull(customerStaff);
        assertEquals("tianyalan", customerStaff.getStaffName());
    }


    @Test
    public void testGetCustomerStaffByPhoneNumber(){
        val customerStaff = customerStaffMapper.findCustomerStaffByPhoneNumber("13355667789");
        assertNotNull(customerStaff);
        assertEquals("tianyalan", customerStaff.getStaffName());
    }
}
