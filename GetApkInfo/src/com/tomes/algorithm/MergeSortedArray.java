package com.tomes.algorithm;

/**
 * 混合插入有序数组
 * 针对https://leetcode.com/problems/merge-sorted-array/description/
 * @author Tomes
 * 
 */
public class MergeSortedArray {
	public static void main(String[] args) {
		int[] A = { 2, 4, 5 };
		int[] B = { 1, 3, 6 };
		merge(A, 3, B, 3);
		System.out.println("aaa");
	}

	/**
	 * @param nums1 数组1
	 * @param m	数组1长度
	 * @param nums2	数组2
	 * @param n	数组2长度
	 */
	public static void merge(int nums1[], int m, int nums2[], int n) {
		if (nums1 == null || nums2 == null)
			return;
		int idx1 = m - 1;
		int idx2 = n - 1;
		int len = m + n - 1;
		while (idx1 >= 0 && idx2 >= 0) {
			if (nums1[idx1] > nums2[idx2]) {
				nums1[len--] = nums1[idx1--];
			} else {
				nums1[len--] = nums2[idx2--];
			}
		}
		while (idx2 >= 0) {
			nums1[len--] = nums2[idx2--];
		}
	}

	public static void merge1(int A[], int m, int B[], int n) {
		int count = m + n - 1;
		--m;
		--n;
		while (m >= 0 && n >= 0)
			A[count--] = A[m] > B[n] ? A[m--] : B[n--];
		while (n >= 0)
			A[count--] = B[n--];
	}

	public static void merge2(int A[], int m, int B[], int n) {
		if (m <= 0 && n <= 0)
			return;
		int a = 0, b = 0;
		int[] C = new int[m + n];
		for (int i = 0; i < m + n; ++i) {
			if (a < m && b < n) {
				if (A[a] < B[b]) {
					C[i] = A[a];
					++a;
				} else {
					C[i] = B[b];
					++b;
				}
			} else if (a < m && b >= n) {
				C[i] = A[a];
				++a;
			} else if (a >= m && b < n) {
				C[i] = B[b];
				++b;
			} else
				return;
		}
		for (int i = 0; i < m + n; ++i)
			A[i] = C[i];
	}
}
