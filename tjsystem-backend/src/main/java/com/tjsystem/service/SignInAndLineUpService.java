package com.tjsystem.service;

import com.tjsystem.pojo.QueueInfo;

public interface SignInAndLineUpService {

    boolean SignIn(int Reserve_id);
    boolean LineUp(int Reserve_id);

    boolean UpdateQueue(QueueInfo queueInfo);

}
