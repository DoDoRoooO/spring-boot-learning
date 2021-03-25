package com.spring.boot.example.jdbc.service.jdbc.impl;

import com.spring.boot.example.common.model.Msg;
import com.spring.boot.example.common.model.MsgReceiver;
import com.spring.boot.example.jdbc.dao.jdbc.MsgJDBCDAO;
import com.spring.boot.example.jdbc.service.jdbc.MsgJDBCService;
import com.spring.boot.example.jdbc.service.jdbc.MsgReceiverJDBCService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MsgJDBCServiceImpl implements MsgJDBCService {

    @Resource
    private MsgJDBCDAO msgJDBCDAO;

    @Autowired
    private MsgReceiverJDBCService msgReceiverJDBCService;

    @Transactional
    @Override
    public void save(Msg msg) {
        msgJDBCDAO.save(msg);
    }

    @Override
    public void update(Msg msg) {
        msgJDBCDAO.update(msg);
        int i = 1 / 0;
    }

    @Transactional
    @Override
    public void saveOrUpdate(Msg msg) {
        msgJDBCDAO.save(msg);
        MsgReceiver msgReceiver = new MsgReceiver();
        msgReceiver.setId(msg.getMsgId());
        msgReceiver.setMsgId(msg.getMsgId());
        msgReceiver.setUserId(1000L);
        msgReceiver.setReadFlag(0);
        msgReceiver.setDeleteFlag(0);
        msgReceiverJDBCService.save(msgReceiver);
    }
}
