package langrensha.game.base;

import java.util.ArrayList;
import java.util.List;

public class ActionSkill {

	private int index;

	private List<ActionSkill> akList = new ArrayList<>();

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<ActionSkill> getAkList() {
		return akList;
	}

	public void setAkList(List<ActionSkill> akList) {
		this.akList = akList;
	}

}
