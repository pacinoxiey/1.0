package langrensha.game.hand;

import java.util.List;
import java.util.Scanner;

public class Guard {
	/**
	 * 
	 * @param guard_index
	 * @param alivePlayers 
	 */
	public static int defend(int guard_index, Integer last_index, List<Integer> alivePlayers) {
		System.out.println("守卫回合：请选择被守护的目标，不能连续两局守护同一个目标");
		Scanner line = new Scanner(System.in);
		String str = null;
		String[] cmd = null;
		str = line.nextLine();
		cmd = str.split(",");
		
		if(Integer.valueOf(cmd[0])==guard_index) {
			if (Integer.valueOf(cmd[1])==last_index) {
				System.out.println("不能连续守护同一个人");
				defend(guard_index, last_index, alivePlayers);
			}
			return Integer.valueOf(cmd[1]);
		}else {
			System.out.println("你不是守卫");
			defend(guard_index, last_index, alivePlayers);
		}
		//不会走到这里
		return 0;
	}
}
