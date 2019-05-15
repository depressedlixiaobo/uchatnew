package com.lux.uchat.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class MessageBody implements Serializable {
    //消息类型
    private int msgType;
    private String from;
    private String to;
    //消息内容
    private Object content;
}
