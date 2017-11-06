package langrensha.io.protocol;

import langrensha.game.data.Cmd;

/**
 * i/o 客户端
 * @author 10040
 *
 */
public class ClientReq {
	
	/** 消息号*/
	private Cmd cmd;
	/** 用户标识*/
	private String token;
	/** 消息内容*/
	private String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Cmd getCmd() {
		return cmd;
	}
	public void setCmd(Cmd cmd) {
		this.cmd = cmd;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
