package langrensha.game.base;

import java.util.List;

import langrensha.game.data.Actor;


public class ActorPlayer {
	private Actor[] actors;
	
	private List<Player> players;

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Actor[] getActors() {
		return actors;
	}

	public void setActors(Actor[] actors) {
		this.actors = actors;
	}

}	
