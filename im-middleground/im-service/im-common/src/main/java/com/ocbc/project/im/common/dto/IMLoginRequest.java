package com.ocbc.project.im.common.dto;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IMLoginRequest {
    private String userid;
    private String username;
    private String serverHost;
    private int nettyPort;
    private int httpPort;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
