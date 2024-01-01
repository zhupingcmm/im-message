package com.ocbc.project.im.common.packet;

import com.ocbc.project.im.common.protocol.Command;
import com.ocbc.project.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class HeartBeatPacket extends Packet {
    private String message = "heart-beat";
    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT;
    }
}
