package game.util;

import static org.junit.Assert.*;

import org.junit.Test;

import langrensha.game.data.Actor;
import langrensha.game.util.RoomUtil;


public class TestRoomUtil {
	public static RoomUtil roomUtil = new RoomUtil();

	@Test
	public void testAct() {
		fail("Not yet implemented");
	}

	@Test
	public void testArrFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testName() {
		fail("Not yet implemented");
	}

	@Test
	public void testNextActor() {
		Actor[] roomActor = new Actor[] { Actor.huntsman, Actor.villager, Actor.werwolf, Actor.prophet };
		System.out.println(roomUtil.nextActor(roomActor, Actor.werwolf));
		// fail("Not yet implemented");
	}

	@Test
	public void getNextActor() {
		System.out.println(roomUtil.getNextActor(Actor.werwolf));
	}
}
