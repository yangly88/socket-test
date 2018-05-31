package com.web.yangly.websocket.bean;

/**
 * @author yangly@eastcom-sw.com
 * @create 2018-05-30 17:12
 **/
public class AricResponse {

    private String responseMessage;

    public AricResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
