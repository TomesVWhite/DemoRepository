package com.tomes.algorithm;

/**
 * 验证一个数是否是回文数 
 * 原题：https://leetcode.com/problems/palindrome-number/description/
 * @author Tomes
 * 
 */
public class PalindromeNumber {

	public static void main(String[] args) {
		System.out.println(isPalindrome(1));
		System.out.println(130%100);
	}

	/**验证一个数是否是回文数 
	 * @param x 需要验证的数
	 * @return	验证结果
	 */
	public static boolean isPalindrome(int x) {
		if(x<0){
			return false;
		}
		int div=1;
		//取左右数进行对比
		//获取最大除数
		while (x/div>=10) {
			div*=10;
		}
		while(x>0){
			int left=x/div;
			int right=x%10;
			if(left!=right){
				return false;
			}
			x=(x-left*div)/10;
			div/=100;
		}
		return true;

	}

}
