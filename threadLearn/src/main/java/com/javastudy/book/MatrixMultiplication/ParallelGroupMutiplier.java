package com.javastudy.book.MatrixMultiplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: JavaStudy
 * ClassName: ParallelGroupMutiplier
 * PackageName: com.javastudy.book.MatrixMultiplication
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-10-16 16:38
 */
public class ParallelGroupMutiplier {
    public static final Logger logger = LoggerFactory.getLogger(ParallelGroupMutiplier.class);
    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        List<Thread> threads = new ArrayList<>();

        int row1 = matrix1.length;

        int numThreads = Runtime.getRuntime().availableProcessors();
        logger.debug("use {} "+numThreads+" core cpu");
        int startIndex, endIndex, step;
        step = row1 / numThreads;
        startIndex = 0;
        endIndex = step;

        for (int i = 0; i < numThreads; i++) {
            GroupMultiplierTask task = new GroupMultiplierTask(result, matrix1, matrix2, startIndex, endIndex);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
            startIndex = endIndex;
            endIndex = i == numThreads - 2 ? row1 : endIndex + step;
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
