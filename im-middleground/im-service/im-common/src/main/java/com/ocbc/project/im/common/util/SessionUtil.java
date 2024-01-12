package com.ocbc.project.im.common.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SessionUtil {

    @Getter
    public static final Map<String, Channel> sessionMap = new ConcurrentHashMap<>();

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

    public static void removeSessionByChannel(Channel channel) {
        Attribute<Session> sessionAttribute =  channel.attr(Attributes.SESSION);

        Session session = sessionAttribute.get();
        sessionMap.remove(session.getUserId());
    }


    public static List<Session> getAllSessions() {
        return sessionMap.values().stream().map(channel -> {
            Attribute<Session> sessionAttribute =  channel.attr(Attributes.SESSION);
            return sessionAttribute.get();
        }).collect(Collectors.toList());

    }


}
