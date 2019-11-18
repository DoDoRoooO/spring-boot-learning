package com.spring.boot.example.service.jdbc.impl;

import com.spring.boot.example.dao.jdbc.MsgReceiverJDBCDAO;
import com.spring.boot.example.model.MsgReceiver;
import com.spring.boot.example.service.jdbc.MsgReceiverJDBCService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class MsgReceiverJDBCServiceImpl implements MsgReceiverJDBCService {

    @Resource
    private MsgReceiverJDBCDAO msgReceiverJDBCDAO;

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void save(MsgReceiver msgReceiver) {
        msgReceiverJDBCDAO.save(msgReceiver);
    }

    @Transactional
    @Override
    public void update(MsgReceiver msgReceiver) {
        msgReceiverJDBCDAO.update(msgReceiver);
    }
}
