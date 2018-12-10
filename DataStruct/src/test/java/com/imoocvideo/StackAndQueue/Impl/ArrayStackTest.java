package com.imoocvideo.StackAndQueue.Impl;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProjectName: JavaStudy
 * ClassName: ArrayStackTest
 * PackageName: com.imoocvideo.StackAndQueue.Impl
 * Description: 对数组实现的栈进行测试
 *
 * @author: wangjingbiao
 * @date: 2018-11-06 10:23
 */
public class ArrayStackTest {
    private Logger logger = LoggerFactory.getLogger(ArrayStackTest.class);

    private ArrayStack<Integer> stack;

    @Before
    public void init(){
        stack = new ArrayStack<>();
        stack.push(1);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(77);
        stack.push(9);
    }

    @Test
    public void getSize() {
        logger.debug("size: {}",stack.getSize());
    }

    @Test
    public void isEmpty() {
        logger.debug("isempty: {}",stack.isEmpty());
    }

    @Test
    public void push() {
        stack.push(150);
        logger.debug(stack.toString());
    }

    @Test
    public void pop() {
        stack.pop();
        logger.debug(stack.toString());
        stack.pop();
        logger.debug(stack.toString());
    }

    @Test
    public void peek() {
        logger.debug("stack peek: {}",stack.peek());
        logger.debug(stack.toString());
    }

    @Test
    public void getCapacity() {
        logger.debug("current capacity: {}",stack.getCapacity());
    }
}