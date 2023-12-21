package com.customer.hangzhou.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.customer.hangzhou.entity.HangzhouCustomerStaff;
import com.customer.hangzhou.repository.HangzhouCustomerStaffRepository;
import com.customer.hangzhou.service.HangzhouCustomerStaffService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HangzhouCustomerStaffServiceImpl implements HangzhouCustomerStaffService {

    private final HangzhouCustomerStaffRepository hangzhouCustomerStaffRepository;
    @Override
    public Page<HangzhouCustomerStaff> findAllCustomerStaffs(String nickname, Date updateTime, PageRequest pageRequest) {
        Specification<HangzhouCustomerStaff> spec = Specification.where(null);
        if (StringUtils.isNoneBlank(nickname)){
             spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nickname"), nickname));
        } else if (ObjectUtil.isNotEmpty(updateTime)) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("updatedAt"), updateTime));
        }

        return hangzhouCustomerStaffRepository.findAll(spec, pageRequest);
    }

    @Override
    public List<HangzhouCustomerStaff> findCustomerStaffsByUpdatedTime(Date updatedTime) {
        return hangzhouCustomerStaffRepository.findByUpdatedAtGreaterThanOrEqual(updatedTime);
    }

    @Override
    public HangzhouCustomerStaff createCustomerStaff(HangzhouCustomerStaff customerStaff) {
        return hangzhouCustomerStaffRepository.save(customerStaff);
    }

    @Override
    public HangzhouCustomerStaff updateCustomerStaff(HangzhouCustomerStaff customerStaff) {
        val staffRepositoryById = hangzhouCustomerStaffRepository.findById(customerStaff.getId());

        return hangzhouCustomerStaffRepository.save(customerStaff);
    }

    @Override
    public HangzhouCustomerStaff deleteCustomerStaffById(Long id) {

//        HangzhouCustomerStaff customerStaff = new HangzhouCustomerStaff().setId(id);
        Optional<HangzhouCustomerStaff> optional = hangzhouCustomerStaffRepository.findById(id);
        val customerStaff = optional.get();
        customerStaff.setIsDeleted(true);
        return hangzhouCustomerStaffRepository.save(customerStaff);
    }
}
