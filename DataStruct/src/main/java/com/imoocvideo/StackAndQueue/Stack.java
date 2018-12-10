package com.imoocvideo.StackAndQueue;

/**
 * ProjectName: JavaStudy
 * ClassName: Stack
 * PackageName: com.imoocvideo.StackAndQueue
 * Description: 栈接口，栈的特点是后进先出
 *
 * @author: wangjingbiao
 * @date: 2018-11-06 10:07
 */
public interface Stack<E> {
    /**
     * 查看当前栈中所有的元素
     *
     * @return
     */
    int getSize();

    /**
     * 查看栈大小
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 往栈中放入元素
     *
     * @param e
     */
    void push(E e);

    /**
     * 从栈中取出一个元素
     *
     * @return
     */
    E pop();

    /**
     * 查看栈顶元素
     *
     * @return
     */
    E peek();

}
