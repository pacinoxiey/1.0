package langrensha.io.client.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.google.gson.Gson;

import langrensha.io.client.Client;
import langrensha.io.protocol.Msg01;
import langrensha.io.protocol.Send;

public class ClientWeb2 {
	static Gson gson = new Gson();

	public static void main(String[] args) throws UnknownHostException, IOException {

		f1();
	}

	public static void pw(Socket socket, String msg) {
		// BaseMsg baseMsg = new BaseMsg();
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.println(msg);
	}

	/*
	 * 
	 * {"cmd":771,"msg":{"uid":8139720,"room_id":62}}
	 */
	public static void f1() {
		int uid = 8140346;
		int index = 10;
		String sid = "9de4bca519f63166733053667fd22203";

		Send joinServer = new Send();
		Msg01 msg01 = new Msg01();
		joinServer.setCmd(760);
		msg01.setSid(sid);
		msg01.setUid(uid);
		joinServer.setMsg(msg01);

		Send joinRoom = new Send();
		Msg01 msg02 = new Msg01();
		joinRoom.setCmd(771);
		msg02.setRoom_id(62);
		joinRoom.setMsg(msg02);

		Send exit = new Send();
		exit.setCmd(773);

		Send info = new Send();
		info.setCmd(761);

		Send sit = new Send();
		Msg01 msg03 = new Msg01();
		sit.setCmd(772);
		msg03.setIndex(index);
		sit.setMsg(msg03);

		Send lineSpeak = new Send();
		lineSpeak.setCmd(774);

		Send exitLine = new Send();
		exitLine.setCmd(775);

		Send verify = new Send();
		verify.setCmd(781);
		Msg01 msg04 = new Msg01();
		msg04.setTarget(2);
		verify.setMsg(msg04);

		Send guard = new Send();
		Msg01 msgGuard = new Msg01();
		guard.setCmd(782);
		msgGuard.setTarget(1);
		guard.setMsg(msgGuard);

		Send getInfoById = new Send();
		getInfoById.setCmd(791);
		Msg01 infobyID = new Msg01();
		infobyID.setUid(8140059);
		getInfoById.setMsg(infobyID);

		Send hurt = new Send();
		hurt.setCmd(1);

		Send test = new Send();
		test.setCmd(99999);

		Send give = new Send();
		give.setCmd(792);
		Msg01 gift = new Msg01();
		gift.setTarget(8140275);
		gift.setProp_id(3000);
		give.setMsg(gift);

		Send gifts = new Send();
		gifts.setCmd(793);

		Scanner scanner = new Scanner(System.in);
		String string = null;
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // 获取WebSocket连接器，其中具体实现可以参照websocket-api.jar的源码,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
			// String uri = "ws://120.92.78.239:9504";
			String uri = "ws://192.168.0.161:9507";
			Session session = container.connectToServer(Client.class, new URI(uri)); // 连接会话
			// if (string.equals("joinServer")) {
			session.getBasicRemote().sendText(gson.toJson(joinServer)); // 发送文本消息
			// session.getBasicRemote().sendText(gson.toJson(joinRoom));
			// session.getBasicRemote().sendText(gson.toJson(sit));
			// } else
			do {
				System.out.println("输入要发送的数据:");
				string = scanner.nextLine();
				if (string.equals("joinRoom")) {
					session.getBasicRemote().sendText(gson.toJson(joinRoom));
				} else if (string.equals("sit")) {
					session.getBasicRemote().sendText(gson.toJson(sit));
					System.out.print(System.currentTimeMillis() / 1000);
				} else if (string.equals("info")) {
					session.getBasicRemote().sendText(gson.toJson(info));
				} else if (string.equals("line")) {
					session.getBasicRemote().sendText(gson.toJson(lineSpeak));
				} else if (string.equals("exitLine")) {
					session.getBasicRemote().sendText(gson.toJson(exitLine));
				} else if (string.equals("verify")) {
					session.getBasicRemote().sendText(gson.toJson(verify));
				} else if (string.equals("guard")) {
					session.getBasicRemote().sendText(gson.toJson(guard));
				} else if (string.equals("exit")) {
					session.getBasicRemote().sendText(gson.toJson(exit));
				} else if (string.equals("getinfo")) {
					session.getBasicRemote().sendText(gson.toJson(getInfoById));
				} else if (string.equals("hurt")) {
					session.getBasicRemote().sendText(gson.toJson(hurt));
				} else if (string.equals("test")) {
					session.getBasicRemote().sendText(gson.toJson(test));
				} else if (string.equals("give")) {
					session.getBasicRemote().sendText(gson.toJson(give));
				} else if (string.equals("gift")) {
					session.getBasicRemote().sendText(gson.toJson(gifts));
				}
			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
