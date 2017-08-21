package com.tomes.properties;

/**
 * 旋转一个二维矩阵,此类以顺时针旋转90为例，其余度数自然可以获取到。
 * 
 * @author Tomes
 * 
 */
public class RotateImage {

	public static void main(String[] args) {
		int[][] w2 = new int[][] { { 7, 2, 1 }, { 3, 5, 6 }, { 4, 2, 3 } };
//		m1(w2);
		// 顺时针旋转90
		System.out.println("-------------------");
		m2(w2);
		m3(w2);
	}

	/**
	 * 矩阵顺时针旋转90
	 * 
	 * @param matrix
	 */
	public static void m2(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		if (m != n)
			return;
		int[][] result = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				result[j][n - 1 - i] = matrix[i][j];
			}
		// print(result);//打印结果（方法不再给出）
		m1(result);
	}

	/**
	 * 打印二维数组
	 * 
	 * @param 二维数组
	 */
	private static void m1(int[][] w2) {
		System.out.println();
		for (int i = 0; i < w2.length; i++) {
			for (int j = 0; j < w2.length; j++) {
				System.out.print(w2[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/*
	 * 反转图像1:主对角线反转，垂直中线反转 时间复杂度 O(n^2) ，空间复杂度 O(1)
	 */
	public static void m3(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		if (m != n)
			return;
		int temp;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < i; j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n / 2; j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[i][n - 1 - j];
				matrix[i][n - 1 - j] = temp;
			}
		m1(matrix);
	}
	
	/*
     * 副对角线反转，水平中线反转 时间复杂度 O(n^2) ，空间复杂度 O(1)
     */

    public static void m3a(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m != n)
            return;
        int temp;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n - 1 - i; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][n - 1 - i];
                matrix[n - 1 - j][n - 1 - i] = temp;
            }
        for (int i = 0; i < n / 2; i++)
            for (int j = 0; j < n; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
    }
    
    /*
     * 水平中线反转，主对角线反转 时间复杂度 O(n^2) ，空间复杂度 O(1)
     */
    public static void m3b(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m != n)
            return;
        int temp;
        for (int i = 0; i < n / 2; i++)
            for (int j = 0; j < n; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
    }
    
    /*
     * 垂直中线反转，副对角线反转 时间复杂度 O(n^2) ，空间复杂度 O(1)
     */
    public static void m3c(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m != n)
            return;
        int temp;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n / 2; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n - 1 - i; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][n - 1 - i];
                matrix[n - 1 - j][n - 1 - i] = temp;
            }
    } 
}
