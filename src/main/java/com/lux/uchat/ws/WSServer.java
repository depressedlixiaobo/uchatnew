package com.lux.uchat.ws;

import com.lux.uchat.domain.Message;
import com.lux.uchat.domain.MessageBody;
import com.lux.uchat.domain.Users;
import com.lux.uchat.service.MsgDealService;
import com.lux.uchat.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws/{token}")
@Controller
public class WSServer {
      static Logger logger = LoggerFactory.getLogger(WSServer.class);

      private static ConcurrentHashMap<String,WSServer> map = new ConcurrentHashMap<>();

      private Session CurrentSession;

      private String objKey = "";


      private static MsgDealService msgDealService;
      private static UsersService usersService;
     @Autowired
     public void setUsersService(UsersService usersService) {
        WSServer.usersService = usersService;
    }
      @Autowired
      public void setMsgDealService(MsgDealService msgDealService) {
            WSServer.msgDealService = msgDealService;
        }

      @OnOpen
      public void OnOPen(Session session, @PathParam("token")String token) throws IOException {
          //后续修改为拦截器 ， 这样避免 握手了

          if(!StringUtils.isEmpty(token)){
              Users user = usersService.getUser(token);
              String userId =  user.getId();
              objKey = userId; //设置userid 为objeckey
              if(!StringUtils.isEmpty(userId)){
                  if(map.get(objKey) == null ){
                      CurrentSession = session;
                      map.put(objKey,this);
                  }
                  System.out.println(CurrentSession.getId()+ "-> on open ...");
              }else{
                  System.out.println("userid="+userId);
                  session.close();
              }
          }else{
              session.close();
          }
      }

      @OnMessage
      public void OnMessage(String msg) throws IOException {
          long timeStamp = System.currentTimeMillis();

          Message _msg = msgDealService.toMsg(msg);
         // _msg.getHeader().setClient(timeStamp);
          String fromUser = _msg.body.getFrom();
           WSServer server =  map.get(fromUser);
           if(server!=null){
               System.out.println(CurrentSession.getId()+"发送来了消息->"+  msg);
               long id = msgDealService.insertMsg2Redis(_msg);
               CurrentSession.getAsyncRemote().sendText( "redis中的id为"+id+"服务端收到消息:"
                       +CurrentSession.getId()+"发送的->"
                       +msg);
           }else{
               //没上线 或者不存在这个用户
           }







      }

      @OnClose
      public void Onclose(){
          System.out.println(this);
          System.out.println(map.mappingCount());
           map.remove(objKey);
          System.out.println(map.mappingCount());
          System.out.println("关闭连接");
      }

      @OnError
      public void Error(Throwable error){
          System.out.println(error.toString());
      }
}
