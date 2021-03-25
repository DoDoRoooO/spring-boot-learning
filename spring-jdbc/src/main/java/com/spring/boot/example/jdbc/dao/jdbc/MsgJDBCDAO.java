package com.spring.boot.example.jdbc.dao.jdbc;

import com.spring.boot.example.common.model.Msg;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MsgJDBCDAO {

    @Resource
    private JdbcTemplate primaryJdbcTemplate;

    public void save(Msg msg) {
        primaryJdbcTemplate.update(" insert into t_msg values (?, ?, ?, ?, ?)",
                msg.getMsgId(),
                msg.getTitle(),
                msg.getContent(),
                msg.getUrl(),
                msg.getCreateTime());
    }

    public void update(Msg msg) {
        primaryJdbcTemplate.update("update t_msg set TITLE = ?, CONTENT = ?, URL = ?, CREATE_TIME = ? where MSG_ID = ?",
                msg.getTitle(),
                msg.getContent(),
                msg.getUrl(),
                msg.getCreateTime(),
                msg.getMsgId());
    }
}
