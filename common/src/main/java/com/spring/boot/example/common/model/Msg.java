package com.spring.boot.example.common.model;

import java.util.Date;
import lombok.Data;

@Data
public class Msg {

    private Long msgId;

    private String title;

    private String content;

    private String url;

    private Date createTime;
}
