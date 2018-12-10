package com.imoocvideo.StackAndQueue;

/**
 * ProjectName: JavaStudy
 * ClassName: Queue
 * PackageName: com.imoocvideo.StackAndQueue
 * Description: 队列接口,队列是先进先出
 *
 * @author: wangjingbiao
 * @date: 2018-11-06 11:31
 */
public interface Queue<E> {
    /**
     * 入队列
     * @param e
     */
    public void enqueue(E e);

    /**
     * 出队列
     * @return
     */
    public E dequeue();

    /**
     * 查看队首元素
     * @return
     */
    public E getFront();

    /**
     * 查看队列大小
     * @return
     */
    public int getSize();

    /**
     * 查看队列是否为空
     * @return
     */
    public boolean isEmpty();

}
