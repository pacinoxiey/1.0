package langrensha.io.client.web;

import java.net.URI;
import java.util.Scanner;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.google.gson.Gson;

import langrensha.io.client.Client;
import langrensha.io.protocol.Msg01;
import langrensha.io.protocol.Send;

public class Main {
	static Gson gson = new Gson();

	public static void main(String[] args) {
		int uid = 8140293;
		int index = 1;
		String sid = "9658f8556809a1ab4987ff7db21259d3";

		Send joinServer1 = new Send();
		Msg01 msg01 = new Msg01();
		joinServer1.setCmd(760);
		msg01.setSid("6b499fbc632078305cf240a4e6dd8f7f");
		msg01.setUid(8140352);
		joinServer1.setMsg(msg01);

		Send joinRoom = new Send();
		Msg01 msg02 = new Msg01();
		joinRoom.setCmd(771);
		msg02.setRoom_id(63);
		joinRoom.setMsg(msg02);
		//
		Send joinServer2 = new Send();
		Msg01 msg1 = new Msg01();
		joinServer2.setCmd(760);
		msg1.setSid("6a11e416d34f50b1eb6414c355ab1d4b");
		msg1.setUid(8140340);
		joinServer2.setMsg(msg1);

		Send joinRoom2 = new Send();
		Msg01 msg2 = new Msg01();
		joinRoom2.setCmd(771);
		msg2.setRoom_id(63);
		joinRoom.setMsg(msg2);
		//
		Send joinServer3 = new Send();
		Msg01 msg3 = new Msg01();
		joinServer3.setCmd(760);
		msg3.setSid("d13f2046b6dc96a35dfafd7b0562eef9");
		msg3.setUid(8140341);
		joinServer3.setMsg(msg3);

		Send joinRoom3 = new Send();
		Msg01 msg4 = new Msg01();
		joinRoom3.setCmd(771);
		msg4.setRoom_id(63);
		joinRoom.setMsg(msg4);
		//
		Send joinServer4 = new Send();
		Msg01 msg5 = new Msg01();
		joinServer4.setCmd(760);
		msg5.setSid("6ba41c0ea45bf56ffb10544e2250ed2d");
		msg5.setUid(8140342);
		joinServer4.setMsg(msg5);

		Send joinRoom4 = new Send();
		Msg01 msg6 = new Msg01();
		joinRoom4.setCmd(771);
		msg6.setRoom_id(63);
		joinRoom4.setMsg(msg6);
		//
		Send joinServer5 = new Send();
		Msg01 msg7 = new Msg01();
		joinServer5.setCmd(760);
		msg7.setSid("31f3b0ebe05ec56bd3abdb742d544297");
		msg7.setUid(8140343);
		joinServer5.setMsg(msg7);

		Send joinRoom5 = new Send();
		Msg01 msg8 = new Msg01();
		joinRoom5.setCmd(771);
		msg8.setRoom_id(63);
		joinRoom5.setMsg(msg8);
		// --------------------------------------------
		Send sit = new Send();
		Msg01 msg03 = new Msg01();
		sit.setCmd(772);
		msg03.setIndex(index);
		msg03.setRoom_id(62);
		sit.setMsg(msg03);

		Send playerInfo = new Send();
		playerInfo.setCmd(761);

		Send lineSpeak = new Send();
		lineSpeak.setCmd(774);

		Send exitLine = new Send();
		lineSpeak.setCmd(775);

		Scanner scanner = new Scanner(System.in);
		String string = null;
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // 获取WebSocket连接器，其中具体实现可以参照websocket-api.jar的源码,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
			String uri = "ws://192.168.0.161:9507";
			Session session = container.connectToServer(Client.class, new URI(uri)); // 连接会话
			// if (string.equals("joinServer")) {
			session.getBasicRemote().sendText(gson.toJson(joinServer1)); // 发送文本消息
			session.getBasicRemote().sendText(gson.toJson(joinServer2)); // 发送文本消息
			session.getBasicRemote().sendText(gson.toJson(joinServer3)); // 发送文本消息
			session.getBasicRemote().sendText(gson.toJson(joinServer4)); // 发送文本消息
//			session.getBasicRemote().sendText(gson.toJson(joinServer5)); // 发送文本消息
			session.getBasicRemote().sendText(gson.toJson(joinRoom)); // 发送文本消息
//			session.getBasicRemote().sendText(gson.toJson(joinRoom2)); // 发送文本消息
//			session.getBasicRemote().sendText(gson.toJson(joinRoom3)); // 发送文本消息
//			session.getBasicRemote().sendText(gson.toJson(joinRoom4)); // 发送文本消息
//			session.getBasicRemote().sendText(gson.toJson(joinRoom5)); // 发送文本消息
			// } else
			do {
				System.out.println("输入要发送的数据:");
				string = scanner.nextLine();
				if (string.equals("joinRoom")) {
					session.getBasicRemote().sendText(gson.toJson(joinRoom));
				} else if (string.equals("sit")) {
					session.getBasicRemote().sendText(gson.toJson(sit));
				} else if (string.equals("info")) {
					session.getBasicRemote().sendText(gson.toJson(playerInfo));
				} else if (string.equals("line")) {
					session.getBasicRemote().sendText(gson.toJson(lineSpeak));
				} else if (string.equals("exitLine")) {
					session.getBasicRemote().sendText(gson.toJson(exitLine));
				}
			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
