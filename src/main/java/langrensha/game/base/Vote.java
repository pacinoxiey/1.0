package langrensha.game.base;

/**
 * 投票类
 * @author 10040
 *
 */

import java.util.List;

public class Vote {
	private int target_index;

	private List<Integer> vote_players;

	public int getTarget_index() {
		return target_index;
	}

	public void setTarget_index(int target_index) {
		this.target_index = target_index;
	}

	public List<Integer> getVote_players() {
		return vote_players;
	}

	public void setVote_players(List<Integer> vote_players) {
		this.vote_players = vote_players;
	}

}
