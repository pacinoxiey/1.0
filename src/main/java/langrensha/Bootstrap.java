package langrensha;

import java.io.IOException;

import langrensha.game.Center;
import langrensha.game.base.Room;
import langrensha.game.base.RoomDetail;
import langrensha.io.advance.Server;

public class Bootstrap {
	public static void main(String[] args) {
		System.out.println("***************langrensha bootstrap*************");
		System.out.println("----No.1 init game room info");
		// 游戏房间
		for (int i = 1; i <= 5; i++) {
			String roomName = "游戏场" + i + "室";

			Room room = new Room(i, roomName, null, null,null);
			Center.roomList.add(room);

			RoomDetail roomDetail = new RoomDetail();
			roomDetail.setId(room.getRoomId());
			roomDetail.setMax_num(room.getMax_num());
			roomDetail.setRoom_name(room.getRoomName());

			Center.roomdetail.add(roomDetail);
		}
		// System.out.println("-------------No.1 init chat room info");
		// 聊天房间
		// for (int i = 0; i < 5; i++) {
		//
		// }
		System.out.println("----No.2 start I/O server");
		try {
			Server.go();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
