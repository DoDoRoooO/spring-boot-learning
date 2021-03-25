package com.spring.boot.example.jdbc.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ImportCustomizeXmlTest {

    @Autowired
    private ConfigurableApplicationContext ioc;

    @Test
    public void test() {
        boolean beanExist = ioc.containsBean("beanService");
        System.out.println(beanExist);
        if (beanExist) {
            Object beanService1 = ioc.getBean("beanService");
            System.out.println(beanService1);
        }
    }
}
