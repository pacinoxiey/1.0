package langrensha.io.notify;

import com.google.gson.Gson;

import langrensha.game.Center;
import langrensha.game.data.Cmd;
import langrensha.io.protocol.ClientReq;

public class NotifyBox {
	/**
	 * 单例
	 */
	private NotifyBox(){
	}
	private static NotifyBox instance = new NotifyBox();
	
	public static NotifyBox getIntance(){
		return instance;
	}
	
	/**
	 * @describle
	 * @param msg
	 */
	public void MsgProcess(ClientReq msg){
		switch (msg.getCmd()) {
		case ROOMLIST:
//			sayHello(msg);
			System.out.println(new Gson().toJson(Center.roomdetail));
			break;
//		case "sayHi":
//			sayHi(msg);
//			break;
		default:
			break;
		}
	}
	
	/**
	 * 消息处理
	 * @param msg
	 */
//	public void sayHello(BaseMsg msg){
//		System.out.println("this is say hello:" + msg.getContent().toString());
//	}
//	
//	public void sayHi(BaseMsg msg){
//		System.out.println("this is say hi:" + msg.getContent().toString());
//	}
}
