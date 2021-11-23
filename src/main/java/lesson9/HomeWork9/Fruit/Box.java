package lesson9.HomeWork9.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Box<F> {

    private ArrayList<F> currentBox;


    public Box(ArrayList<F> currentBox) {
        this.currentBox = currentBox;
    }

    public int getSize() {
        return currentBox.size();
    }

    public boolean compare(Box box) {
        if(){
            return true;
        }
        return false;
    }

    public void add(F fruit) {
        currentBox.add(fruit);
    }

    public float getWeight(float weight){
      return getSize() * weight;
    }

}
