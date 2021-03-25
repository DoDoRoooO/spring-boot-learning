package com.spring.boot.example.jdbc.service;

import com.spring.boot.example.common.model.Msg;
import com.spring.boot.example.jdbc.service.jdbc.MsgJDBCService;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
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
