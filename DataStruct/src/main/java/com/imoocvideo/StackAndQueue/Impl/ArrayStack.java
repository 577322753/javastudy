package com.imoocvideo.StackAndQueue.Impl;

import com.imoocvideo.Array.Arrays;
import com.imoocvideo.StackAndQueue.Stack;

/**
 * ProjectName: JavaStudy
 * ClassName: ArrayStack
 * PackageName: com.imoocvideo.StackAndQueue.Impl
 * Description: 使用动态数组实现栈
 *
 * @author: wangjingbiao
 * @date: 2018-11-06 10:11
 */
public class ArrayStack<E> implements Stack<E> {
    private Arrays<E> arrays;

    public ArrayStack() {
        arrays = new Arrays<>();
    }

    public ArrayStack(int capacity) {
        arrays = new Arrays<>(capacity);
    }

    @Override
    public int getSize() {
        return arrays.getSize();
    }

    @Override
    public boolean isEmpty() {
        return arrays.isEmpty();
    }

    @Override
    public void push(E e) {
        arrays.addLast(e);
    }

    @Override
    public E pop() {
        return arrays.removeLast();
    }

    @Override
    public E peek() {
        return arrays.getLast();
    }

    /**
     * array实现栈所独有的方法
     * @return
     */
    public int getCapacity() {
        return arrays.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack : ");
        stringBuilder.append("[");
        for (int i = 0; i < arrays.getSize(); i++) {
            stringBuilder.append(arrays.get(i));
            if (i != arrays.getSize() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("] ,top");
        return stringBuilder.toString();
    }
}
