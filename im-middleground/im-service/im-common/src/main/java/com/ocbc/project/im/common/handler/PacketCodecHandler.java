package com.ocbc.project.im.common.handler;

import com.ocbc.project.im.common.protocol.Packet;
import com.ocbc.project.im.common.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static volatile PacketCodecHandler instance;
    private PacketCodecHandler () {}

    public static PacketCodecHandler getInstance() {
        if (instance == null) {

            synchronized (PacketCodecHandler.class) {
                if (instance == null) {
                    instance = new PacketCodecHandler();
                }
            }
        }

        return instance;
    }
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) throws Exception {
        ByteBuf byteBuf = channelHandlerContext.channel().alloc().ioBuffer();
        // todo check
        byteBuf = PacketCodeC.getInstance().encode(byteBuf, packet);
        list.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf buf, List<Object> list) throws Exception {
        list.add(PacketCodeC.getInstance().decode(buf));
    }
}
