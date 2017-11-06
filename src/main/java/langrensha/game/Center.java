package langrensha.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import langrensha.game.base.Player;
import langrensha.game.base.Room;
import langrensha.game.base.RoomDetail;
import langrensha.game.base.Round;
import langrensha.game.data.Actor;
import langrensha.game.data.ActorSkill;
import langrensha.game.util.ActorUtil;
import langrensha.game.util.RoomUtil;


/**
 * @author 10040
 */
public class Center {
	//房间
	public static List<Room> roomList = new ArrayList<>();
	
//	public static Map<Integer, Room> roomMap = new HashMap<>();
	//房间信息map
	public static List<RoomDetail> roomdetail = new ArrayList<>();
	
//	public static Map<Integer, RoomDetail> roomdetail = new HashMap<>();
	
//	public static Map<Integer, Room> getRoomMap() {
//		
//		return roomMap;
//	}
//	
//	public static Map<Integer, RoomDetail> getRoomDetail() {
//		
//		return roomdetail;
//	}
	/**
	 * 此方法用于判断游戏进行的回合信息
	 * 
	 * @return
	 */
	public static Round roundCenter(Actor[] insideOfRoomActors, Actor actor, List<Player> players) {
		Round round = new Round();
		// 获取当前回合的角色
		// 角色在当前回合拥有的操作
		// 可操作的玩家列表
		Actor actor2 = RoomUtil.nextActor(insideOfRoomActors, actor);
		round.setActor(actor2);
		round.setSkills(ActorUtil.getActHandle(actor2));
		round.setIndex(RoomUtil.getActionPlayers(players, actor2));
		return round;
	}

	/**
	 * actorSkill的代理入口
	 * 
	 * @param players
	 * @param index   我的位置
	 * @param skill    操作
	 * @param target   目标
	 * @return
	 */
	public static Object skillController(Player[] players, int index, ActorSkill skill, int target) {
		Player p = null;
		for (Player player : players) {
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

		return null;
	}
}
