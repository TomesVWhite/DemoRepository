package com.tomes.properties;

/**该类作用在于将数字顺序颠倒（若原数0结尾，则颠倒后将0省去）
 * @author Tomes
 *
 */
public class ReverseInteger {

	public static void main(String[] args) {
//		int reverseInt = simpleReverse(-95720);
		int reverseInt = simpleReverseInt(-957240);
		System.out.println(reverseInt);
	}
	
	/**简单方法调用，不涉及到算法实现
	 * @param srcInt 调换前的数
	 * @return	调换后的数
	 */
	public static int simpleReverse(int srcInt){
		String tmp=String.valueOf(Math.abs(srcInt));
		StringBuffer reverse = new StringBuffer(tmp).reverse();
		String result = reverse.toString();
		if(srcInt>=0){
			return Integer.valueOf(result);
		}else {
			return -Integer.valueOf(result);
		}
	}
	
	/**简单方法调用
	 * @param srcInt 调换前的数
	 * @return 调换后的数（该数不考虑超过int有效范围，可能造成反转数后溢出）
	 */
	public static int simpleReverseInt(int srcInt){
		int num=Math.abs(srcInt);
		int end=0;
		int mark=srcInt>=0?1:(-1);
		while (num>0) {
			end=(end*10)+(num%10);
			num=num/10;
		}
		return end*mark;
		
	}
	
	/**简单方法调用
	 * @param srcInt 调换前的数(不用考虑其超过int的有效范围)
	 * @return 调换后的数
	 */
	public static long simpleReverseInt(long srcInt){
		long num=Math.abs(srcInt);
		long end=0;
		long mark=srcInt>=0?1:(-1);
		while (num>0) {
			end=(end*10)+(num%10);
			num=num/10;
		}
		return end*mark;
		
	}
	
	/*
     * 反转整型数,考虑溢出的情况,不考虑输入参数溢出的情况，因为输入参数越界时，编译不通过
     */
    public static int reverse(int n) {

        int result = 0;
        int flag = (n >= 0) ? 1 : -1;
        // 如果参数等于 Integer.MIN_VALUE 的值（即能够表示的最小负 int 值），那么结果与该值相同且为负。
        int num = Math.abs(n);
        if (num == Integer.MIN_VALUE)
            return -1;
        boolean check = true;
        while (num > 0) {
            result = result * 10 + num % 10;
            num /= 10;
            check = result > Integer.MAX_VALUE / 10
                    || (result == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10);
            // 越界判定条件：num>int极大值/10,或者num等于最大值/10并且剩余最高位数字大于极大值除以10的余数
            if (check)
                return -1;

        }
        return result * flag;
    }
    
    /*
     * 利用捕获异常来处理溢出的情况，这里用到了jdk的字符反转方法
     */
    public static int simpleReverseBetter(int n) {
        try {
            int result = 0;
            int flag = (n >= 0) ? 1 : -1;
            int num = Math.abs(n);
            String str = String.valueOf(num);
            str = new StringBuffer(str).reverse().toString();
            result = Integer.valueOf(str);
            return result * flag;
        } catch (NumberFormatException e) {
            // 但该字符串不能转换为适当格式时，抛出该异常，注意传入数字是负数时，也会发生转化异常，所以需要绝对值处理
            return -1;
        }

    }
}
