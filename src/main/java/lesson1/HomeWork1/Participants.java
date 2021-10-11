package lesson1.HomeWork1;

public interface Participants {

    default void jump() {
        System.out.println("перепрыгнул");
    }

    default void run() {
        System.out.println("пробежал");
    }

    boolean obstacle(Obstacle obstacle);

    String getName();
}
