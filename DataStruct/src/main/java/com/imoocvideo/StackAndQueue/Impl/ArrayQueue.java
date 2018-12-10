package com.imoocvideo.StackAndQueue.Impl;

import com.imoocvideo.Array.Arrays;
import com.imoocvideo.StackAndQueue.Queue;

/**
 * ProjectName: JavaStudy
 * ClassName: ArrayQueue
 * PackageName: com.imoocvideo.StackAndQueue.Impl
 * Description: 使用数组实现队列,数组队列存在问题，删除元素是O(n)的，因为所有的元素要前移
 *
 * @author: wangjingbiao
 * @date: 2018-11-06 11:36
 */
public class ArrayQueue<E> implements Queue<E> {
    private Arrays<E> arrays;

    public ArrayQueue() {
        arrays = new Arrays<>();
    }

    public ArrayQueue(int capacity) {
        arrays = new Arrays<>(capacity);
    }

    @Override
    public void enqueue(E e) {
        arrays.addLast(e);
    }

    @Override
    public E dequeue() {
        return arrays.removeFirst();
    }

    @Override
    public E getFront() {
        return arrays.getFirst();
    }

    @Override
    public int getSize() {
        return arrays.getSize();
    }

    @Override
    public boolean isEmpty() {
        return arrays.isEmpty();
    }

    public int getCapacity() {
        return arrays.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("queue : ");
        stringBuilder.append("tail [");
        for (int i = arrays.getSize() - 1; i >= 0; i--) {
            stringBuilder.append(arrays.get(i));
            if (i != 0) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("] ,front");
        return stringBuilder.toString();
    }
}
