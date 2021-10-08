package lesson1;

import lesson1.interfaces.Obstacles;
import lesson1.interfaces.Participants;

public class Robot implements Participants {
    private String name;
    private int jumpLimit;
    private int runLimit;

    public Robot(String name, int jumpLimit, int runLimit) {
        this.name = name;
        this.jumpLimit = jumpLimit;
        this.runLimit = runLimit;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean obstacle(Obstacles obstacle) {

        if (jumpLimit > obstacle.getHeight()) {
            return true;
        } /*else {
            //System.out.println(name + " не может пройти дальше");
            return false;
        }*/
        if (runLimit > obstacle.getLength()) {
            return true;
        } /*else {
            //System.out.println(name + " не может пройти дальше");
            return false;
        }*/
        return false;
    }
}
