package com.javastudy;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * projectName: javastudy
 * packageName: com.javastudy
 *
 * @author wangjingbiao
 * @date 2019-01-07 10:39
 * desc:
 * 测试set集合中的元素是否会重复
 */
public class TestSetSame {
    @Test
    public void testSetAdd(){
        Set<String> set = new HashSet<>();
        set.add("a1");
        set.add("a2");
        set.add("a1");
        System.out.println(set);
    }
}
