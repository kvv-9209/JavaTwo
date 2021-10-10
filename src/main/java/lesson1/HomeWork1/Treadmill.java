package lesson1.HomeWork1;

public class Treadmill implements Obstacle {
   private int length;

    public Treadmill(int length) {
        this.length = length;
    }

    @Override
    public int getTredmill() {
        return length;
    }

    @Override
    public int getWall() {
        return 0;
    }
}
