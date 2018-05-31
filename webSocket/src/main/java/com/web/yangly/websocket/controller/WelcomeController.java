package com.web.yangly.websocket.controller;

import com.web.yangly.websocket.bean.AricMessage;
import com.web.yangly.websocket.bean.AricResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangly@eastcom-sw.com
 * @create 2018-05-30 17:10
 **/
@Controller
public class WelcomeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @MessageMapping("/welcome") //当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
    @SendTo("/topic/getResponse")//当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
    public AricResponse say(AricMessage message) {
        try {
            //睡眠1秒
            Thread.sleep(1000);
            test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AricResponse("welcome," + message.getName() + "!");
    }

//    @PostConstruct
    public void test(){
        Thread thread = null;
        try {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        AricMessage message = new AricMessage();
                        message.setName("yang");
                        messagingTemplate.convertAndSend("/topic/getResponse",message);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread.start();
    }
}
