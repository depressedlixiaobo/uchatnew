package com.lux.uchat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lux.uchat.domain.Message;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class MsgDealService {

    private ObjectMapper _mapper;

    private RedisTemplate<String, Object> redisTemplate;


    public Message toMsg(String msg) throws IOException {
        Message msgObj =  _mapper.readValue(msg,Message.class);
        return  msgObj;
    }

    public <T> long insertMsg2Redis(T msg){

      long longId = redisTemplate.opsForList().rightPush("aaaa",msg);
      return  longId;
    }

}
