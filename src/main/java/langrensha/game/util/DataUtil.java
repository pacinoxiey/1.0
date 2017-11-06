package langrensha.game.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DataUtil {
	// 移除指定数组元素
	public static Object[] arRemove(Object[] objects, int index, Object obj) {
		if (obj == null) {
			// 按照下标删除
			if (objects.length < index) {
				return null;
			} else {
				objects[index] = objects[objects.length - 1];
			}
		} else {
			// 按数据删除
		}
		return null;
	}

	/**
	 * 使用HashMap提高性能
	 * 
	 * @param a
	 * @return
	 */
	public static <T> int getMostFrequent(T a[]) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int result = 0;
		int size = a.length;

		HashMap<T, LinkedList<T>> severalMap = new HashMap<>();

		for (int i = 0; i < size; i++) {
			T t = a[i];
			// 以元素本身为键，元素分配到的LinkedList为值
			if (severalMap.get(t) != null) {
				severalMap.get(t).add(t);
			} else {
				LinkedList<T> temp = new LinkedList<T>();
				temp.add(t);
				severalMap.put(t, temp);
			}
		}
		// 指向长度最大的集合
		LinkedList<T> largestList = null;
		// 找到元素最多的集合
		for (LinkedList<T> tempList : severalMap.values()) {
			if (largestList == null) {
				largestList = tempList;
				continue;
			}
			if (tempList.size() > largestList.size()) {
				largestList = tempList;
			}
			result = largestList.size();
		}
		return result;
	}

	/**
	 * 返回得到最多票数玩家的座位号
	 * 
	 * @param votes 所有得票
	 * @return
	 */
	public static List<Integer> getMostVote(List<Integer> votes) {

		if (votes.size() == 0 || votes == null) {
			return null;
		}
		HashMap<Integer, LinkedList<Integer>> voteItem = new HashMap<>();

		for (int i = 0; i < votes.size(); i++) {
			int item = votes.get(i);
			if (voteItem.get(item) != null) {	
				voteItem.get(item).add(item);
			} else {
				LinkedList<Integer> temp = new LinkedList<Integer>();
				temp.add(item);
				voteItem.put(item, temp);
			}
		}
		// 指向长度最大的集合
		LinkedList<Integer> largestList = null;

		for (LinkedList<Integer> tempList : voteItem.values()) {
			if (largestList == null) {
				largestList = tempList;
			} else if (largestList.size() < tempList.size()) {
				largestList = tempList;
			}
		}
		List<Integer> result = new ArrayList<>();
		// result.add(largestList.get(0));
		// Iterator<Integer> iterator = result.iterator();
		// 判断是否有相同的最高票数
		for (LinkedList<Integer> tempList : voteItem.values()) {
			if (largestList.size() == tempList.size()) {
				result.add(tempList.get(0));
			}
		}
		return result;
	}

	/**
	 * 投票工具类
	 * 
	 * @param voteMap
	 *            Map<得票玩家 , List<投票玩家>>
	 * @return
	 */
	public static List<Integer> getMaxVote(Map<Integer, List<Integer>> voteMap) {

		if (voteMap.size() == 0 || voteMap == null) {
			return null;
		}

		// 指向长度最大的元素
		List<Integer> largestList = null;
		// 结果集
		List<Integer> result = new ArrayList<>();

		for (List<Integer> tempList : voteMap.values()) {
			if (largestList == null) {
				largestList = tempList;
			} else if (largestList.size() < tempList.size()) {
				largestList = tempList;
			}
		}

		// 判断是否有相同的最高票数
		for (Entry<Integer, List<Integer>> entry : voteMap.entrySet()) {
			if (largestList.size() == entry.getValue().size()) {
				result.add(entry.getKey());
			}
		}

		return result;
	}
}
