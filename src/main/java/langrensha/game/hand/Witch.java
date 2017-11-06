package langrensha.game.hand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import langrensha.game.data.ActorSkill;

/**
 * 女巫
 * 
 * @author 10040
 *
 */
public class Witch {
	/**
	 * 
	 * @param witch_index
	 *            女巫玩家座位
	 * @param alivePlayers
	 * @param voteResult
	 *            被毒的玩家 return
	 */
	public static Map<ActorSkill, String> witch_do(int witch_index, List<Integer> alivePlayers, int voteResult,
			int antidote, int poison) {
		System.out.println("女巫回合：使用毒药或者解药");
		Gson gson = new Gson();
		Scanner line = new Scanner(System.in);
		String str = null;
		String[] cmd = null;
		Map<ActorSkill, String> map = new HashMap<>();
		// 先死亡判断
		if (voteResult > 0) {
			System.out.println("昨晚" + voteResult + "号玩家死亡，请决定是否使用解药 yes/no");
			if (antidote > 0) {
				str = line.nextLine();
				System.out.println(str);
				// 使用equals比较值内容
				if (str.equals("yes")) {
					map.put(ActorSkill.antidote, "used");
				} else {
					map.put(ActorSkill.antidote, "intact");
				}
			} else if (antidote == 0) {
				System.out.println("你的解药已用完");
			}

		} else {
			System.out.println("昨晚没有玩家死亡，等待时间结束开始毒药");
		}

		if (poison > 0) {
			System.out.println("你要使用毒药吗？yes/no");
			str = line.nextLine();
			if (str.equals("yes")) {
				System.out.println("请选择你要使用的目标：" + gson.toJson(alivePlayers));
				str = line.nextLine();
				map.put(ActorSkill.poison, str); // 使用毒药的时候，value为目标
			} else {
				map.put(ActorSkill.poison, "intact");
			}
		} else if (poison == 0) {
			System.out.println("你的毒药已用完");
		}

		return map;
	}
}
