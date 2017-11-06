package langrensha.io.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import com.google.gson.Gson;

import langrensha.game.util.ServerConfig;
import langrensha.io.protocol.ClientReq;

/**
 * 模拟客户端1
 * 
 * @author 10040
 *
 */
public class Client2 {
	static Properties properties = ServerConfig.getProp();

	private static int port;

	private static String ip = null;

	public static void main(String[] args) {

		Gson gson = new Gson();
		System.out.println("--------------客戶端2開始建立連接--------------");
		port = Integer.valueOf(properties.getProperty("server.port"));
		ip = properties.getProperty("server.ip");

		System.out.println(port + "---" + ip);
		ClientReq clientReq = new ClientReq();
//		clientReq.setCmd("2");
		clientReq.setMsg("hi");
		clientReq.setToken("client2");
		String json = gson.toJson(clientReq);
		Socket socket = null;
		try {
			socket = new Socket(ip, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 发送数据
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pw.println(json);
		pw.flush();
		
		try {
			System.out.println("开始接收");
			Reader reader = new InputStreamReader(socket.getInputStream());
			char chars[] = new char[64];
			int len;
			StringBuffer sb = new StringBuffer();
			while ((len = reader.read(chars)) != -1) {
				sb.append(new String(chars, 0, len));
			}
			System.out.println("from server: " + sb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.close();
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
