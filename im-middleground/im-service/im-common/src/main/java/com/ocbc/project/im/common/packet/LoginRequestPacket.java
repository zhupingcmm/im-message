package com.ocbc.project.im.common.packet;

import com.ocbc.project.im.common.protocol.Command;
import com.ocbc.project.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class LoginRequestPacket extends Packet {

    private String userId;
    private String userName;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
