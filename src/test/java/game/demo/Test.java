package game.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import langrensha.game.data.ActorSkill;


public class Test {
	public static void main(String[] args) {
		test1();
	}
	public static void test1() {
		List<ActorSkill> xx = new ArrayList<>();
		xx.add(ActorSkill.night_vote);
		System.out.println(xx.remove(0));
	}
	
	public void test2() {
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<>();
		map.put("key", "value");
		System.out.println(gson.toJson(map));
	}
}
