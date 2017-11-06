package langrensha.game.base;

import langrensha.game.data.Actor;
import langrensha.game.data.ActorSkill;

public class Static {
	// public Static() {
	// // TODO Auto-generated constructor stub
	// werwolf.add(ActorSkill.protect);
	// werwolf.add(ActorSkill.guo);
	// }
	// 6人局
	public static Actor[] ACTOR_SIX = { Actor.werwolf, Actor.werwolf, Actor.villager, Actor.villager, Actor.prophet,
			Actor.witch };
	// 9人局
	public static Actor[] ACTOR_NINE = { Actor.werwolf, Actor.werwolf, Actor.werwolf, Actor.villager, Actor.villager,
			Actor.villager, Actor.huntsman, Actor.prophet, Actor.witch };
	// 12人局
	public static Actor[] ACTOR_TWELVE = { Actor.werwolf, Actor.werwolf, Actor.werwolf, Actor.werwolf, Actor.villager,
			Actor.villager, Actor.villager, Actor.villager, Actor.huntsman, Actor.guard, Actor.prophet, Actor.witch };
	// 角色执行顺序 狼人，守卫，预言家，女巫，死者，活着的所有人
	public static Actor[] ACTION_ORDER = { Actor.werwolf, Actor.guard, Actor.prophet, Actor.witch, Actor.all };

	/** 角色持有的基本操作 */
	// public static List<ActorSkill> werwolf = new
	// ArrayList<>();,ActorSkill.statement
	public static ActorSkill[] werwolf = { ActorSkill.night_vote };
	public static ActorSkill[] guard = { ActorSkill.protect, ActorSkill.guo };
	public static ActorSkill[] prophet = { ActorSkill.verify };
	public static ActorSkill[] witch = { ActorSkill.antidote, ActorSkill.poison };
	public static ActorSkill[] villager = { ActorSkill.guo };
	public static ActorSkill[] huntsman = { ActorSkill.guo, ActorSkill.gun };
	/** 猎人死后触发其余情况算作平民 */
	public static ActorSkill[] all = { ActorSkill.vote };
	/** 天亮时 */
	public static ActorSkill[] dayTimeActor = { ActorSkill.guo, ActorSkill.statement };
}
