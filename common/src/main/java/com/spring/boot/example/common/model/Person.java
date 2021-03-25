package com.spring.boot.example.common.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@ConfigurationProperties("person")
public class Person {

//    @Value("${person.name}")
//    @NotEmpty
    private String firstName;

    private String lastName;

//    @Value("${person.age}")
//    @Min(value = 21, message = "年龄不能小于21")
    private Integer age;

    private String given;

    private String family;

    private Address address;
}
