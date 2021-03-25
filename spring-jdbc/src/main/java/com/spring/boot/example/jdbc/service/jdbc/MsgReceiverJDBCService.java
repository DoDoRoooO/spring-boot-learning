package com.spring.boot.example.jdbc.service.jdbc;


import com.spring.boot.example.common.model.MsgReceiver;

public interface MsgReceiverJDBCService {

    void save(MsgReceiver msgReceiver);

    void update(MsgReceiver msgReceiver);
}
