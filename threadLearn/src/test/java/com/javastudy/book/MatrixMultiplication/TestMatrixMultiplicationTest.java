package com.javastudy.book.MatrixMultiplication;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * ProjectName: JavaStudy
 * ClassName: TestMatrixMultiplicationTest
 * PackageName: com.javastudy.book.MatrixMultiplication
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-10-16 11:16
 */
public class TestMatrixMultiplicationTest {
    private Logger logger = LoggerFactory.getLogger(TestMatrixMultiplicationTest.class);
    public static final int rows = 1000;
    public static final int columns = 1000;
    /**
     * 测试串行方法
     */
    @Test
    public void testSerialMethod() {
        double[][] matrix1 = MatrixGenerator.generate(rows, columns);
        double[][] matrix2 = MatrixGenerator.generate(rows, columns);
        double result[][] = new double[matrix1.length][matrix2[0].length];
        logger.info("generate array,start Calculation");
        long startTime = System.currentTimeMillis();
        SerialMultiplier.multiply(matrix1, matrix2, result);
        long endTime = System.currentTimeMillis();
        logger.info("spend time {}", startTime - endTime);
//        printfMatrix(result);
    }

    @Test
    public void testIndividualThreadMethod() {
        double[][] matrix1 = MatrixGenerator.generate(rows, columns);
        double[][] matrix2 = MatrixGenerator.generate(rows, columns);
        double result[][] = new double[matrix1.length][matrix2[0].length];
        logger.info("generate array,start Calculation");
        long startTime = System.currentTimeMillis();
        ParallelIndividualMultiplier.multiply(matrix1, matrix2, result);
        long endTime = System.currentTimeMillis();
        logger.info("spend time {}", startTime - endTime);
//        printfMatrix(result);
    }

    @Test
    public void testRowsThreadMethod() {
        double[][] matrix1 = MatrixGenerator.generate(rows, columns);
        double[][] matrix2 = MatrixGenerator.generate(rows, columns);
        double result[][] = new double[matrix1.length][matrix2[0].length];
        logger.info("generate array,start Calculation");
        long startTime = System.currentTimeMillis();
        ParallelRowMultplier.multiply(matrix1, matrix2, result);
        long endTime = System.currentTimeMillis();
        logger.info("spend time {}", startTime - endTime);
//        printfMatrix(result);
    }

    @Test
    public void testGroupThreadMethod() {
        double[][] matrix1 = MatrixGenerator.generate(rows, columns);
        double[][] matrix2 = MatrixGenerator.generate(rows, columns);
        double result[][] = new double[matrix1.length][matrix2[0].length];
        logger.info("generate array,start Calculation");
        long startTime = System.currentTimeMillis();
        ParallelGroupMutiplier.multiply(matrix1, matrix2, result);
        long endTime = System.currentTimeMillis();
        logger.info("spend time {}", startTime - endTime);
//        printfMatrix(result);
    }


    private void printfMatrix(double[][] result){
        for (int i = 0; i < result.length; i++) {
            System.out.println();
            for (int j = 0; j < result[i].length; j++) {
                System.out.print (result[i][j]+" ");
            }
        }
        System.out.println();
    }

}