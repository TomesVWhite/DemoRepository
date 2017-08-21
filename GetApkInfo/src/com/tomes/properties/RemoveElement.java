package com.tomes.properties;

import java.lang.reflect.Array;
import java.util.Arrays;

/**针对https://leetcode.com/problems/remove-element/description/
 * 的题目，提供的数组移除指定元素后的个数与新数组。
 * @author Tomes
 *
 */
public class RemoveElement {

	public static void main(String[] args) {
		int[] a={1,2,3,4,4,5};
//		int m1 = m1(a,4);
//		System.out.println(m1);
		int[] newArrays = removeElement(a, 4);
//		System.out.println(newArrays);
		for (int i : newArrays) {
			System.out.print(i+"\t");
		}
	}

	
	/**找到指定元素，并返回排除指定元素后数组的长度
	 * @param a	需要筛选的数组
	 * @param i	指定元素	
	 * @return	排除指定元素后数组的长度
	 */
	private static int m1(int[] a, int i) {
		int count=0;
		for (int j = 0; j < a.length; j++) {
			if(a[j]==i){
				count++;
			}
		}
		return a.length-count;
	}
	
	/**到指定元素，并返回排除指定元素后数组
	 * @param A	需要筛选的数组
	 * @param elem	指定元素	
	 * @return	排除指定元素后数组
	 */
	public static int[] removeElement(int[] A, int elem) {
        int index = 0;
        for (int i = 0; i < A.length; i++)
            if (A[i] != elem) {
                A[index] = A[i];
                index++;
            }
        int[] newArrays = Arrays.copyOf(A, index);
        return newArrays;
    }
	
}
