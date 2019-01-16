package com.book.GraphicAlgorithm.SelectSort;

import com.imoocvideo.LinkedList.LinkedList;

/**
 * projectName: javastudy
 * packageName: com.book.GraphicAlgorithm.SelectSort
 *
 * @author wangjingbiao
 * @date 2019-01-16 11:12
 * desc:
 * 选择排序及相关测试
 */
public class SelectSort {
    public static void main(String[] args) {
        int arr[] = new int[]{7, 10, 26, 1, 5, 9, 100, 8, 6};
        printArr(arr);
        int[] sortArr = new SelectSort().selectSort(arr);
        printArr(arr);
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public int[] selectSort(int[] arr) {
        int resultArr[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int k = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    k = j;
                }
            }

            if (k > i){
                int tmp = arr[i];
                arr[i] = arr[k];
                arr[k] = tmp;
            }
        }
        return resultArr;
    }
}
