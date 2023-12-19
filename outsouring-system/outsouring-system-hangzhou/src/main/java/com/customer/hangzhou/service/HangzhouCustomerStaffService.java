package com.customer.hangzhou.service;

import com.customer.hangzhou.entity.HangzhouCustomerStaff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface HangzhouCustomerStaffService {

    Page<HangzhouCustomerStaff> findAllCustomerStaffs(String nickname, PageRequest pageRequest);

    List<HangzhouCustomerStaff> findCustomerStaffsByUpdatedTime(Date updatedTime);

    HangzhouCustomerStaff createCustomerStaff(HangzhouCustomerStaff customerStaff);

    HangzhouCustomerStaff updateCustomerStaff(HangzhouCustomerStaff customerStaff);

    HangzhouCustomerStaff deleteCustomerStaffById(Long id);
}
