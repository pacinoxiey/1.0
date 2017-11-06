package langrensha.game.hand;

import java.io.Serializable;

import langrensha.game.base.Room;


public abstract class Act implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5901703572663520282L;
	/**
	 * 投票
	 */
	public void vote(Room room, int index) {
		System.out.println("投票");
		
	}
	
	
}
