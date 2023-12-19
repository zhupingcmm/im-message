package com.customer.hangzhou.repository;


import com.customer.hangzhou.entity.HangzhouCustomerStaff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface HangzhouCustomerStaffRepository extends JpaRepository<HangzhouCustomerStaff, Long> {


    List<HangzhouCustomerStaff> findByIsDeletedFalse();


    List<HangzhouCustomerStaff> findByUpdatedAtAfter(Date updateTime);

    Page<HangzhouCustomerStaff> findAll(Specification<HangzhouCustomerStaff> specification, Pageable pageable);

}
