package com.ocbc.project.im.common.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class IMServerInfo {
    private String host;
    private int nettyPort;
    private int httpPort;

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
