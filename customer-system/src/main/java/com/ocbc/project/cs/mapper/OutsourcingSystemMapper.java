package com.ocbc.project.cs.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocbc.project.cs.entity.tenant.OutsourcingSystem;

public interface OutsourcingSystemMapper extends BaseMapper<OutsourcingSystem> {

    default OutsourcingSystem findByAppId(String systemId){
        LambdaQueryWrapper<OutsourcingSystem> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OutsourcingSystem::getAppId, systemId);
        lambdaQueryWrapper.eq(OutsourcingSystem::getIsDeleted, false);
        return selectOne(lambdaQueryWrapper);
    }
}
