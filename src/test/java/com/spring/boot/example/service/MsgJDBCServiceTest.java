package com.spring.boot.example.service;

import com.spring.boot.example.model.Msg;
import com.spring.boot.example.service.jdbc.MsgJDBCService;
import com.spring.boot.example.service.jdbc.MsgReceiverJDBCService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgJDBCServiceTest {

    @Autowired
    private MsgJDBCService msgJDBCService;

    @Test
    public void test() {
        Msg msg = new Msg();
        msg.setMsgId(10001L);
        msg.setTitle("第一个消息");
        msg.setContent("Propagation.REQUIRES_NEW 测试");
        msg.setCreateTime(new Date());
        msgJDBCService.saveOrUpdate(msg);
    }
}
