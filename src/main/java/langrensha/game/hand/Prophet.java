package langrensha.game.hand;

import java.util.List;
import java.util.Scanner;

import langrensha.game.base.Player;
import langrensha.game.data.Actor;
import langrensha.game.data.PlayerStatu;

public class Prophet {
	/**
	 * 
	 * @param guard_index
	 * @param players
	 * @return 好人返回1，坏人返回2
	 */
	public static int verify(int verify_index, List<Player> players) {
		System.out.println("预言家回合：验人");
		Scanner line = new Scanner(System.in);
		String str = null;
		String[] cmd = null;
		str = line.nextLine();
		cmd = str.split(",");
		if(Integer.valueOf(cmd[0])==verify_index) {
			for (Player p : players) {
				if(p.getIndex()==Integer.valueOf(cmd[1])&&p.getStatu()==PlayerStatu.alive) {
					if(p.getActor()!=Actor.werwolf) {
						System.out.println("玩家"+cmd[1]+"是好人");
						return 1;
					}else {
						System.out.println("玩家"+cmd[1]+"是坏人");
						return 2;
					}
				}
			}
		}else {
			System.out.println("你不是预言家");
		}
		return 0;
	}
}
