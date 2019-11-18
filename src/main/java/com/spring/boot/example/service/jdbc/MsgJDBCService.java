package com.spring.boot.example.service.jdbc;

import com.spring.boot.example.model.Msg;

public interface MsgJDBCService {

    void save(Msg msg);

    void update(Msg msg);

    void saveOrUpdate(Msg msg);
}
