package com.ocbc.project.im.common.util;

import io.netty.channel.Channel;

public class LoginUtil {


    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }




}
