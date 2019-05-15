package com.lux.uchat.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    public MessageHeader header;

    public MessageBody body;

}
