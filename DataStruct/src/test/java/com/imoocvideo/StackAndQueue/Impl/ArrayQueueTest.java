package com.imoocvideo.StackAndQueue.Impl;

import com.imoocvideo.StackAndQueue.Queue;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * ProjectName: JavaStudy
 * ClassName: ArrayQueueTest
 * PackageName: com.imoocvideo.StackAndQueue.Impl
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-11-06 11:44
 */
public class ArrayQueueTest {
    private Logger logger = LoggerFactory.getLogger(ArrayQueueTest.class);

    Queue<Integer> queue;

    @Before
    public void init() {
        queue = new LoopQueue<>();
        for (int i = 0; i < 12; i++) {
            queue.enqueue(i);
        }
    }

    @Test
    public void enqueue() {
        logger.debug(queue.toString());
        queue.enqueue(10000);
        logger.debug(queue.toString());
    }

    @Test
    public void dequeue() {
        logger.debug(queue.toString());
        queue.dequeue();
        logger.debug(queue.toString());
    }

    @Test
    public void getFront() {
        logger.debug(queue.toString());
        logger.debug("get front :{}", queue.getFront());
        logger.debug(queue.toString());
    }

    @Test
    public void getSize() {
        logger.debug("queue size :{}", queue.getSize());
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void getCapacity() {
    }

    /**
     * 比对两种实现方式的性能
     */
    @Test
    public void compareQueue() {
        int nums = 1000000;
        Queue<Integer> arrayQueue = new ArrayQueue<>();
        for (int i = 0; i < nums; i++) {
            arrayQueue.enqueue(i);
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < nums; i++) {
            arrayQueue.dequeue();
        }
        long endTime = System.nanoTime();
        logger.debug("array queue use time :{}", (endTime - startTime) / 1000000000.0);

        Queue<Integer> loopQueue = new LoopQueue<>();
        for (int i = 0; i < nums; i++) {
            loopQueue.enqueue(i);
        }
        startTime = System.nanoTime();
        for (int i = 0; i < nums; i++) {
            loopQueue.dequeue();
        }
        endTime = System.nanoTime();
        logger.debug("loop queue use time :{}", (endTime - startTime) / 1000000000.0);
    }
}