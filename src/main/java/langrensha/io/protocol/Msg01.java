package langrensha.io.protocol;

public class Msg01 {

	private String sid;

	private int uid;

	private int room_id;
	/** 座位号 */
	private int index;

	private int target;

	private int prop_id;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getProp_id() {
		return prop_id;
	}

	public void setProp_id(int prop_id) {
		this.prop_id = prop_id;
	}
}
