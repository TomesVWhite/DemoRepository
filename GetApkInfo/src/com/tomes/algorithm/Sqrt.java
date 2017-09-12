package com.tomes.algorithm;

/**对x求平方根
 * 针对leetcode 69 https://leetcode.com/problems/sqrtx
 * @author Tomes
 *
 */
public class Sqrt {

	public static void main(String[] args) {
		System.out.println(mySqrt(2147483647));
	}

	/**对x求平方根
	 * @param x	需要计算平方根的数
	 * @return	平方根的整数位
	 */
	public static int mySqrt(int x) {
		for (long i = 0; i <= x / 2 + 1; i++) {
			if (i * i > x) {
				x=(int)(i - 1);
				break;
			} else if (i * i == x) {
				x=(int)i;
				break;
			}
		}
		return x;
	}
	
	/**该方法使用牛顿迭代法公式求解
	 * @param x
	 * @return
	 */
	public int sqrt(int x) {
	    if (x == 0) return 0;
	    double last = 0;
	    double res = 1;
	    while (res != last)
	    {
	        last = res;
	        res = (res + x / res) / 2;
	    }
	    return (int)res;
	}
	
	public double sqrt(double x) {
	    if (x == 0) return 0;
	    double last = 0.0;
	    double res = 1.0;
	    while (res != last)
	    {
	        last = res;
	        res = (res + x / res) / 2;
	    }
	    return res;
	}
}
