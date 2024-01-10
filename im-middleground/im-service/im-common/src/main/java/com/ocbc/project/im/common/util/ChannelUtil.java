package com.ocbc.project.im.common.util;

import com.ocbc.project.im.common.protocol.Packet;
import com.ocbc.project.im.common.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class ChannelUtil {
    public static void writeAndFlush(Channel channel, Packet packet) {
        ByteBuf byteBuf = PacketCodeC.getInstance().encode(packet);
        channel.writeAndFlush(byteBuf);
    }
}
