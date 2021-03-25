package com.spring.boot.example.jdbc.service.jdbc.impl;

import com.spring.boot.example.common.model.MsgReceiver;
import com.spring.boot.example.jdbc.dao.jdbc.MsgReceiverJDBCDAO;
import com.spring.boot.example.jdbc.service.jdbc.MsgReceiverJDBCService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
