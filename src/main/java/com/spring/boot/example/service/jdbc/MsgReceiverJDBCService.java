package com.spring.boot.example.service.jdbc;

import com.spring.boot.example.model.MsgReceiver;

public interface MsgReceiverJDBCService {

    void save(MsgReceiver msgReceiver);

    void update(MsgReceiver msgReceiver);
}
