package com.book.GraphicAlgorithm.quickSort;

/**
 * projectName: javastudy
 * packageName: com.book.GraphicAlgorithm.quickSort
 *
 * @author wangjingbiao
 * @date 2019-01-16 19:59
 * desc:
 * 快速排序法
 */
public class QuickSort {
    public static void main(String[] args) {

    }

    public int[] quickSort(int[] array, int start, int end) {
        int key = array[start];
        while (end > start) {
            while (end > start && array[end] >= key) {
                end--;
            }
            if (array[end] <= key){
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            }
        }
        return null;
    }
}
