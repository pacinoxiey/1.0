package langrensha.game.base;

import langrensha.game.data.RoomLevel;
import langrensha.game.data.RoomType_E;

/**
 * 房间基础信息
 * 
 * @author 10040
 * 
 */
public class RoomDetail {
	
	private int id;

	private String room_name;

	private int current_num = 0;

	private int max_num;
	
	private RoomLevel roomLevel = RoomLevel.civilian;
	
	private RoomType_E roomType_E = RoomType_E.game;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public int getCurrent_num() {
		return current_num;
	}

	public void setCurrent_num(int current_num) {
		this.current_num = current_num;
	}

	public int getMax_num() {
		return max_num;
	}

	public void setMax_num(int max_num) {
		this.max_num = max_num;
	}

	public RoomLevel getRoomLevel() {
		return roomLevel;
	}

	public void setRoomLevel(RoomLevel roomLevel) {
		this.roomLevel = roomLevel;
	}

	public RoomType_E getRoomType_E() {
		return roomType_E;
	}

	public void setRoomType_E(RoomType_E roomType_E) {
		this.roomType_E = roomType_E;
	}
	
}
