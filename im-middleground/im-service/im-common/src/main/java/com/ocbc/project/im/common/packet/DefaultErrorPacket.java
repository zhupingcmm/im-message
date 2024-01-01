package com.ocbc.project.im.common.packet;

import com.ocbc.project.im.common.protocol.Command;

public class DefaultErrorPacket extends BaseResponsePacket{
    @Override
    public Byte getCommand() {
        return Command.DEFAULT_ERROR;
    }
}
