package com.customer.beijing.controller.vo.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BeijingCustomerStaffCreateReqVO implements Serializable {

    private String nickname;
    private String avatar;
    private String phone;
    private String gender;
    private String goodAt;
    private String remark;
}
