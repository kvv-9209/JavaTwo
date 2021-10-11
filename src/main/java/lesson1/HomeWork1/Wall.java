package lesson1.HomeWork1;

public class Wall implements Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public int getTredmill() {
        return 0;
    }

    @Override
    public int getWall() {
        return height;
    }
}
