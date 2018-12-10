package com.imoocvideo.Array;

/**
 * desp：
 * 使用泛型抽象通用数组
 * note：
 * 数组的最大优点，快速查询
 * 数组最好应用于索引有语义的情况
 * 但并非所有有语义的索引都适用于数组
 * 数组也可以处理索引无语义的情况
 */
public class Arrays<E> {
    /**
     * 元数据数组
     */
    private E[] data;
    /**
     * 数组中元素的数量
     */
    private int size;

    /**
     * 构造参数初始化数组
     *
     * @param capacity
     */
    public Arrays(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参构造函数，默认容量设置为10
     */
    public Arrays() {
        this(10);
    }

    /**
     * 获取数组元素数量
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     *
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在尾端插入元素
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 在最前端插入元素
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在指定位置添加元素
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Require index >= 0 and <= size : " + size);
        }
        // 数组扩容
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 封装获取元素的方法，唯一入口，保证数据安全
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed,Require index >= 0 and <= size : " + size);
        }
        return data[index];
    }

    /**
     * 取出数组中的最后一个元素
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 取出数组中的第一个元素
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 修改元素
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed,Require index >= 0 and <= size : " + size);
        }
        data[index] = e;
    }

    /**
     * 查询数组中某个元素
     *
     * @param e
     * @return 存在时返回下标，不存在返回-1
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除元素
     *
     * @param index
     * @return 返回删除的元素
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed,Require index >= 0 and <= size : " + size);
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        //释放最后一位的元素，让垃圾回收器更好的回收
        data[size] = null;
        //缓慢缩小，避免出现频繁的resize操作，当达到1/4的大小时，才缩写为1/2
        if (size == data.length / 4) {
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 删除元素头中的元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除指定的元素
     *
     * @param e
     * @return
     */
    public boolean removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 输出数组
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    /**
     * 数组动态扩展容量
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
