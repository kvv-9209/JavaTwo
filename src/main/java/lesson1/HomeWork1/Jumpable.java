package lesson1.HomeWork1;

public interface Jumpable {

    default void jump() {
        System.out.println("перепрыгнул");
    }
}
