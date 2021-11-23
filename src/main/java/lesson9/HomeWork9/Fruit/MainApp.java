package lesson9.HomeWork9.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainApp {


    public static void main(String[] args) {

        Apple apple = new Apple();
        Orange orange = new Orange();
        Box<Apple> box1 = new Box(new ArrayList<>());
        Box<Orange> box2 = new Box(new ArrayList<>());

        box1.add(apple);
        box1.add(apple);
        box1.add(apple);
        box2.add(orange);
        box2.add(orange);

        System.out.println(box1.compare(box2,apple));
        System.out.println(box2.getWeight(orange.getWeight()));
    }



}
