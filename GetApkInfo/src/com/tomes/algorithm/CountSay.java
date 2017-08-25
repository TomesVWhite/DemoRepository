package com.tomes.algorithm;

/**count and say
 * 针对：https://leetcode.com/problems/count-and-say/description/
 * @author Tomes
 *
 */
public class CountSay {
	public static void main(String[] args) {
		System.out.println(countAndSay(4));
		
	}

	public  static String countAndSay(int n) {
        if(n == 1) {
            return 1+"";
        }
        String result = "1";
        for(int i=2;i<=n;i++) {
            result = say(result);
        }
        return result;
    }

    public static String say(String s) {
        String result = "";
        //某个数字（连续）出现的次数（若中间被打断则被置为0，然后记录下个数字出现的次数）
        int count = 0;
        //当前数字的值
        char currentNo = '0';
        for(int i=0;i<s.length();i++) {
        	currentNo = s.charAt(i);
        	//如果之前出现过个某个数，就用当前数和之前的进行比较，如果两个数字相同，该数出现次数+1；
            if(count !=0) {
            	//如果当前数字和前一位数不同，则结算前一位数 即：个数+前一个数字，然后重置count的值；
                if(s.charAt(i-1) != currentNo) {
                    result = result + count + s.charAt(i-1);
                    count = 0;
                }
            }
            count++;
        }
        result = result + count + currentNo;
        return result;
    }
}
