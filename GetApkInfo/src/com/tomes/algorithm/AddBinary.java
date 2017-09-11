package com.tomes.algorithm;

/**二进制数相加
 * 对应leetcode 67 https://leetcode.com/problems/add-binary/description/
 * 
 * @author Tomes
 * 
 */
public class AddBinary {

	public static void main(String[] args) {

		System.out.println(addBinary("111", "11"));
	}
	
	/*下面这种写法又巧妙又简洁，用了两个指针分别指向a和b的末尾，然后每次取出一个字符，转为数字，
	若无法取出字符则按0处理，然后定义进位carry，初始化为0，将三者加起来，对2取余即为当前位的数字，
	对2取商即为当前进位的值，记得最后还要判断下carry，如果为1的话，要在结果最前面加上一个1，参见代码如下：*/
	/**二进制数相加
	 * @param a 二进制数a
	 * @param b	二进制数a
	 * @return	二进制数相加后的結果
	 */
	public static String addBinary(String a, String b) {
		int aIndex=a.length()-1;
		int bIndex=b.length()-1;
		int everySum=0;
		int carry=0;
		StringBuilder res=new StringBuilder();
		while (aIndex>=0&&bIndex>=0) {
			//数字-'0'是为了获得之间的相对差值来得到转换成int后的真实数值，具体看数字char对应的数字。这里不详述。
			everySum=(int)(a.charAt(aIndex--)-'0'+b.charAt(bIndex--)-'0'+carry);
			carry=everySum/2;
			res.append(everySum%2);
		}
		while (aIndex>=0) {
			everySum=(int)(a.charAt(aIndex--)-'0'+carry);
			carry=everySum/2;
			res.append(everySum%2);
		}
		while (bIndex>=0) {
			everySum=(int)(b.charAt(bIndex--)-'0'+carry);
			carry=everySum/2;
			res.append(everySum%2);
		}
		if(carry==1){
			res.append(carry);
		}
		return res.reverse().toString();
		
	}
	/**整体思路和上面一样，只是对代码进行了简化
	 * @param a
	 * @param b
	 * @return
	 */
	public static String addBinary1(String a, String b) {
		int aIndex=a.length()-1;
		int bIndex=b.length()-1;
		int everySum=0;
		int carry=0;
		StringBuilder res=new StringBuilder();
		while (aIndex>=0||bIndex>=0) {
			int aR=aIndex>=0?(int)(a.charAt(aIndex--)-'0'):0;
			int bR=bIndex>=0?(int)(b.charAt(bIndex--)-'0'):0;
			everySum=aR+bR+carry;
			carry=everySum/2;
			res.append(everySum%2);
		}
		if(carry>0){
			res.append(carry);
		}
		return res.reverse().toString();
	}


}
