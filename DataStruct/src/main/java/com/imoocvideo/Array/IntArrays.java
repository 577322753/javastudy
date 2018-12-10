package com.imoocvideo.Array;

/**
 * desp：
 * 二次封装数组
 * note：
 * 数组的最大优点，快速查询
 * 数组最好应用于索引有语义的情况
 * 但并非所有有语义的索引都适用于数组
 * 数组也可以处理索引无语义的情况
 */
public class IntArrays {
    /**
     * 元数据数组
     */
    private int[] data;
    /**
     * 数组中元素的数量
     */
    private int size;

    /**
     * 构造参数初始化数组
     *
     * @param capacity
     */
    public IntArrays(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    /**
     * 无参构造函数，默认容量设置为10
     */
    public IntArrays() {
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
    public void addLast(int e) {
        add(size, e);
    }

    /**
     * 在最前端插入元素
     *
     * @param e
     */
    public void addFirst(int e) {
        add(0, e);
    }

    /**
     * 在指定位置添加元素
     *
     * @param index
     * @param e
     */
    public void add(int index, int e) {
        if (size == data.length) {
            throw new IllegalArgumentException("AddLast failed.Array is full.");
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Require index >= 0 and <= size : " + size);
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
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed,Require index >= 0 and <= size : " + size);
        }
        return data[index];
    }

    /**
     * 修改元素
     *
     * @param index
     * @param e
     */
    public void set(int index, int e) {
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
    public int find(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
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
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed,Require index >= 0 and <= size : " + size);
        }
        int ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return ret;
    }

    /**
     * 删除元素头中的元素
     *
     * @return
     */
    public int removeFirst() {
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素
     *
     * @return
     */
    public int removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除指定的元素
     * @param e
     * @return
     */
    public boolean removeElement(int e) {
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

}
