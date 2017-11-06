package langrensha.game.base;

import langrensha.game.data.Actor;
import langrensha.game.data.PlayerStatu;

/**
 * @author 10040
 *
 */

public class Player{
	public Player(int uid,int index) {
		// TODO Auto-generated constructor stub
		this.uid = uid;
		this.index = index;
		this.name = null;
//		this.act = t;
	}
	private int uid;
	/** 桌位*/
	private int index;
	
	private PlayerStatu statu = PlayerStatu.alive;

	private String name;
	
	private Actor actor;
	
	private boolean Online;
	
	/** 投票座位*/
	private int voteIndex;
	
//	/** 角色类*/
//	private T act;
//	
//	public T getAct() {
//		return act;
//	}
//
//	public void setAct(T act) {
//		this.act = act;
//	}
	
//	private 
	
	public int getVoteIndex() {
		return voteIndex;
	}

	public void setVoteIndex(int voteIndex) {
		this.voteIndex = voteIndex;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOnline() {
		return Online;
	}

	public void setOnline(boolean online) {
		Online = online;
	}
	
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	public PlayerStatu getStatu() {
		return statu;
	}

	public void setStatu(PlayerStatu statu) {
		this.statu = statu;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
