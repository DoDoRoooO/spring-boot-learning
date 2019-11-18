package com.spring.boot.example.model;

import lombok.Data;

import java.util.Date;

@Data
public class Msg {

    private Long msgId;

    private String title;

    private String content;

    private String url;

    private Date createTime;
}
