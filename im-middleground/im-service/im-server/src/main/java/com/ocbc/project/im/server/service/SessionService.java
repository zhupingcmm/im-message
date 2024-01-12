package com.ocbc.project.im.server.service;

import com.ocbc.project.im.common.util.Session;


import java.util.List;

public interface SessionService {

    List<Session> getAllSessions ();

    void removeSession(String userId);




}
