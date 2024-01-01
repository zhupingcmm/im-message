package com.ocbc.project.im.common.packet;

import com.ocbc.project.im.common.protocol.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class MessageResponsePacket extends BaseResponsePacket{
    /**
     * 响应内容
     */
    private String message;

    private String fromUserId;

    private String fromUserName;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
