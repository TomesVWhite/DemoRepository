package com.tomes.algorithm;

/**获取目标字符串在源字符串第一次出现的下标
 * 针对https://leetcode.com/problems/implement-strstr/description/的题目
 * @author Tmes
 *
 */
public class ImplementstrStr {

	public static void main(String[] args) {

		int strStr = getStrIndex("as","");
		System.out.println(strStr);
	}
	
	/**同样实现，别人的方法
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr(String haystack, String needle) {
        if(haystack==null||needle==null)
            return -1;
        if("".equals(needle))
            return 0;
        int len1=haystack.length();
        int len2=needle.length();

        int i=0,j=0;
        while(i<len1&&j<len2){
            if(haystack.charAt(i)==needle.charAt(j)){
                if(j==len2-1)
                    return i-j;
                i++;
                j++;
            }
            else{
                i=i-j+1;
                j=0;
            }
        }
        return -1;
    }
	
	/**获取目标字符串在源字符串第一次出现的下标
	 * @param srcString	要检出的源字符串
	 * @param inflater	目标字符串
	 * @return	目标字符串在源字符串第一次出现的下标
	 */
	public static int getStrIndex(String srcString, String inflater){
		int i=0;
		int j=0;
		if(srcString==null||inflater==null||inflater.length()>srcString.length()){
			return -1;
		}
		if("".equals(inflater)){
			return 0;
		}
		while(i<srcString.length()&&j<inflater.length()){
			if(srcString.charAt(i)==inflater.charAt(j)){
				if(inflater.length()==j+1){
					return i-j;
				}
				i++;
				j++;
			}else {
				i++;
				j=0;
			}
		}
		return -1;
	}
}
