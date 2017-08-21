package com.tomes.properties;

import java.util.ArrayList;
import java.util.List;

/**该类用于获取杨辉三角数
 * @author Tomes
 *
 */
public class PascalTriangle {
	public static void main(String[] args) {
		// List<List<Integer>> m2 = m2(10);
		
		int[][] m1 = m1(10);
		int[] formM1 = formM1(10, 3);
		
	}

	/**获取指定行数的杨辉三角数。
	 * @param rowNo
	 *            杨辉三角行数
	 * @return 包含杨辉三角的二维数组
	 */
	private static int[][] m1(int rowNo) {
		int[][] rows = new int[rowNo][];
		for (int i = 0; i < rows.length; i++) {
			// 给二维数组的列动态创建,其中I的值为（行数-1），j的值为（列数-1），且该行列数等于所在行数
			rows[i] = new int[i + 1];
			for (int j = 0; j <= i; j++) {
				if (i == 0 || j == 0 || j == i) {
					rows[i][j] = 1;
				} else {
					rows[i][j] = rows[i - 1][j - 1] + rows[i - 1][j];
				}
				System.out.print(rows[i][j] + "\t");
			}
			System.out.println();
		}
		return rows;
	}

	/**从杨辉三角中获取第checkNo的数
	 * @param rowNo	杨辉三角行数
	 * @param checkNo	需要获取第checkNo的数
	 * @return
	 */
	private static int[] formM1(int rowNo,int checkNo) {
		int[][] m1 = m1(rowNo);
		return m1[checkNo];
		
	}

	/**杨辉三角数
	 * @param numRows	杨辉三角行数
	 * @return
	 */
	public static List<List<Integer>> m2(int numRows) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		// 不合法的输入
		if (numRows <= 0)
			return result;
		List<Integer> pre = new ArrayList<Integer>();
		pre.add(1);
		result.add(pre);
		for (int i = 2; i <= numRows; i++) {
			List<Integer> cur = new ArrayList<Integer>();
			cur.add(1); // 开头元素
			for (int j = 0; j < pre.size() - 1; j++) {
				cur.add(pre.get(j) + pre.get(j + 1)); // 中间位置
			}
			cur.add(1);// 末尾元素
			result.add(cur);
			pre = cur;
		}
		return result;
	}

	/**从杨辉三角中获取第rowIndex的数
	 * @param rowIndex	获取第rowIndex的数
	 * @return
	 */
	public static List<Integer> getRow1(int rowIndex) {
		List<Integer> result = new ArrayList<Integer>();
		if (rowIndex <= 0)
			return result;
		result.add(1);
		if (rowIndex == 1)
			return result;
		for (int i = 1; i < rowIndex; i++) {
			for (int j = result.size() - 2; j >= 0; j--) {
				// 赋值前
				// System.out.println("赋值前:" + result);
				result.set(j + 1, result.get(j) + result.get(j + 1));// 从右到左
				// 赋值后
				// System.out.println("赋值后:" + result);
			}
			result.add(1);
		}
		return result;
	}

}
