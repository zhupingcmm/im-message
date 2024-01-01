package com.ocbc.project.im.common.protocol;

import lombok.Data;

@Data
public abstract class Packet {
    /**
     * 协议版本号
     */
    private Byte version = 1;

    public abstract Byte getCommand();
}
