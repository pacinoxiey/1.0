package langrensha.io.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;


import langrensha.game.util.ServerConfig;

/**
 * 模拟客户端1
 * 
 * @author 10040
 *
 */
public class Client1 {
	static Properties properties = ServerConfig.getProp();

	private static int port;

	private static String ip = null;

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket("localhost", 9527);//建立连接
		System.out.println("socket:"+socket.isConnected());
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String msg = reader.readLine();
			pw.println(msg);
			pw.flush();
			if (msg.equals("bye")) {
				break;
			}
			System.out.println(in.readLine());
		}
		socket.close();
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
		// for (int i = 2; i < 13; i++) {
		// if (i/2==0) {
		//// baseMsg.setContent("sayHello");
		// } else {
		//// baseMsg.setContent("sayHi");
		// }
		//// String json = JSON.toJSONString(baseMsg);
		//// pw.println(json);
		// }
	}
}
