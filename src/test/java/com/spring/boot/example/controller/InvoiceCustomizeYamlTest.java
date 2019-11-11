package com.spring.boot.example.controller;

import com.spring.boot.example.model.Invoice;
import com.spring.boot.example.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class InvoiceCustomizeYamlTest {

    @Autowired
    private Invoice invoice;

    @Autowired
    private Product product;

    @Test
    public void run() {
        System.out.println(product);
    }
}
