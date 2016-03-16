package com.wd.socket;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

public class SocketServlet extends WebSocketServlet {

	private static final long serialVersionUID = 1L;

	private static List<SocketMessage> socketList = new ArrayList<SocketMessage>();

	@Override
	protected StreamInbound createWebSocketInbound(String arg0, HttpServletRequest arg1) {
		return new SocketMessage();
	}

	public static synchronized List<SocketMessage> getSocketList() {
		return socketList;
	}
}
