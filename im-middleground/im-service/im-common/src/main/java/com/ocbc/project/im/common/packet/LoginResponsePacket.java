package com.ocbc.project.im.common.packet;

import com.ocbc.project.im.common.protocol.Command;

public class LoginResponsePacket extends BaseResponsePacket{
    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
