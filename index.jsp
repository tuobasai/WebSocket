<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>WebSocket</title>
<script type="text/javascript">
    var ws = null;
    function startServer() {
        var url = "ws://192.168.0.102/SocketServlet.ws";
        if ('WebSocket' in window) {
            ws = new WebSocket(url);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(url);
        } else {
            alert('Your browser Unsupported WebSocket!');
            return;
        }

        ws.onopen = function() {
            document.getElementById("content").innerHTML += 'websocket open! Welcome!<br />';
        };
        ws.onmessage = function(event) {
        	 var vals=event.data.split("@#@");
        	 var msgType=vals[0];
        	 if (msgType == "userId") {
        		 var msg=vals[1];
        	     var mes="userId"+"@#@"+msg+"@#@"+ '<%=request.getRemoteAddr()%>';
        	     ws.send(mes);
        	 } else if (msgType == "msg") {
        	 	 document.getElementById("content").innerHTML +=  '服务器端：' + vals[1] + '<br />';
        	 }
        };
        ws.onclose = function() {
            document.getElementById("content").innerHTML += 'websocket closed! Byebye!<br />';
        };
    }
</script>
</head>
<body onload="startServer()">
    <div id="content"></div>
</body>
</html>