package com.imoocvideo.StackAndQueue.Solution;

import org.junit.Test;


/**
 * ProjectName: JavaStudy
 * ClassName: BracketMatchingTest
 * PackageName: com.imoocvideo.StackAndQueue.Solution
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-11-06 11:03
 */
public class BracketMatchingTest {

    @Test
    public void isMatch() {
        BracketMatching matching = new BracketMatching();
        boolean flag1 = matching.isMatch("[}");
        boolean flag2 = matching.isMatch("{([])}");
        boolean flag3 = matching.isMatch("public static void main(String args[])");
    }
}