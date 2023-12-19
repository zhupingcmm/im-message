package com.customer.hangzhou.entity;

import com.customer.hangzhou.entity.model.BaseDO;
import com.customer.hangzhou.listener.BaseListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "hangzhou_customer_staff")
public class HangzhouCustomerStaff extends BaseDO {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别
     */
    private String gender;

    /**
     * 擅长领域
     */
    private String goodAt;

    /**
     * 备注
     */
    private String remark;


}
