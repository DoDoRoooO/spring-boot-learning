package com.spring.boot.example.model;

import lombok.Data;

@Data
public class Address {

    private String lines;

    private String city;

    private String state;

    private String postal;
}
