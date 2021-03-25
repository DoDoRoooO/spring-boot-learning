package com.spring.boot.example.jdbc.service.jdbc;


import com.spring.boot.example.common.model.Msg;

public interface MsgJDBCService {

    void save(Msg msg);

    void update(Msg msg);

    void saveOrUpdate(Msg msg);
}
