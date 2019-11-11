package com.spring.boot.example.controller;

import com.spring.boot.example.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PersonYamlTest {

    @Autowired
    private Person person;

    @Test
    public void person() {
        System.out.println(person);
    }
}
