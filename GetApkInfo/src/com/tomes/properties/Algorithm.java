package com.tomes.properties;

/**该类为获取SingleNo
 * @author Administrator
 *
 */
public class Algorithm extends Demo {

	public static void main(String[] args) {
		int[] a = { 6, 5, 5, 5 };
		System.out.println(findSingle2(a));
	}

	/**
	 * 数组当中除了一个元素外，其他元素都出现两次。找到这个唯一的元素。
	 * 
	 * @param array
	 *            需要进行判断的数组
	 * @return	这个唯一的元素。
	 */
	public static int findSingle(int[] array) {
		int temp = 0;
		for (int i = 0; i < array.length; i++)
			temp ^= array[i];
		return temp;
	}

	/**数组中其余元素数量为3，找到这个唯一的元素。
	 * @param array	需要进行判断的数组
	 * @return	这个唯一的元素。
	 */
	public static int findSingle2(int[] array) {
		int[] count = new int[32];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < 32; j++) {
				int t = array[i];
				int a = array[i] >> j;
				int b = a & 1;
				count[j] += b;// 获得最后一位数字
				count[j] %= 3;
				System.out.println(count[j]);
			}
			System.out.println("---");

		}
		int result = 0;
		// 数字恢复
		for (int i = 0; i < 32; i++) {
			int k = count[i];
			int v = count[i] << i;
			result += (count[i] << i);
			System.out.println("--->" + result);
		}
		return result;
	}


	/**数组中其余元素数量为num，找到这个唯一的元素。
	 * @param array	需要进行判断的数组
	 * @param num	重复出现次数
	 * @return	这个唯一的元素。
	 */
	public static int findSingle3(int[] array, int num) {
		int[] count = new int[32];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < 32; j++) {
				count[j] += (array[i] >> j) & 1;// 获得最后一位数字
				count[j] %= num;// 此处为num
			}
		}
		int result = 0;
		// 数字恢复
		for (int i = 0; i < 32; i++) {
			result += (count[i] << i);
		}
		return result;
	}
}
