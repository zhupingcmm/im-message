package com.customer.hangzhou.repository;


import com.customer.hangzhou.entity.HangzhouCustomerStaff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface HangzhouCustomerStaffRepository extends JpaRepository<HangzhouCustomerStaff, Long> {


    List<HangzhouCustomerStaff> findByIsDeletedFalse();


    @Query(value = "select * from hangzhou_customer_staff where updated_at >= :updateTime", nativeQuery = true)
    List<HangzhouCustomerStaff> findByUpdatedAtGreaterThanOrEqual(@Param("updateTime") Date updateTime);


    Page<HangzhouCustomerStaff> findAll(Specification<HangzhouCustomerStaff> specification, Pageable pageable);

}
