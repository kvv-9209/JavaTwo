package lesson9.HomeWork9.Fruit;

import java.util.ArrayList;

public class Box<F> {

    private float weight;
    private boolean box;
    private ArrayList<F> currentBox;

    public Box(float weight, boolean box, ArrayList<F> currentBox) {
        this.weight = weight;
        this.box = box;
        this.currentBox = currentBox;
    }

    public float getWeight() {
        return weight;
    }

    public boolean compare(ArrayList<F> newBox){
        return currentBox.equals(newBox);
    }
}
