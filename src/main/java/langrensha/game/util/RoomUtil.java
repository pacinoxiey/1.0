package langrensha.game.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import langrensha.game.base.ActorPlayer;
import langrensha.game.base.Player;
import langrensha.game.base.Round;
import langrensha.game.base.Static;
import langrensha.game.data.Actor;
import langrensha.game.data.PlayerStatu;
import langrensha.game.data.RoomType;
import langrensha.game.data.Time;

public class RoomUtil {
	/**
	 * 初始化玩家身份
	 * 
	 * @param roomType
	 * @param players
	 * @return
	 */
	public static ActorPlayer initActor(RoomType roomType, List<Player> players, Actor[] actArr) {
		ActorPlayer actorPlayers = new ActorPlayer();
		Actor[] actors = null;
		Integer[] indexArr = null;
		if (roomType == RoomType.six) {
			actors = Static.ACTOR_SIX;
			indexArr = new Integer[6];
		} else if (roomType == RoomType.nine) {
			actors = Static.ACTOR_NINE;
			indexArr = new Integer[9];
		} else if (roomType == RoomType.twelve) {
			actors = Static.ACTOR_TWELVE;
			indexArr = new Integer[12];
		}

		for (int i = 0; i < players.size(); i++) {
			int index;
			do {
				index = (int) Math.floor(Math.random() * actors.length);
			} while (ArrFind(indexArr, index));

			Actor ac = actors[index];
			players.get(i).setActor(ac);
			indexArr[i] = index;
		}
		actorPlayers.setActors(actors);
		actorPlayers.setPlayers(players);
		return actorPlayers;
	}

	public static boolean ArrFind(Object[] obj, Object val) {
		boolean result = false;
		for (Object object : obj) {
			if (object == val) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 回合选择器 当前玩家结束操作后，返回下一个操作的玩家的信息
	 * 
	 * @param actors
	 * @param timeRound
	 * @param index
	 * @param actorRound
	 * @return
	 */
	public static Round name(Actor[] actors, Player[] players, Player player, Time time, Integer round) {
		Actor nextActor;
		Round roundDetail = new Round();

		List<Player> pList = new ArrayList<Player>();
		if (round == 0) {
			/*
			 * 游戏开始 time firstActor
			 */
			for (Player p : players) {
				if (p.getActor() == Static.ACTION_ORDER[0]) {
					pList.add(p);
				}
			}
			// roundDetail.setPlayer(pList);???
		} else {
			for (int i = 0; i < Static.ACTION_ORDER.length; i++) {
				if (player.getActor() == Static.ACTION_ORDER[i]) {
					if (i < Static.ACTION_ORDER.length - 1) {
						nextActor = Static.ACTION_ORDER[i + 1];
					} else {
						nextActor = Static.ACTION_ORDER[0];
					}

				} else {
					System.out.println("未知的角色");
				}
			}
		}

		return null;
	}

	/**
	 * 拿出房间中的下一个执行角色
	 * 
	 * @param roomActor
	 * @param onActor
	 * @return
	 */
	public static Actor nextActor(Actor[] roomActor, Actor onActor) {
		Actor nextActor = null;
		if (onActor == null) {
			System.out.println("*********欢迎来到狼人杀*********");
			return Static.ACTION_ORDER[0];
		}
		
		boolean flag = true;
		nextActor = getNextActor(onActor);
		if(nextActor==Actor.all) {
			
		}else {
			do {
				for (int i = 0; i < roomActor.length; i++) {
					if (roomActor[i] == nextActor) {
						onActor = nextActor;
						flag = false;
						break;
					}
				}
				if (flag == true) {
					nextActor = getNextActor(nextActor);
				} else {//？
					break;
				}
			} while (flag);
		}

		return nextActor;
	}

	/**
	 * 获取此回合的玩家列表
	 * 
	 * @param players
	 * @param actor
	 * @returns
	 */
	public static List<Integer> getActionPlayers(List<Player> players, Actor actor) {
		List<Player> pList = new ArrayList<>();
		// boolean flag = true;
		// do {
		List<Integer> ll = new ArrayList<>();
		if (actor == Actor.all) {
			//放入全部存活的玩家
			for (Player p : players) {
				if (p.getStatu() == PlayerStatu.alive) {
					pList.add(p);
					ll.add(p.getIndex());
					// flag = false;
				}
			}
		}else {
			for (Player p : players) {
				if (p.getActor() == actor && p.getStatu() == PlayerStatu.alive) {
					pList.add(p);
					ll.add(p.getIndex());
					// flag = false;
				}
			}
		}
		
		// if (flag==true) {
		// actor = getNextActor(actor);
		// }
		// } while (true);
		return ll;
	}

	/**
	 * 下一回合的角色
	 * 
	 * @param actor
	 * @return
	 */
	public static Actor getNextActor(Actor actor) {

		for (int i = 0; i < Static.ACTION_ORDER.length; i++) {
			if (actor == Static.ACTION_ORDER[i]) {
				if (i == Static.ACTION_ORDER.length - 1) {
					actor = Static.ACTION_ORDER[0];
				} else {
					actor = Static.ACTION_ORDER[i + 1];
				}
				break;
			}
		}
		return actor;
	}

	/**
	 * 狼人---------杀人----------记录没有返回值 守卫---------守护----------没有返回值
	 * 预言家--------验人----------返回结果 女巫---------两次操作--救人》》毒人----------没有立即的返回值
	 * 
	 * @return
	 */
	public static Map<String, String> name() {

		return null;
	}

	/**
	 * 判断游戏是否结束
	 * 
	 * @return
	 */
	public static boolean over(List<Player> players) {
		int werwolf_count = 0;
		int villager_count = 0;
		int deity_count = 0;
		Gson gson = new Gson();
		for (Player p : players) {
			if (p.getStatu() == PlayerStatu.alive) {
				if (p.getActor() == Actor.werwolf) {
					werwolf_count += 1;
				} else if (p.getActor() == Actor.villager) {
					villager_count += 1;
				} else {
					deity_count += 1;
				}
			}
		}
		if (werwolf_count == 0) {
			System.out.println("好人阵营获胜");
			System.out.println(gson.toJson(players));
			return false;
		} else if (villager_count == 0 || deity_count == 0) {
			System.out.println("狼人阵营获胜");
			System.out.println(gson.toJson(players));
			return false;
		} else {
			System.out.println("未分出胜负，游戏继续...");
			return true;
		}
	}
	
}
