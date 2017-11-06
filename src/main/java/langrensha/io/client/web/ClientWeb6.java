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

public class ClientWeb6 {
	static Gson gson = new Gson();

	public static void main(String[] args) throws UnknownHostException, IOException {

		// Socket socket = new Socket("ws//:127.0.0.1", 9507);// 建立连接
		// System.out.println("socket:" + socket.isConnected());
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(socket.getInputStream()));
		// PrintWriter pw = new PrintWriter(socket.getOutputStream());
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(System.in));
		//
		// while (true) {
		// String msg = reader.readLine();
		// pw.println(msg);
		// pw.flush();
		// if (msg.equals("bye")) {
		// break;
		// }
		// System.out.println(in.readLine());
		// }
		// socket.close();
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
		int uid = 8140365;
		int index = 6;
		String sid = "611c50e1329132f694c95fa0a3af9996";

		Send joinServer = new Send();
		Msg01 msg01 = new Msg01();
		joinServer.setCmd(760);
		msg01.setSid(sid);
		msg01.setUid(uid);
		joinServer.setMsg(msg01);

		Send joinRoom = new Send();
		Msg01 msg02 = new Msg01();
		joinRoom.setCmd(771);
		msg02.setRoom_id(66);
		msg02.setUid(uid);
		joinRoom.setMsg(msg02);

		Send sit = new Send();
		Msg01 msg03 = new Msg01();
		msg03.setIndex(index);
		sit.setCmd(772);
		sit.setMsg(msg03);
		msg03.setUid(uid);
		msg03.setRoom_id(62);

		Scanner scanner = new Scanner(System.in);
		String string = null;
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // 获取WebSocket连接器，其中具体实现可以参照websocket-api.jar的源码,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
			String uri = "ws://192.168.0.161:9507";
			Session session = container.connectToServer(Client.class, new URI(uri)); // 连接会话
			
			session.getBasicRemote().sendText(gson.toJson(joinServer)); // 发送文本消息
			
			do {
				System.out.println("输入要发送的数据:");
				string = scanner.nextLine();
				if (string.equals("joinServer")) {
					session.getBasicRemote().sendText(gson.toJson(joinServer)); // 发送文本消息
				} else if (string.equals("joinRoom")) {
					session.getBasicRemote().sendText(gson.toJson(joinRoom)); // 发送文本消息
				} else if (string.equals("sit")) {
					session.getBasicRemote().sendText(gson.toJson(sit)); // 发送文本消息
				}
			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
