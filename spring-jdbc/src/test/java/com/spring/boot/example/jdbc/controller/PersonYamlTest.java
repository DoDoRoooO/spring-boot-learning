package com.spring.boot.example.jdbc.controller;

import com.spring.boot.example.common.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class PersonYamlTest {

    @Autowired
    private Person person;

    @Test
    public void person() {
        System.out.println(person);
    }
}
