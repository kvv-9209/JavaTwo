package lesson5.HomeWork5;

import java.util.Arrays;

public class HowManyTimeApp {
    private static final int SIZE = 10_000_000;

    public static void main(String[] args) throws InterruptedException {
        firstMethod();
        secondMethod();
    }

    public static void arrayFill(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public static void firstMethod() {
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;
        long startTime = System.currentTimeMillis();
        arrayFill(arr);
        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    public static void secondMethod() throws InterruptedException {

        float[] initialArray = new float[SIZE];
        for (int i = 0; i < initialArray.length; i++) initialArray[i] = 1.0f;
        long startTime = System.currentTimeMillis();
        float[] leftHalf = new float[SIZE / 2];
        float[] rightHalf = new float[SIZE / 2];
        System.arraycopy(initialArray, 0, leftHalf, 0, (SIZE / 2));
        System.arraycopy(initialArray, (SIZE / 2), rightHalf, 0, (SIZE / 2));

        Thread thread1 = new Thread(() -> {
            arrayFill(leftHalf);
        });
        Thread thread2 = new Thread(() -> {
            arrayFill(rightHalf);
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        float[] mergedArray = new float[SIZE];
        System.arraycopy(leftHalf, 0, mergedArray, 0, (SIZE / 2));
        System.arraycopy(rightHalf, 0, mergedArray, (SIZE / 2), (SIZE / 2));

        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}










