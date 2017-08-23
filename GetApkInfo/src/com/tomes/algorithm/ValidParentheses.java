package com.tomes.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**验证有效括号对
 * https://leetcode.com/problems/valid-parentheses/description/
 * @author Toems
 *
 */
public class ValidParentheses {

	public static void main(String[] args) {
		System.out.println(isValid("{[}]"));
	}

	
	 public boolean isValid1(String s) {
	        Map<Character, Character> map = new HashMap<Character, Character>();
	        map.put('(',')');
	        map.put('[',']');
	        map.put('{','}');
	        Stack<Character> stk = new Stack<Character>();
	        for(int i = 0; i < s.length(); i++){
	            Character c = s.charAt(i);
	            switch(c){
	                case '(': case '[': case '{':
	                    stk.push(c);
	                    break;
	                case ')': case ']': case '}':
	                    if(stk.isEmpty() || c != map.get(stk.pop())){
	                        return false;
	                    }
	            }
	        }
	        return stk.isEmpty();
	    }
	 
	 /**判断输入的[]{}()是否是有效的
	 * @param s	需要校验的括号字符串
	 * @return	是否有效
	 */
	public static boolean isValid(String s){
		 Stack<Character> mode=new Stack<Character>();
		 for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{'){
				mode.push(s.charAt(i));
			}else {
				if(mode.isEmpty()){
					return false;
				}else if ((s.charAt(i)==')'&&mode.peek()!='(') || (s.charAt(i)==']'&&mode.peek()!='[') || (s.charAt(i)=='}'&&mode.peek()!='{')) {
					return false;
				}
				mode.pop();
			}
		}
		 
		 return mode.isEmpty();
	 }
}
