package com.web.yangly.websocket.config;

import javax.websocket.*;

/**
 * @author yangly@eastcom-sw.com
 * @create 2018-05-31 11:00
 **/
@ClientEndpoint
public class WebsocketClient {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
}
