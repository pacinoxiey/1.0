package langrensha.game.util;

public class GsonObj {
	private GsonObj() {
	}

	private static GsonObj instance = new GsonObj();

	public static GsonObj getIntance() {
		
		return instance;
	}
}
