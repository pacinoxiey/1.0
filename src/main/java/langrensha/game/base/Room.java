package langrensha.game.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import langrensha.game.Center;
import langrensha.game.data.Actor;
import langrensha.game.data.ActorSkill;
import langrensha.game.data.PlayerStatu;
import langrensha.game.data.RoomLevel;
import langrensha.game.data.Time;
import langrensha.game.hand.All;
import langrensha.game.hand.Guard;
import langrensha.game.hand.Prophet;
import langrensha.game.hand.Werwolf;
import langrensha.game.hand.Witch;
import langrensha.game.util.RoomUtil;

/**
 * 房间类
 * 
 * @author 10040 包含房间内的所有数据但不包括游戏方法
 */
public class Room {
	public Room(int roomId, String roomName, RoomLevel joinLevel, List<Player> players, Actor[] actor) {
		// TODO 初始化房间，id，name
		this.roomId = roomId;
		this.roomName = roomName;

		if (joinLevel == null) {
			this.joinLevel = RoomLevel.civilian;
		} else {
			this.joinLevel = joinLevel;
		}

		this.players = players;
		this.insideOfRoomActors = actor;
	}

	private int roomId;

	private String roomName;
	/** 进入等级 */
	private RoomLevel joinLevel;
	/** 角色 */
	private Actor[] insideOfRoomActors;
	/** 现有玩家 */
	private int current_num = 0;
	/** 最大玩家数量 */
	private int max_num = 20;
	/** 玩家列表 */
	private List<Player> players = new ArrayList<>();
	/** 一天的回合 */
	private Time time = Time.night;
	/** 第几天 */
	private int day = 1;
	/** 当前回合的角色 */
	private Actor onActor = null;
	/** 投票集合 */
	// private List<Vote> votes = new ArrayList<>();
	/** 这个回合的玩家-持有的操作 */
	private Map<Integer, List<ActorSkill>> as = new HashMap<>();
	/** 投票map <target_index, index> */
	private Map<Integer, List<Integer>> voteMap = new HashMap<>();
	/** 存活玩家座位 */
	private List<Integer> alive_index = new ArrayList<>();
	/** 回合信息 */
	private Round round = new Round();
	/** 狼人投票结果 */
	private int werwolf_voteResult = -1;
	/** 所有人投票结果 */
	private int all_voteResult = -1;
	/** 守卫目标 */
	private int guard_target;
	/** 解药数量 */
	private int antidote = 1;
	/** 解药目标 */
	private int antidote_target;
	/** 毒药数量 */
	private int poison = 1;
	/** 毒药目标 */
	private int poison_target;

	public void lrs() {
		do {
			/**
			 * round onactor as alive_index 所有的环节，将数据收集完整之后再传入工具类获取结果 封装成entity
			 */
			start();
			// 根据角色信息进入专门的方法
			switch (onActor) {

			case werwolf:
				System.out.println("----------------第" + day + "天-------------------");
				// 狼人杀人回合
				werwolf_voteResult = Werwolf.vote(round.getPlayers(), alive_index);
				System.out.println("狼人投票结果：" + werwolf_voteResult);
				break;
			case guard:
				// 守卫回合
				guard_target = Guard.defend(round.getPlayers().get(0), guard_target, alive_index);
				System.out.println("守卫目标：" + guard_target);
				break;
			case prophet:
				// 预言家回合
				Prophet.verify(round.getPlayers().get(0), players);
				break;
			case witch:
				// 女巫回合,传入解药跟毒药
				Map<ActorSkill, String> map = Witch.witch_do(round.getPlayers().get(0), alive_index, werwolf_voteResult,
						antidote, poison);
				if (map.get(ActorSkill.antidote).equals("used")) {
					antidote -= 1;
					antidote_target = werwolf_voteResult;
					werwolf_voteResult = -1; // 没有人死亡
				} else {
					p_death(werwolf_voteResult);
					System.err.println("昨晚" + werwolf_voteResult + "号玩家死亡");
				}
				if (!map.get(ActorSkill.poison).equals("intact")) {// 毒药被使用
					poison_target = Integer.valueOf(map.get(ActorSkill.poison));
					poison -= 1;
					p_death(poison_target);
					System.out.println(poison_target + "号玩家死亡，下面开始轮流发言");
				} else {
					if (map.get(ActorSkill.antidote).equals("used")) {
						System.out.println("天亮了，昨晚平安夜，没有玩家死亡，请开始轮流发言-----");
					}
				}
				break;
			case all:
				all_voteResult = All.daytime(alive_index, alive_index);
				p_death(all_voteResult);
				System.out.println(all_voteResult + "号玩家死亡");
			default:
				break;
			}

		} while (RoomUtil.over(players));
	}

	/**
	 * 回合初始化
	 */
	public void start() {

		Gson gson = new Gson();
		// 初始化回合信息
		round = Center.roundCenter(this.insideOfRoomActors, this.onActor, this.players);

		this.onActor = round.getActor(); // 当前回合
		// 回合玩家
		as.clear();

		for (int i = 0; i < round.getPlayers().size(); i++) {
			as.put(round.getPlayers().get(i), round.getSkills());
		}
		// 当前存活的玩家
		alive_index.clear();

		for (Player p : players) {
			if (p.getStatu() == PlayerStatu.alive) {
				alive_index.add(p.getIndex());
			}
		}
		System.out.println(
				"天气:" + this.time + "\r\n当前回合:" + round.getActor() + "\r\n持有的操作选项：" + gson.toJson(round.getSkills()));
		/** 说明文字 */
		System.out.println("睁眼玩家:" + gson.toJson(as));

	}

	public void p_death(int target) {
		for (Player p : players) {
			if (p.getIndex() == target) {
				p.setStatu(PlayerStatu.death);
			}
		}
	}

	/**
	 * 清理这个回合的玩家的操作选项
	 * 
	 * @param index
	 * @param skill
	 */
	public void removeSkill(int index, ActorSkill skill) {

		List<ActorSkill> aSkills = as.get(index);

		for (int i = 0; i < aSkills.size(); i++) {
			if (aSkills.get(i) == skill) {
				aSkills.remove(i);
			}
		}
	}

	/** 考虑将在外部操作会修的数据放到一个类中，统一全部修改 */
	public void conntroller(String str) {
		// 循环请求入口，先内部打印回合数据
		Gson gson = new Gson();
		String[] cmd = str.split(",");/** index, cmd, target_index */
		System.out.println("你的操作指令" + gson.toJson(cmd));
		// 判断是否有这个操作，执行
		for (int i = 0; i < as.size(); i++) {
			List<ActorSkill> xxx = as.get(cmd[0]);
			for (ActorSkill actorSkill : xxx) {
				if (actorSkill.toString() == cmd[1]) {
					/** 需要一个对各种操作的mgr */
					skillController(Integer.valueOf(cmd[0]), ActorSkill.valueOf(cmd[1]), Integer.valueOf(cmd[2]));

				} else {
					System.out.println("请检查你的操作指令：" + cmd[1] + ",重新输入：");
					Scanner line = new Scanner(System.in);
					conntroller(line.nextLine().toString());
				}
			}
		}
	}

	public void vote(String[] cmd) {
		// 判断是否有这个玩家/操作，执行，当所有玩家执行完后进入下一个回合
		System.out.println("狼人");
		List<ActorSkill> www = as.get(cmd[0]);
		if (www == null) {// 还没到你说话
			System.out.println("还没到你说话");
		} else {
			// for (int i = 0; i < as.size(); i++) { // 是否有多个玩家需要操作
			List<ActorSkill> xxx = as.get(cmd[0]); // 获取到玩家的操作
			for (int j = 0; j < xxx.size(); j++) {
				if (xxx.get(j).toString() == cmd[1]) {
					// 存下所有操作，接收完后一次发送到逻辑处理方法
					List<Integer> indexList = new ArrayList<>();
					if (voteMap.get(Integer.valueOf(cmd[2])) == null) {
						indexList.add(Integer.valueOf(cmd[0]));
						voteMap.put(Integer.valueOf(cmd[2]), indexList);
					} else {
						indexList = voteMap.get(cmd[2]);
						indexList.add(Integer.valueOf(cmd[0]));
						voteMap.put(Integer.valueOf(cmd[2]), indexList);
					}
					skillController(Integer.valueOf(cmd[0]), ActorSkill.valueOf(cmd[1]), Integer.valueOf(cmd[2]));
					// 去掉这个操作，避免重复操作
					xxx.remove(j);
					as.put(Integer.valueOf(cmd[0]), xxx);
				}
			}
			// }
			// 检查当前回合的玩家是否全部执行完毕
		}
	}

	/**
	 * actorSkill的代理入口
	 * 
	 * @param players
	 * @param index
	 *            我的位置
	 * @param skill
	 *            操作
	 * @param target
	 *            目标
	 * @return
	 */
	public Gson skillController(int index, ActorSkill skill, int target) {
		Player p = null;
		for (Player player : this.players) {
			if (player.getIndex() == index) {
				p = player;
			}
		}
		if (p == null) {
			throw new RuntimeException("未找到这个玩家");
		}
		// 数据验证，先省略（技能，目标身份）
		switch (skill) {
		case zibao:
			break;
		case night_vote:

			// this.votes = ActorUtil.vote(index, target, this.votes);
			// System.out.println("");
			break;
		case daytime_vote:

			break;
		case poison:

			break;
		case antidote:

			break;
		case protect:

			break;
		case verify:

			break;
		case statement:

			break;
		case gun:

			break;
		default:
			break;
		}
		removeSkill(index, skill);

		return null;
	}

	/**
	 * 
	 * 需要一个对各种操作的mgr 狼人---------杀人----------记录没有返回值 守卫---------守护----------没有返回值
	 * 预言家--------验人----------返回结果 女巫---------两次操作--救人》》毒人----------没有立即的返回值
	 * 移除已操作的玩家的指令 让玩家输入指令来推动回合发展
	 * 
	 * @param str
	 */

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getCurrent_num() {
		return current_num;
	}

	public void setCurrent_num(int current_num) {
		this.current_num = current_num;
	}

	public int getMax_num() {
		return max_num;
	}

	public void setMax_num(int max_num) {
		this.max_num = max_num;
	}
}
