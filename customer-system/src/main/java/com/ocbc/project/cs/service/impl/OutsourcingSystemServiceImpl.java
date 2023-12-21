package com.ocbc.project.cs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.project.cs.entity.tenant.OutsourcingSystem;
import com.ocbc.project.cs.mapper.OutsourcingSystemMapper;
import com.ocbc.project.cs.service.ICustomerStaffService;
import com.ocbc.project.cs.service.IOutsourcingSystemService;
import org.springframework.stereotype.Service;

@Service
public class OutsourcingSystemServiceImpl extends ServiceImpl<OutsourcingSystemMapper, OutsourcingSystem> implements IOutsourcingSystemService {
    @Override
    public OutsourcingSystem findBySystemId(String systemId) {
        return baseMapper.findByAppId(systemId);
    }
}
