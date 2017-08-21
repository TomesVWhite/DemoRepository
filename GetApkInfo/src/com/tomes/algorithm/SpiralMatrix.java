package com.tomes.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋式遍历二维数组（矩阵）
 * 
 * @author Tomes
 * 
 */
public class SpiralMatrix {

	public static void main(String[] args) {
		int[][] a = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		generateMatrix1(3);
	}

	/**
	 * 螺旋方式访问二维数组，并将逆时针遍历获取到的结果，封装到list
	 * 
	 * @param matrix
	 *            需要访问的二维数组
	 * @return 逆时针遍历获取到的结果
	 */
	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();
		// 行
		int m = matrix.length;
		// 列
		int n = matrix[0].length;
		int beginX, endX, beginY, endY;
		beginX = 0;
		endX = n - 1;
		beginY = 0;
		endY = m - 1;
		while (true) {
			// 从左上到右上
			for (int j = beginX; j <= endX; j++) {
				result.add(matrix[beginY][j]);
			}
			beginY++;
			if (beginY > endY)
				break;
			// 从右上到右下
			for (int i = beginY; i <= endY; i++) {
				result.add(matrix[i][endX]);
			}
			endX--;
			if (endX < beginX)
				break;

			// 从右下到左下
			for (int j = endX; j >= beginX; j--) {
				result.add(matrix[endY][j]);
			}
			endY--;
			if (endY < beginY)
				break;

			// 从左下到左上
			for (int i = endY; i >= beginY; i--) {
				result.add(matrix[i][beginX]);
			}
			beginX++;
			if (beginX > endX)
				break;
		}
		return result;

	}

	/**
	 * 给定一个n，从1开始逆时针生成一个n*n的矩阵
	 * 
	 * @param n
	 *            该矩阵的行（列）数
	 * @return 用二维数组表示的矩阵
	 */
	public static int[][] generateMatrix2(int n) {
		int[][] result = new int[n][n];
		int num, beginX, endX, beginY, endY;
		num = 1;
		beginX = 0;
		endX = n - 1;
		beginY = 0;
		endY = n - 1;
		while (true) {
			// 从左上到右上
			for (int j = beginX; j <= endX; j++) {
				result[beginX][j] = num++;
			}
			beginY++;
			if (beginY > endY)
				break;
			// 从右上到右下
			for (int i = beginY; i <= endY; i++) {
				result[i][endX] = num++;
			}
			endX--;
			if (endX < beginX)
				break;

			// 从右下到左下
			for (int j = endX; j >= beginX; j--) {
				result[endY][j] = num++;
			}
			endY--;
			if (endY < beginY)
				break;

			// 从左下到左上
			for (int i = endY; i >= beginY; i--) {
				result[i][beginX] = num++;
			}
			beginX++;
			if (beginX > endX)
				break;
		}
		return result;
	}

	//如：int[] a={1,2,3,4,5,6,7,8,9} 该方法第一次遍历1，2；上面的方法遍历1，2，3；
	/**
	 * 给定一个n，从1开始逆时针生成一个n*n的矩阵
	 * （分割方式不同，该方式第一次只遍历n-1个数，而不是将一排一行全遍历完）
	 * @param n
	 *            该矩阵的行（列）数
	 * @return 用二维数组表示的矩阵
	 */
	public static int[][] generateMatrix1(int n) {
		int[][] result = new int[n][n];
		if (n <= 0)
			return null;
		int begin = 0, end = n - 1;
		int num = 1;
		while (begin < end) {
			// 从左到右
			for (int j = begin; j < end; j++) {
				result[begin][j] = num++;
			}
			System.out.println();
			// 从上到下
			for (int i = begin; i < end; i++) {
				result[i][end] = num++;
			}
			System.out.println();
			// 从右到左
			for (int j = end; j > begin; j--) {
				result[end][j] = num++;
			}
			System.out.println();
			// 从下到上
			for (int i = end; i > begin; i--) {
				result[i][begin] = num++;
			}
			begin++;
			end--;

		}
		if (begin == end) {
			result[begin][end] = num;
		}
		return result;
	}
}
