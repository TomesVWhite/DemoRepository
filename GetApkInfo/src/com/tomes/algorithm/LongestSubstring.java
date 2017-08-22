package com.tomes.algorithm;

import java.util.Arrays;

/**获取最长不重复字符串的长度
 * 针对https://leetcode.com/problems/longest-substring-without-repeating-
 * characters/description/的问题
 * 
 * @author Tomes
 * 
 */
public class LongestSubstring {

	public static void main(String[] args) {
		int lengthOfLongestSubstring = lengthOfLongestSubstring("abcabcbb");
		System.out.println(lengthOfLongestSubstring);
		// int[] a=new int[3];
		// Arrays.fill(a, -1);
		// System.out.println(a);
	}

	/**获取传入字符串中最长不重复字符串的长度
	 * @param s	传入的字符串
	 * @return	最长不重复字符串的长度
	 */
	public static int lengthOfLongestSubstring(String s) {
		int[] m = new int[256];
		Arrays.fill(m, -1);
		//最长不重复字符串的长度
		int destLenth = 0;
		//不重复字符串的最左侧起始位置
		int startIndex = -1;
		for (int i = 0; i < s.length(); ++i) {
			//根据字符是否重复，来重置不重复字符串的最左侧起始位置
			startIndex = Math.max(startIndex, m[s.charAt(i)]);
			//在对应的字符asc编码位置进行下标标记
			m[s.charAt(i)] = i;
			//已有最大长度（字符重复前的最大长度）和现有的最大长度的比较，然后更新长度
			destLenth = Math.max(destLenth, i - startIndex);
		}
		return destLenth;
	}

	public static int lengthOfLongestSubstring1(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		// visited[char's ASCII] = char's index
		int[] visited = new int[256];
		Arrays.fill(visited, -1);

		int curLen = 1;
		int maxLen = 1;
		int prevIndex = 0;
		visited[s.charAt(0)] = 0;

		for (int i = 1; i < s.length(); i++) {
			prevIndex = visited[s.charAt(i)]; // 之前存储过的index
			// 如果是第一次出现，或者不在当前考虑的字串内 如当访问第二个a时对于第一个a就不在考虑范围
			if (prevIndex == -1 || prevIndex + curLen < i) {
				curLen++; // 在旧字串上增加
			} else { // 如b
				maxLen = Math.max(maxLen, curLen);
				curLen = i - prevIndex; // 建立新的字串
			}
			visited[s.charAt(i)] = i; // 更新
		}
		// 最后一次
		maxLen = Math.max(maxLen, curLen);

		return maxLen;
	}
}
