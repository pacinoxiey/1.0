package langrensha.io.client;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class Client {
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Connected to endpoint: " + session.getBasicRemote());
	}

	@OnMessage
	public void onMessage(String message) {// 卧槽你的majsasdbiusadja
		System.out.println(message);
	}

	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}
}
