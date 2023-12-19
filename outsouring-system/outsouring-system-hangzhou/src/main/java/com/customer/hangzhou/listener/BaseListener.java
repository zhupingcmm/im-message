package com.customer.hangzhou.listener;

import cn.hutool.core.util.ObjectUtil;
import com.customer.hangzhou.entity.model.BaseDO;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class BaseListener{

    @PrePersist
    public void beforeSave(BaseDO ob) {
        ob.setUpdatedAt(new Date());
        ob.setCreatedAt(new Date());

        if (ObjectUtil.isEmpty(ob.getIsDeleted())) {
            ob.setIsDeleted(false);
        }
    }


    @PreUpdate
    public void beforeUpdate(BaseDO ob) {
        ob.setUpdatedAt(new Date());
        // todo this is a issue, create_at is no need to update
        ob.setCreatedAt(new Date());
        if (ObjectUtil.isEmpty(ob.getIsDeleted())) {
            ob.setIsDeleted(false);
        }
    }


}
