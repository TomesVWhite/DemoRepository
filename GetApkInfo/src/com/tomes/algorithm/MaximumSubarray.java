package com.tomes.algorithm;

/**Maximum Subarray 动态规划 最大连续子序列和；具体思路参照http://blog.csdn.net/qq_35559358/article/details/77571787
 * @author Tomes
 *
 */
public class MaximumSubarray {
	
	public static void main(String[] args) {
		
	}
	public int maxSubArray1(int[] nums) {
        int result=Integer.MIN_VALUE;
        int currentSums=0;
        for (int num : nums) {
			currentSums=Math.max(currentSums+num, num);
			result=Math.max(result, currentSums);
        }
         return result;
    }
}
