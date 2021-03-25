package com.spring.boot.example.common.model;

import lombok.Data;

@Data
public class MsgReceiver {

    private Long Id;

    private Long msgId;

    private Long userId;

    private Integer readFlag;

    private Integer deleteFlag;
}
