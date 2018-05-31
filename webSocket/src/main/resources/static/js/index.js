$.namespace('web.socket.index');
var websocket = null;
web.socket.index={
    init:function () {
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            var socket = new SockJS('/websocket');
            websocket = Stomp.over(socket);
            websocket.connect({},function (frame) {
                console.log('Connected:' + frame);
                websocket.subscribe('/topic/getResponse',function(response){
                    console.info(response);
                    alert(JSON.parse(response.body));
                });
            })
            console.info(websocket);
            // websocket = new WebSocket("ws://localhost:8080/websocket");
        }
        else {
            alert('当前浏览器 Not support websocket')
        }

        //连接发生错误的回调方法
        websocket.onerror = function () {
            setMessageInnerHTML("WebSocket连接发生错误");
        };

        //连接成功建立的回调方法
        websocket.onopen = function () {
            setMessageInnerHTML("WebSocket连接成功");
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            setMessageInnerHTML(event.data);
        }

        //连接关闭的回调方法
        websocket.onclose = function () {
            setMessageInnerHTML("WebSocket连接关闭");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            web.socket.index.closeWebSocket();
        }

        //将消息显示在网页上
        function setMessageInnerHTML(innerHTML) {
            document.getElementById('message').innerHTML += innerHTML + '<br/>';
        }
    },
    send:function () {
        var message = document.getElementById('text').value;
        websocket.send("/app/welcome",{},JSON.stringify({'name':message}));
    },
    closeWebSocket:function () {
    }
}
$(function () {
    web.socket.index.init();
})
