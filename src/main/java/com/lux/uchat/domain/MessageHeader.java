package com.lux.uchat.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
class MessageHeader implements Serializable {

    private String authentication;
    private long client;
    //消息发送时间,由于服务器设置
    private long server;
}
