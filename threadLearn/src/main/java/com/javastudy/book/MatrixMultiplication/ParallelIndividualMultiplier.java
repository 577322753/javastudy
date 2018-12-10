package com.javastudy.book.MatrixMultiplication;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: JavaStudy
 * ClassName: ParallelIndividualMultiplier
 * PackageName: com.javastudy.book.MatrixMultiplication
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-10-16 11:32
 */
public class ParallelIndividualMultiplier {
    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        List<Thread> threads = new ArrayList<>();

        int row1 = matrix1.length;
        int row2 = matrix2.length;

        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < row2; j++) {
                IndividualMultiplierTask task = new IndividualMultiplierTask(result, matrix1, matrix2, i, j);
                Thread thread = new Thread(task);
                thread.start();
                threads.add(thread);

                if (threads.size() % 10 == 0) {
                    waitForThreads(threads);
                }
            }
        }
    }

    private static void waitForThreads(List<Thread> threads) {
        for (Thread thread: threads
             ) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }
}
