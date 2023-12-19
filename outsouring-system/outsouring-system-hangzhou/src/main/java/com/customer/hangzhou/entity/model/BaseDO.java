package com.customer.hangzhou.entity.model;

import com.customer.hangzhou.listener.BaseListener;
import lombok.Data;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@EntityListeners(BaseListener.class)
@MappedSuperclass
public class BaseDO  implements Serializable {
    private static final long serialVersionUID = 1L;

    /***
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 是否删除，0：否，1：是
     **/
    private Boolean isDeleted;
}
