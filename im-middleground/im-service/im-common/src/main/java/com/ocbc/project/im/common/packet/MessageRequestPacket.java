package com.ocbc.project.im.common.packet;

import com.ocbc.project.im.common.protocol.Command;
import com.ocbc.project.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class MessageRequestPacket extends Packet {


    private String message;

    private String toUserId;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
