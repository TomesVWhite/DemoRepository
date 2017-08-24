package com.tomes.algorithm;

/**
 * 有序数组去重复
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 * @author Tomes
 * 
 */
public class RemoveDuplicatesSortedArray {

	public static void main(String[] args) {
		int[] nums={1,1,2,2,3};
		System.out.println(removeDuplicates(nums));
	}


	/**有序数组去重复
	 * @param nums	需要去重的有序数组
	 * @return	去重后数组的长度
	 */
	public static int removeDuplicates(int[] nums) {
		if(nums.length==0){
			return 0;
		}
		int len=0;
		int cur=0;
		while (cur<nums.length) {
			if(nums[cur]==nums[len]){
				cur++;
			}else {
				//给数组重排序
				nums[++len]=nums[cur++];
			}
			
		}
		return len+1;
	}
}
