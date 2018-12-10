package com.imoocvideo.Array;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntArraysTest {
    @Test
    public void testMyIntArray() {
        IntArrays arr = new IntArrays(20);
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 250);
        System.out.println(arr);

        arr.remove(1);
        System.out.println(arr);

        arr.remove(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);
    }

}