package langrensha.io.advance;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import langrensha.game.Center;
import langrensha.game.base.Room;
import langrensha.game.base.RoomDetail;
import langrensha.game.base.Static;
import langrensha.game.util.ServerConfig;
import langrensha.io.notify.ProcessFactory;

/**
 * 关于nio selector 的使用实践 以及 对于指定客户端发送数据实践 在Java IO中，基本上可以分为文件类和Stream类两大类。
 * Channel 也相应地分为了FileChannel 和 Socket Channel， 其中 socket channel
 * 又分为三大类，一个是用于监听端口的ServerSocketChannel，
 * 第二类是用于TCP通信的SocketChannel，第三类是用于UDP通信的DatagramChannel
 * 
 * @author 10040 port :6666
 */
public class Server {

	private static ByteBuffer readBuffer = ByteBuffer.allocate(512);

	private static ByteBuffer writeBuffer = ByteBuffer.allocate(512);

	static Properties properties = ServerConfig.getProp();
	
	public static HashMap<String, Socket> socketList = new HashMap<>();

	private static Selector selector;

	private static Gson gson = new Gson();

	public static void go() throws IOException {
		String ip = properties.getProperty("server.ip");
		int port = Integer.valueOf(properties.getProperty("server.port"));
		selector = Selector.open();

		// 建立对端口的监听
		ServerSocketChannel ssc = ServerSocketChannel.open(); 
		ssc.configureBlocking(false); 
		ssc.socket().bind(new InetSocketAddress(ip, port)); // 绑定端口监听
		// ServerSocket
		System.out.println("Going to listen on " + ip + ": " + port);
		
		SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

		// 等待io操作
		while (true) {
			// int num = selector.select(); 

			Set selectedKeys = selector.selectedKeys(); // 会带有连接的操作类型

			Iterator it = selectedKeys.iterator();
			while (it.hasNext()) {

				SelectionKey key = (SelectionKey) it.next();
				it.remove();
				if (key.isAcceptable()) { 
					SocketChannel sc = ssc.accept();
					sc.configureBlocking(false);
					sc.register(selector, SelectionKey.OP_READ); 

				} else if (key.isReadable() && key.isValid()) {
					readBuffer.clear();

					SocketChannel sc = (SocketChannel) key.channel(); 

					try {
						sc.read(readBuffer);
					} catch (IOException e) {
						System.out.println("client closed connection " + sc.getRemoteAddress());
						key.cancel();
						sc.socket().close();
						sc.close();
						break;
					}
					// 创建一个线程池来负责 I/O 事件处理中的耗时部分
					String string = new String(readBuffer.array());

					ProcessFactory.Consumer(gson.toJson(string));
					readBuffer.flip();

					sc.write(readBuffer);
					System.out.println("received : " + new String(readBuffer.array()));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		System.out.println("-------------游戏房间初始化--------------");
		// 游戏房间
		for (int i = 1; i <= 5; i++) {
			String roomName = "游戏场" + i + "室";

			Room room = new Room(i, roomName, null, null, null);
			Center.roomList.add(room);

			RoomDetail roomDetail = new RoomDetail();
			roomDetail.setId(room.getRoomId());
			roomDetail.setMax_num(room.getMax_num());
			roomDetail.setRoom_name(room.getRoomName());

			Center.roomdetail.add(roomDetail);
		}
		// 聊天房间
		// for (int i = 0; i < 5; i++) {
		//
		// }
		System.out.println("---------------启动i/o服务器---------------");

		go();
	}
}
