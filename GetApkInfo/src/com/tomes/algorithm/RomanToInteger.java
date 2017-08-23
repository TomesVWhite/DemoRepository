package com.tomes.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转换成Integer https://leetcode.com/problems/roman-to-integer/description/
 * 
 * @author Tomes
 * 
 */
public class RomanToInteger {

	public static void main(String[] args) {
		int romanToInt = romanToInt("IX");
		System.out.println(romanToInt);
	}

	public static int romanToInt(String s) {
		int result=0;
		//1、罗马数字放进map，数字一一对应，
		Map<Character, Integer> romanInteger=new HashMap<Character, Integer>();
		romanInteger.put('I', 1);
		romanInteger.put('V', 5);
		romanInteger.put('X', 10);
		romanInteger.put('L', 50);
		romanInteger.put('C', 100);
		romanInteger.put('D', 500);
		romanInteger.put('M', 1000);
		//2、字符串每个都分割，从map中取出对应的值
		for (int i = 0; i < s.length(); i++) {
			if(i==s.length()-1){
				//如果当前数字是最后一个数字，加上
				result+=romanInteger.get(s.charAt(i));
				continue;
			}
			//之后的数字比它小的话，则加上当前数字
			boolean compare=false;
			for (int j = i+1; j < s.length(); j++) {
				char s1=s.charAt(i);
				char s2=s.charAt(j);
				Integer t1 = romanInteger.get(s.charAt(i));
				Integer t2 = romanInteger.get(s.charAt(j));
				if(romanInteger.get(s.charAt(i))<romanInteger.get(s.charAt(j))){
					compare=true;
				}
			}
			if(!compare){
				result+=romanInteger.get(s.charAt(i));
				continue;
			}
			//除此之外，则减去当前数字
			result-=romanInteger.get(s.charAt(i));
		}
		return result;

	}
}
