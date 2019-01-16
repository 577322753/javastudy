package com.book.GraphicAlgorithm.Recursive;

/**
 * projectName: javastudy
 * packageName: com.book.GraphicAlgorithm.Recursive
 *
 * @author wangjingbiao
 * @date 2019-01-16 19:12
 * desc:
 * 递归相关知识
 */
public class Recursive {
    public static void main(String[] args) {
        System.out.println(new Recursive().fact(5));
        System.out.println(new Recursive().add(new int[]{1,3,5,7},4));
    }

    /**
     * 递归实现阶乘
     * @param x
     * @return
     */
    public int fact(int x) {
        if (x == 1) {
            // 基线条件
            return 1;
        } else {
            // 递归条件
            return x * fact(x - 1);
        }
    }

    /**
     * 递归实现求数组之和
     */
    public int add(int[] arrays,int n){
        if(n > 1){
            return arrays[n-1]+add(arrays,n-1);
        }else{
            return arrays[0];
        }
    }
}
