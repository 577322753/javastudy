package com.book.lambda.filterApple.demo01;

import com.book.lambda.filterApple.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: JavaStudy
 * ClassName: FilterApple01
 * PackageName: com.book.lambda.filterApple.demo01
 * Description: 用户的需求不断变化，代码量持续增加，且重复代码比较多
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 16:07
 */
public class FilterApple01 {
    //第一阶段，参数在函数内部指定，不灵活

    /**
     * 查询出所有的绿颜色的苹果
     * @param inventory
     * @return
     */
    public static List<Apple> filterGreenApple(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getColor().equals("green")){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 查询出比较大的苹果，大于150kg算大苹果
     * @param inventory
     * @return
     */
    public static List<Apple> filterBigApple(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getWeight()>150){
                result.add(apple);
            }
        }
        return result;
    }

    //第二阶段，条件作为参数给出，灵活性提升，但是还是无法应对用户更多的需求，而且代码重复

    /**
     * 查询出指定颜色的苹果
     * @param inventory
     * @param color
     * @return
     */
    public static List<Apple> filterAppleByColor(List<Apple> inventory,String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 查询出大于指定重量的苹果
     * @param inventory
     * @param weight
     * @return
     */
    public static List<Apple> filterAppleByWeight(List<Apple> inventory,double weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getWeight()>weight){
                result.add(apple);
            }
        }
        return result;
    }

    //第三阶段，根据一个变量来判断用户要根据哪个变量来查询，条件组合比较复杂，不易懂

    /**
     * 根据重量或者颜色查询出符合条件的苹果
     * @param inventory 苹果集合
     * @param color 颜色
     * @param weight 重量
     * @param flag true代表根据重量查，false代表根据颜色查
     * @return
     */
    public static List<Apple> filterAppleByColorOrWeight(List<Apple> inventory,String color,double weight,boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if((flag && apple.getWeight()>weight) ||(!flag && apple.getColor().equals(color))){
                result.add(apple);
            }
        }
        return result;
    }


}

