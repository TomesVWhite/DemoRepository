package com.tomes.properties;

/**解费波拉契数列,其中使用m3()公式求解效率最高。
 * @author Tomes
 *
 */
public class ClimbingStairs {
	public static void main(String[] args) {
		// int a = m1(4);
		long start=System.currentTimeMillis();
		int a1 = m3(1000000);
		System.out.println(a1);
		System.out.println(System.currentTimeMillis()-start);
	}

	/**
	 * 使用递归求解费波拉契数列
	 * 
	 * @param n
	 *            费波拉契数列第n位
	 * @return 费波拉契数列第n位的值
	 */
	private static int m1(int n) {

		switch (n) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 2;
		}
		return m1(n - 1) + m1(n - 2);
	}

	/**使用循环解费波拉契数列
	 * @param n	费波拉契数列第n位
	 * @return	费波拉契数列第n位的值
	 */
	private static int m2(int n) {
		int sum = 0;
		int t1=0;
		int t2=1;
		for (int i = 1; i < n+1; i++) {
			sum=t1+t2;
			t1=t2;
			t2=sum;
		}
		return sum;
	}
	
	/**使用公式解费波拉契数列	
	 * @param n	费波拉契数列第n位
	 * @return	费波拉契数列第n位的值
	 */
    public static int m3(int n) {
        double s1 = Math.sqrt(5);
        double s2 = (Math.pow((1 + s1) / 2, n + 1) + Math.pow((1 - s1) / 2,
                n + 1)) / s1 + 0.5;
        int result = (int) Math.floor(s2);
        return result;
    }
}
