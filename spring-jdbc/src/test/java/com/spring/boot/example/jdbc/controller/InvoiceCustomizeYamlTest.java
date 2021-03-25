package com.spring.boot.example.jdbc.controller;

import com.spring.boot.example.common.model.Invoice;
import com.spring.boot.example.common.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
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
