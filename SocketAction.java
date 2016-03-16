package com.wd.action;

import java.io.IOException;
import java.nio.CharBuffer;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.wd.socket.SocketMessage;
import com.wd.socket.SocketServlet;

@Namespace("/")
@Action(value = "socketAction")
public class SocketAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String ipAddr;

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public void socket() {

		for (SocketMessage socketmessage : SocketServlet.getSocketList()) {

			if (socketmessage.getUserId().equals(ipAddr)) {
				try {
					CharBuffer c = CharBuffer.wrap("msg@#@" + "01,01,呼叫总部，这里有人抢劫马云！");
					socketmessage.getWsOutbound().writeTextMessage(c);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

}
