package com.ocbc.project.im.common.protocol;

import com.ocbc.project.im.common.packet.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

public class PacketCodeC {
    /**
     * 魔数
     */
    public static final int MAGIC_NUMBER = 0x88888888;

    public static volatile PacketCodeC instance;

    /**
     * 采用单例模式
     */
    public static PacketCodeC getInstance() {
        if (instance == null) {
            synchronized (PacketCodeC.class) {
                if (instance == null) {
                    instance = new PacketCodeC();
                }
            }
        }
        return instance;
    }

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<Byte, Class<? extends Packet>>();
        packetTypeMap.put(Command.DEFAULT_ERROR, DefaultErrorPacket.class);
        packetTypeMap.put(Command.HEART_BEAT, HeartBeatPacket.class);
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<Byte, Serializer>();
        Serializer serializer = new JsonSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    private PacketCodeC() {

    }


    /**
     * 默认使用json序列化，如需修改，可以使用setSerializer（serializer）
     */
    private static Serializer serializer = new JsonSerializer();

    /**
     * 编码
     * <p>
     * 魔数（4字节） + 版本号（1字节） + 序列化算法（1字节） + 指令（1字节） + 数据长度（4字节） + 数据（N字节）
     */
    public ByteBuf encode(ByteBufAllocator alloc, Packet packet) {
        //创建ByteBuf对象
        ByteBuf buf = alloc.ioBuffer();
        return encode(buf, packet);
    }

    public ByteBuf encode(ByteBuf buf, Packet packet) {
        //序列化java对象
        byte[] objBytes = serializer.serialize(packet);

        //实际编码过程，即组装通信包
        //魔数（4字节） + 版本号（1字节） + 序列化算法（1字节） + 指令（1字节） + 数据长度（4字节） + 数据（N字节）
        buf.writeInt(MAGIC_NUMBER);
        buf.writeByte(packet.getVersion());
        buf.writeByte(serializer.getSerializerAlgorithm());
        buf.writeByte(packet.getCommand());
        buf.writeInt(objBytes.length);
        buf.writeBytes(objBytes);

        return buf;
    }

    public ByteBuf encode(Packet packet) {
        return encode(ByteBufAllocator.DEFAULT, packet);
    }

    /**
     * 解码
     * <p>
     * 魔数（4字节） + 版本号（1字节） + 序列化算法（1字节） + 指令（1字节） + 数据长度（4字节） + 数据（N字节）
     */
    public Packet decode(ByteBuf buf) {
        //魔数校验（暂不做）
        buf.skipBytes(4);
        //版本号校验（暂不做）
        buf.skipBytes(1);
        //序列化算法
        byte serializeAlgorithm = buf.readByte();
        //指令
        byte command = buf.readByte();
        //数据长度
        int length = buf.readInt();
        //数据
        byte[] dataBytes = new byte[length];
        buf.readBytes(dataBytes);

        Class<? extends Packet> packetType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);
        if (packetType != null && serializer != null) {
            return serializer.deSerialize(packetType, dataBytes);
        }

        return null;
    }

    /**
     * 根据序列化算法获取对应的serializer
     *
     * @param serializeAlgorithm
     * @return serializer
     */
    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    /**
     * 根据指令类型获取对应的packet
     *
     * @param command
     * @return packet
     */
    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }

    /**
     * 设置序列化方式
     */
    public static void setSerializer(Serializer serializer) {
        PacketCodeC.serializer = serializer;
    }
}
