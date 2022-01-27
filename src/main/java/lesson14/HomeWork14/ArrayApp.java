package lesson14.HomeWork14;

import java.util.Arrays;

public class ArrayApp {


    public int[] arrayFourNext(int[] arr)throws RuntimeException {

        if (arr.length == 0) { throw new NullPointerException(); }

        int elementFourNext = -1;
        for (int i = 0; i < arr.length; i++){
            if(arr[i] == 4){
                elementFourNext = i + 1;
            }
        }

        if (elementFourNext == -1){
            throw new RuntimeException();
        }
        else{
            return Arrays.copyOfRange(arr, elementFourNext, arr.length);
        }

    }

    public boolean checkArrayOneAndFour(int[] arr) {
        int countOne = 0, countFour = 0;
        for (int x : arr) {
            if (x == 1){
                countOne++;
            }
            else if (x == 4){
                countFour++;
            }
        }
        return (countOne > 0 && countFour > 0);
    }
}
