package com.ocbc.project.cs.servicebus.transformer.hangzhou;

import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.entity.enums.Gender;
import com.ocbc.project.cs.entity.enums.Status;
import com.ocbc.project.cs.servicebus.router.hangzhou.dto.HangzhouCustomerStaff;
import com.ocbc.project.cs.servicebus.transformer.CustomerStaffTransformer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HangzhouTransformer implements CustomerStaffTransformer<HangzhouCustomerStaff> {
    @Override
    public List<CustomerStaff> transformCustomerStaffs(List<HangzhouCustomerStaff> customerStaffs) {

        return customerStaffs.stream().map(hangzhouCustomerStaff -> {
            CustomerStaff customerStaff = new CustomerStaff();

            //填充StaffName
            customerStaff.setStaffName(hangzhouCustomerStaff.getNickname());
            customerStaff.setNickname(hangzhouCustomerStaff.getNickname());
            customerStaff.setPhone(hangzhouCustomerStaff.getPhone());
            customerStaff.setRemark(hangzhouCustomerStaff.getRemark());
            customerStaff.setGoodAt(hangzhouCustomerStaff.getGoodAt());
            customerStaff.setAvatar(hangzhouCustomerStaff.getAvatar());

            //转换性别
            if(hangzhouCustomerStaff.getGender() != null) {
                customerStaff.setGender(Gender.valueOf(hangzhouCustomerStaff.getGender()));
            }

            //转换时间
            if(hangzhouCustomerStaff.getCreateTime() != null) {
                ZoneId zone = ZoneId.systemDefault();
                Instant createdTimeInstance = hangzhouCustomerStaff.getCreateTime().toInstant();
                LocalDateTime createdTimeLocalDateTime = LocalDateTime.ofInstant(createdTimeInstance, zone);
                customerStaff.setCreateTime(createdTimeLocalDateTime);
            }

            //初始化AccountId和Status
            customerStaff.setAccountId(-1L);
            customerStaff.setStatus(Status.OFFLINE);

            return customerStaff;


        }).collect(Collectors.toList());

    }
}
