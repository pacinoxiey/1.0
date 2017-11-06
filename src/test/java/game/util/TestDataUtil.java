package game.util;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import langrensha.game.util.DataUtil;

public class TestDataUtil {

	@Test
	public void testGetMostVote() {
//		fail("Not yet implemented");
//		int[] is = {1,1,2,3,4,4};
		List<Integer> is = new ArrayList<>();
		is.add(1);
		is.add(2);
		is.add(1);
		System.out.println(DataUtil.getMostVote(is));
	}

}
