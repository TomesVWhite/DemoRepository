package com.tomes.algorithm;

/**最长共同前缀
 * https://leetcode.com/problems/longest-common-prefix/description/
 * @author Tomes
 *
 */
public class LongestCommonPrefix {

	public static void main(String[] args) {
		String[] src={"abcxxx","abcx","abccsss"};
		System.out.println(longestCommonPrefix(src));
	}
	
	public static String longestCommonPrefix(String[] strs){
		if(strs==null||strs.length==0){
			return "";
		}
		String result="";
		//拿出String[]中最短的String作为标尺进行比较
		String shortest=strs[0];
		for (int i = 1; i < strs.length; i++) {
			if(strs[i].length()<shortest.length()){
				shortest=strs[i];
			}
		}
		//没什么好说的，将每个string相同位置的字符进行比较
		aa:for (int i = 0; i < shortest.length(); i++) {
			for (String content : strs) {
				if(content.charAt(i)!=shortest.charAt(i)){
					break aa;
				}
			}
			result=shortest.substring(0, i+1);
		}
		return result;
		
	}
	
	 public String longestCommonPrefix2(String[] strs) {
	        if (strs == null || strs.length == 0) return "";
	        for (int j = 0; j < strs[0].length(); ++j) {
	            for (int i = 0; i < strs.length - 1; ++i) {
	                if (j >= strs[i].length() || j >= strs[i + 1].length() || strs[i].charAt(j) != strs[i + 1].charAt(j)) {
	                    return strs[i].substring(0, j);   
	                }
	            }
	        }
	        return strs[0];
	    }
}
