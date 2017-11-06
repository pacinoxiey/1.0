package langrensha.game.hand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import langrensha.game.util.DataUtil;

public class All {
	/**
	 * 轮流bb然后投票
	 */
	public static int daytime(List<Integer> vote_player, List<Integer> statement_index) {
		
		Scanner line = new Scanner(System.in);
		String str = null;
		String[] cmd = null;
		Gson gson = new Gson();
		System.out.println("************发言环节************");
		for (int i = 0; i < statement_index.size(); i++) {
			System.out.println(statement_index.get(i)+"号玩家发言："+line.nextLine());
		}
		System.out.println("投票玩家："+gson.toJson(vote_player)+",cmd:自己的座位号，目标座位号，座位号填0表示不投票to-do");
		
		Map<Integer, List<Integer>> voteMap = new HashMap<>();	//投票数据
		//投票
		for (int j = 0; j < vote_player.size(); j++) {
			
			str = line.nextLine();
			cmd = str.split(",");
			// 存下所有操作，接收完后一次发送到逻辑处理方法
			List<Integer> l = new ArrayList<>();
			if (voteMap.get(Integer.valueOf(cmd[1])) == null) {
				l.add(Integer.valueOf(cmd[0]));
				voteMap.put(Integer.valueOf(cmd[1]), l);
			} else {
//				System.out.println("voteMap:"+gson.toJson(voteMap));
				l = voteMap.get(Integer.valueOf(cmd[1]));
				l.add(Integer.valueOf(cmd[0]));
				voteMap.put(Integer.valueOf(cmd[1]), l);
			}
			/**
			 * 需要验证是否已经投过票了
			 */
			
		}
		//判断重票
		List<Integer> voteResult = DataUtil.getMaxVote(voteMap);
		if(voteResult.size()>1){
			System.out.println("票数最多且同票的人轮流发言，然后投票");
			List<Integer> revote = new ArrayList<>();
			for (Integer integer : voteResult) {
				for (Integer vo : vote_player) {
					if(vo != integer) {
						revote.add(vo);
					}
				}
			}
			daytime(revote,voteResult);
		}else {
			return voteResult.get(0);	//返回唯一
		}
		return voteResult.get(0);
	}
}
