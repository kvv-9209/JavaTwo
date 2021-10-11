package lesson1.HomeWork1;

public class Cat implements Participants {
    private String name;
    private int runLimit;
    private int jumpLimit;

    public Cat(String name, int runLimit, int jumpLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    @Override
    public void jump() {
        System.out.println(name + " перепрыгнул");
    }

    @Override
    public void run() {
        System.out.println(name + " пробежал");
    }

    @Override
    public boolean obstacle(Obstacle obstacle) {
        if (obstacle.getTredmill() <= runLimit && obstacle.getWall() == 0) {
            return true;
        } else if (obstacle.getWall() <= jumpLimit && obstacle.getTredmill() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
