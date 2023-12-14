package com.ocbc.project.infrastructure.vo;

import lombok.Data;

@Data
public class BaseReq {

    private String traceId;

    private Integer pageIndex = 1;

    private Integer pageSize = 10;
}
