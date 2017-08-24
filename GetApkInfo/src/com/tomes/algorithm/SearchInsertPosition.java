package com.tomes.algorithm;

/**从有序数组里搜寻目标数组下标，如果数组中没有这个数，该数插入什么位置合适。
 * @author Administrator
 * 
 */
public class SearchInsertPosition {
	
	public static void main(String[] args) {
		int[] a={0};
//		searchInsert(a, 3);
		System.out.println(searchInsert(a, 3));
	}
	public static int searchInsert(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			if(target<=nums[i]){
				return i;
			}
		}
		return nums.length;

	}
	
	/**使用折半查找优化过的搜索方法
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int searchInsert1(int[] nums, int target) {
        if (nums[nums.length-1] < target) return nums.length;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return right;
    }
}
