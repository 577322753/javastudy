package com.book.lambda.filterApple;

/**
 * ProjectName: JavaStudy
 * ClassName: Apple
 * PackageName: com.book.lambda.filterApple
 * Description: 苹果实体类
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 16:06
 */
public class Apple {
    public Apple() {
    }

    public Apple(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    private String color;
    private double weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
