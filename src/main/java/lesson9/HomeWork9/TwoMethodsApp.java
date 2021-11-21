package lesson9.HomeWork9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoMethodsApp {

    public static void main(String[] args) {

        String[] arr = new String[]{"1", "2", "3", "4"};
        swapPlaces(arr, 1, 3);
        Integer[] arrInt = new Integer[]{5, 6, 8, 7, 2};
        swapPlaces(arrInt, 0, 4);
        arrayToArrayList(arr);


    }

    public static <K> void swapPlaces(K[] array, int i, int j) {
        K buf1;
        K buf2;

        System.out.println(Arrays.toString(array));

        buf1 = array[i];
        buf2 = array[j];

        array[i] = buf2;
        array[j] = buf1;

        System.out.println(Arrays.toString(array));

    }

    public static <V> Object arrayToArrayList(V[] array) {

        List<V> list = new ArrayList<>(Arrays.asList(array));

        return list;
    }
}
