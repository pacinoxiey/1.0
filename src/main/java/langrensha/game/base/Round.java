package langrensha.game.base;

import java.util.ArrayList;
import java.util.List;

import langrensha.game.data.Actor;
import langrensha.game.data.ActorSkill;

/**
 * 回合信息
 * 
 * @author 10040
 */

public class Round {
	
	/** 当前回合的角色*/
	private Actor actor;
	/** 拥有的操作*/
	private List<ActorSkill> skills = new ArrayList<>();
	/** 可操作的玩家座位号*/
	private List<Integer> players =  new ArrayList<>();
	
	
	public List<ActorSkill> getSkills() {
		return skills;
	}

	public void setSkills(List<ActorSkill> skills) {
		this.skills = skills;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public List<Integer> getPlayers() {
		return players;
	}

	public void setIndex(List<Integer> index) {
		this.players = index;
	}

}
