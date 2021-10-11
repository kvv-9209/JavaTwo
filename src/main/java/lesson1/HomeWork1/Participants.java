package lesson1.HomeWork1;

public abstract class Participants implements Jumpable, Runable {
    public String name;
    private int jumpLimit;
    private int runLimit;

    public Participants(String name, int jumpLimit, int runLimit) {
        this.name = name;
        this.jumpLimit = jumpLimit;
        this.runLimit = runLimit;
    }

    @Override
    public void jump() {
        System.out.println(name + " перепрыгнул");
    }

    @Override
    public void run() {
        System.out.println(name + " пробежал");
    }

    public boolean obstacle(Obstacle obstacle) {
        if (obstacle.getTredmill() <= this.runLimit && obstacle.getTredmill() != 0) {
            run();
            return true;
        } else if (obstacle.getWall() <= this.jumpLimit && obstacle.getWall() != 0) {
            jump();
            return true;
        } else {
            System.out.println(name + " не смог пройти дальше");
            return false;
        }
    }
}
