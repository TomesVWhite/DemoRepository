package com.tomes.properties;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**数组中两个数之和为目标值，找出两个数所在位置
 * @author Tomes
 *
 */
public class TwoSum {
	public static void main(String[] args) {
		int[] nums = { 4, 6, 2, 3, 5 };
		int target = 11;
		int[] twoSum = twoSum(nums, target);
//		int[] twoSum = twoSumFromMap(nums, target);
		Arrays.sort(twoSum);
		for (int i : twoSum) {
			System.out.println(i);
		}
	}

	/**数组中两个数之和为目标值，找出两个数所在位置（起始点为1）
	 * @param nums	需要查找的数组
	 * @param target	需要目标值
	 * @return	两个数所在位置
	 */
	public static int[] twoSum(int[] nums, int target) {
		int len = nums.length;
		int index1 = 0, index2 = 0;
		if (len <= 1)
			return null;
		for (int i = 0; i < len; i++)
			for (int j = i + 1; j < len; j++)
				if (nums[i] + nums[j] == target) {
					index1 = i + 1;
					index2 = j + 1;
					break;
				}
		return new int[] { index1, index2 };
	}

	/**数组中两个数之和为目标值，找出两个数所在位置（起始点为1）
	 * @param nums	需要查找的数组
	 * @param target	需要目标值
	 * @return	两个数所在位置
	 */
	public static int[] twoSumFromMap(int[] nums, int target) {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		int[] result = new int[2];
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				result[0] = map.get(target - nums[i]) + 1;
				result[1] = i + 1;
				return result;
			}
		}
		return null;

	}
}
