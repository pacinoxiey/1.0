package langrensha.io.protocol;

public class Send {
	
	private int cmd;
	
	private Msg01 msg;

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public Msg01 getMsg() {
		return msg;
	}

	public void setMsg(Msg01 msg) {
		this.msg = msg;
	}
	
	
}
