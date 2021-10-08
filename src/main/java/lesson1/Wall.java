package lesson1;

import lesson1.interfaces.Obstacles;

public class Wall implements Obstacles {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public int getLength() {
        return 0;
    }

    public int getHeight() {
        return height;
    }
}
