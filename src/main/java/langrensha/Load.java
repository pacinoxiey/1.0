package langrensha;

import java.util.ArrayList;
import java.util.List;

import langrensha.game.base.ActorPlayer;
import langrensha.game.base.Player;
import langrensha.game.base.Room;
import langrensha.game.data.Actor;

public class Load {
	public static void main(String[] args) {
		
		Player p1 = new Player(111, 1);
		Player p2 = new Player(111, 2);
		Player p3 = new Player(111, 3);
		Player p4 = new Player(111, 4);
		Player p5 = new Player(111, 5);
		Player p6 = new Player(111, 6);
		List<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p6);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		players.add(p5);
		Actor[] actors = {Actor.werwolf,Actor.villager,Actor.witch,Actor.prophet};
		Room room = new Room(11, "langrensha", null,players,actors);
//		
		
		room.lrs();
	}
	
	
}
