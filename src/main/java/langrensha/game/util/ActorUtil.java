package langrensha.game.util;

import java.util.ArrayList;
import java.util.List;

import langrensha.game.base.Static;
import langrensha.game.base.Vote;
import langrensha.game.data.Actor;
import langrensha.game.data.ActorSkill;

public class ActorUtil {
	/**
	 * 返回角色对应的操作
	 * 
	 * @param actor
	 * @return
	 */
	public static List<ActorSkill> getActHandle(Actor actor) {
		if (actor == null) {
			actor = Actor.werwolf;
		}
		ActorSkill[] arr = null;
		List<ActorSkill> list = new ArrayList<>();

		switch (actor) {
		case werwolf:
			arr = Static.werwolf;
			break;
		case guard:
			arr = Static.guard;
			break;
		case prophet:
			arr = Static.prophet;
			break;
		case witch:
			arr = Static.witch;
			break;
		// case villager:
		// return Static.villager;
		case huntsman:
			arr = Static.huntsman;
			break;
		case all:
			arr = Static.all;
			break;
		default:
			System.out.println("未知的神灵");
			break;
		}
		// System.out.println(arr);
		for (ActorSkill actorSkill : arr) {
			list.add(actorSkill);
		}
		return list;
	}

	/**
	 * 投票工具类
	 * 
	 * @param index
	 * @param target_index
	 * @param votes
	 * @return
	 */
	@SuppressWarnings("null")
	public static List<Vote> vote(int index, int target_index, List<Vote> votes) {
		Vote vote = new Vote();
		List<Integer> vote_players = new ArrayList<>();

		for (Vote v : votes) {
			if (v.getTarget_index() == target_index) {
				vote_players = v.getVote_players();
			}
		}

		if (vote_players == null) {
			vote.setTarget_index(target_index);
			vote_players.add(index);
			vote.setVote_players(vote_players);
			votes.add(vote);
		} else {
			vote_players.add(index);
			votes.get(target_index).setVote_players(vote_players);
		}
		return votes;
	}

	// public static int

}
