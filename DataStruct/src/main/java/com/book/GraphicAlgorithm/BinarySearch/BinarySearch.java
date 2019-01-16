package com.book.GraphicAlgorithm.BinarySearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * projectName: javastudy
 * packageName: com.book.GraphicAlgorithm.BinarySearch
 *
 * @author wangjingbiao
 * @date 2019-01-16 09:56
 * desc:
 * 二分查找，学习及测试
 */
public class BinarySearch {
    public static final int dataSize = 100000000;
    public static final Logger logger = LoggerFactory.getLogger(BinarySearch.class);

    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
        int[] array = new int[dataSize];
        for (int i = 0; i < dataSize; i++) {
            array[i] = i;
        }
        long startTime1 = System.currentTimeMillis();
        int index1 = search.findDataByBinarySearch(array, 10000002);
        long endTime1 = System.currentTimeMillis();
        logger.debug("find data,index:{},cost Time:{}ms",index1,endTime1-startTime1);

        long startTime2 = System.currentTimeMillis();
        int index2 = search.findDataByEasySearch(array, 10000002);
        long endTime2 = System.currentTimeMillis();
        logger.debug("find data,index:{},cost Time:{}ms",index2,endTime2-startTime2);
    }

    /**
     * 二分查找
     * desc：
     * 二分查找只能用于排好序的数组中
     * 算法复杂度为O(log(n)),最大查找次数为 log2(n)
     * 查找中间位，并不断调整位置
     * @param array
     * @param i
     * @return
     */
    public int findDataByBinarySearch(int[] array, int i) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int guess = array[mid];
            if (guess == i) {
                return mid;
            }
            if (guess > i) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 简单查找
     *
     * @param array
     * @param i
     * @return
     */
    public int findDataByEasySearch(int[] array, int i) {
        for (int j = 0; j < array.length; j++) {
            if(array[j] == i){
                return j;
            }
        }
        return -1;
    }
}
