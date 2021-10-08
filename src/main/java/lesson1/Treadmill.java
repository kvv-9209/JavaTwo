package lesson1;

import lesson1.interfaces.Obstacles;

public class Treadmill implements Obstacles {
    private int length;

    public Treadmill(int length) {
        this.length = length;
    }


    public int getLength() {
        return length;
    }

    @Override
    public int getHeight() {
        return 0;
    }


}
