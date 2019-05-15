package com.lux.uchat.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@AllArgsConstructor
public class MsgTools {


    private ObjectMapper objectMapper ;

    public Future<String> sendMsg(String msg){

        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            //插入数据库 , 谁发给谁的，
            //调用 redis 保存
            System.out.println("执行完成");
            return   "ok";
        });
         return  future;
    }
}
