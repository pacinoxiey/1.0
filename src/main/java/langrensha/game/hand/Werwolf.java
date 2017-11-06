package langrensha.game.hand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import langrensha.game.util.DataUtil;

/**
 * 狼人的操作
 * 
 * @author 10040
 */
public class Werwolf {
	/**
	 * 狼人刀人方法
	 * 
	 * @param werwolf
	 *            狼人
	 * @param alives
	 *            存活的玩家
	 * @return
	 */
	public static Integer vote(List<Integer> werwolf, List<Integer> alivePlayers) {
		// 判断是否有这个玩家/操作，执行，当所有玩家执行完后进入下一个回合
		Scanner line = new Scanner(System.in);
		String str = null;
		String[] cmd = null;
		Gson gson = new Gson();
		Map<Integer, List<Integer>> voteMap = new HashMap<>(); // 投票数据

		System.err.println("狼人回合，场上剩余玩家：" + gson.toJson(alivePlayers));
		// 循环等待所有人的操作
		for (int j = 0; j < werwolf.size(); j++) {
			System.out.println("剩余狼人玩家：" + gson.toJson(werwolf) + ",cmd:自己的座位号，目标座位号，座位号填0表示不投票to-do");
			str = line.nextLine();
			cmd = str.split(",");
			// 存下所有操作，接收完后一次发送到逻辑处理方法
			List<Integer> l = new ArrayList<>();
			if (voteMap.get(Integer.valueOf(cmd[1])) == null) {
				l.add(Integer.valueOf(cmd[0]));
				voteMap.put(Integer.valueOf(cmd[1]), l);
			} else {
				// System.out.println("voteMap:"+gson.toJson(voteMap));
				l = voteMap.get(Integer.valueOf(cmd[1]));
				l.add(Integer.valueOf(cmd[0]));
				voteMap.put(Integer.valueOf(cmd[1]), l);
			}
			/**
			 * 需要验证是否已经投过票了
			 */

		}
		int result = 0;
		// 判断重票
		List<Integer> voteResult = DataUtil.getMaxVote(voteMap);
		if (voteResult.size() > 1) {
			System.out.println("请在最多的目标中重新投票");
			// do {
			//
			// } while (condition);
			result = vote(werwolf, voteResult);
		} else {
			return voteResult.get(0); // 返回唯一
		}
		// 不会执行到这里
		return result;
	}
}
