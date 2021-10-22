package Lesson2.HomeWork2;

public class MyArrayDataException extends NumberFormatException {
    public int i;
    public int j;

    MyArrayDataException(int i, int j) {
        this.i = i;
        this.j = j;

    }
}
