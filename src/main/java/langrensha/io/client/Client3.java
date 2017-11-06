package langrensha.io.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import com.google.gson.Gson;

import langrensha.game.data.Cmd;
import langrensha.game.util.ServerConfig;
import langrensha.io.protocol.ClientReq;

/**
 * 使用协议传输
 * @author 10040
 *
 */
public class Client3 {
	static Properties properties = ServerConfig.getProp();

	private static int port;

	private static String ip = null;
	
	static Gson gson = new Gson();
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket("localhost", 9527);//建立连接
		System.out.println("socket:"+socket.isConnected());
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ClientReq clientReq = new ClientReq();
		clientReq.setCmd(Cmd.ROOMLIST);
		clientReq.setMsg("hello server");
		clientReq.setToken("第一无二的标识");
		while (true) {
			String msg = reader.readLine();
			if(msg.equals("y")) {
				pw.println(gson.toJson(clientReq));
			}else {
				pw.println(msg);
			}
			
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
