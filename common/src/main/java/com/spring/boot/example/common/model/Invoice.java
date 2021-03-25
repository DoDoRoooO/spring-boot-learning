package com.spring.boot.example.common.model;

import com.spring.boot.example.common.utils.MyPropertySourceFactory;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@PropertySource(value = {"classpath:yaml-example/invoice.yaml"}, factory = MyPropertySourceFactory.class)
@ConfigurationProperties(prefix = "invoice")
public class Invoice {

//    @Max(value = 1000, message = "最大1000")
    private Integer invoice;

    private String date;

    private Person billTo;

    private Person shipTo;

    private List<Product> product;

    private Float tax;

    private Float total;

    private String comments;
}
