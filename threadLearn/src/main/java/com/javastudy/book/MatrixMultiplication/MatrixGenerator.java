package com.javastudy.book.MatrixMultiplication;

import java.util.Random;

/**
 * ProjectName: JavaStudy
 * ClassName: MatrixGenerator
 * PackageName: com.javastudy.book.MatrixMultiplication
 * Description: 生成一个随机矩阵
 *
 * @author: wangjingbiao
 * @date: 2018-10-16 10:58
 */
public class MatrixGenerator {
    /**
     * 随机矩阵
     * @param rows 行
     * @param columns 列
     * @return 生成的矩阵
     */
    public static double[][] generate(int rows,int columns){
        double[][] ret =  new double[rows][columns];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                ret[i][j] = random.nextDouble()*10;
            }
        }
        return ret;
    }
}
