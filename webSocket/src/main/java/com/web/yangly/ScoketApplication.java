package com.web.yangly;

import com.web.yangly.websocket.config.WebsocketClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangly@eastcom-sw.com
 * @create 2018-05-29 9:19
 **/
@SpringBootApplication
@EnableWebSocket
public class ScoketApplication {
    public static void main(String[] args){
        SpringApplication.run(ScoketApplication.class,args);
    }

    @Bean
    public RestTemplate getRestTemplate(ClientHttpRequestFactory factory){
        RestTemplate restTemplate=new RestTemplate(factory);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(300000);//单位为ms
        factory.setConnectTimeout(10000);//单位为ms
        return factory;
    }

//    @Bean
//    public Session getSession() throws IOException, DeploymentException {
//        WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
//        String uri = "ws://127.0.0.1:8080//websocket";
//        Session session = webSocketContainer.connectToServer(WebsocketClient.class, URI.create(uri)); // 连接会话
//        return session;
//    }
}
