package com.spring.boot.example.dao.jdbc;

import com.spring.boot.example.model.MsgReceiver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class MsgReceiverJDBCDAO {

    @Resource
    private JdbcTemplate primaryJdbcTemplate;

    public void save(MsgReceiver msgReceiver) {
        primaryJdbcTemplate.update("insert into t_msg_receiver values (?, ?, ?, ?, ?)",
                msgReceiver.getId(),
                msgReceiver.getMsgId(),
                msgReceiver.getUserId(),
                msgReceiver.getReadFlag(),
                msgReceiver.getDeleteFlag());
    }

    public void update(MsgReceiver msgReceiver) {
        primaryJdbcTemplate.update("update t_msg_receiver set READ_FLAG = ?, DELETE_FLAG = ? where ID = ?",
                msgReceiver.getReadFlag(),
                msgReceiver.getDeleteFlag(),
                msgReceiver.getId());
    }
}
