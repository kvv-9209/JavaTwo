package lesson1.HomeWork1;

public interface Runable {

    default void run() {
        System.out.println("пробежал");
    }
}
