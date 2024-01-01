package com.ocbc.project.im.common.packet;

import com.ocbc.project.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseResponsePacket extends Packet {

    /**
     * 返回码，0000表示成功
     */
    private String code = "0000";
    /**
     * 返回消息
     */
    private String message;

    /**
     * 判断是否操作成功， 0000表示成功
     */

    public boolean isSuccess() {
        return "0000".equals(code);
    }
}
