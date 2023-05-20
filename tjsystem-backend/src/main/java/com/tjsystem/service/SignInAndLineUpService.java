package com.tjsystem.service;

import com.tjsystem.pojo.QueueInfo;

public interface SignInAndLineUpService {

    boolean SignIn(String Patient_id);
    boolean LineUp(String Patient_id);

    boolean UpdateQueue(QueueInfo queueInfo);

}
