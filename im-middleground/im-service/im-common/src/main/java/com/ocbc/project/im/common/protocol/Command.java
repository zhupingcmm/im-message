package com.ocbc.project.im.common.protocol;

public interface Command {

    /**
     * 心跳包
     */
    Byte HEART_BEAT = 0;
    /**
     * 登录请求
     */
    Byte LOGIN_REQUEST = 1;
    /**
     * 登录响应
     */
    Byte LOGIN_RESPONSE = 2;

    /**
     * 消息请求
     */
    Byte MESSAGE_REQUEST = 3;
    /**
     * 消息响应
     */
    Byte MESSAGE_RESPONSE = 4;

    /**
     * 默认错误
     */
    Byte DEFAULT_ERROR = 127;
}
