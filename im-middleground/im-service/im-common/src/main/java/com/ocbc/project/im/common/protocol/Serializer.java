package com.ocbc.project.im.common.protocol;

public interface Serializer {

    /**
     *
     *获取序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * 将java 对象系列化成二进制
     */

    byte[] serialize(Object object);

    /**
     * 将二进制 反序列化为对象
     */

    <T> T deSerialize(Class<T> clazz, byte[] bytes);
}
