package com.ocbc.project.im.common.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    private static final Map<String, Channel> sessionMap = new ConcurrentHashMap<>();

    public static void buildSession(Session session, Channel channel){
        sessionMap.putIfAbsent(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static Channel getChannelByUserId(String id){
        return sessionMap.get(id);
    }

    public static Session getSessionByChannel(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }


    public static boolean hasLogin(Channel channel) {
        Attribute<Session> login = channel.attr(Attributes.SESSION);
        return login.get() != null;
    }





}
