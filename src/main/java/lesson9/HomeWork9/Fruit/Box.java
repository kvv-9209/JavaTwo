package lesson9.HomeWork9.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Box<F extends Fruit> {

    private List<F> currentBox;


    public Box() {
        this.currentBox = new ArrayList<>();
    }

    public boolean compare(Box<? extends Fruit> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.00001;
    }

    public void add(F fruit) {
        currentBox.add(fruit);
    }

    public float getWeight() {
        float weight = 0.0f;

        for (F fruit : currentBox) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public void pourFruitFromBoxToBox(Box<F> box) {
        box.currentBox.addAll(currentBox);
        currentBox.clear();
    }

}
