package com.imoocvideo.StackAndQueue.Solution;

import java.util.Stack;

/**
 * ProjectName: JavaStudy
 * ClassName: BracketMatching
 * PackageName: com.imoocvideo.StackAndQueue.Solution
 * Description: 使用栈来解决括号匹配的问题，匹配括号[],{},(),leecode习题
 *
 * @author: wangjingbiao
 * @date: 2018-11-06 10:42
 */
public class BracketMatching {
    /**
     * 匹配括号
     * @param s
     * @return
     */
    public boolean isMatch(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                //将左括号入栈
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                //将右括号与栈顶元素做对比
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
