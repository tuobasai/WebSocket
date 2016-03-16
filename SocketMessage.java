package com.wd.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

public class SocketMessage extends MessageInbound {

	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	protected void onOpen(WsOutbound outbound) {
		super.onOpen(outbound);
		this.userId = String.valueOf(outbound.hashCode());
		String message = "userId" + "@#@" + this.userId;
		try {
			CharBuffer buffer = CharBuffer.wrap(message);
			this.getWsOutbound().writeTextMessage(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SocketServlet.getSocketList().add(this);
	}

	@Override
	protected void onTextMessage(CharBuffer buffer) throws IOException {

		String msgOriginal = buffer.toString();
		String[] vals = msgOriginal.split("@#@");

		if (vals[0].equals("userId")) {
			for (SocketMessage socketmessage : SocketServlet.getSocketList()) {
				if (socketmessage.getUserId().equals(vals[1])) {
					socketmessage.setUserId(vals[2]);
				}
			}
		}
	}

	@Override
	protected void onClose(int status) {
		SocketServlet.getSocketList().remove(this);
		super.onClose(status);
	}

	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {

	}
}
