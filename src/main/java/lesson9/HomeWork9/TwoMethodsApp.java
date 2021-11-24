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

    }

    public static <K> void swapPlaces(K[] array, int i, int j) {
        System.out.println(Arrays.toString(array));

        K buf = array[i];
        array[i] = array[j];
        array[j] = buf;

        System.out.println(Arrays.toString(array));
    }

    public static <V> List<V> arrayToArrayList(V[] array) {

        List<V> list = new ArrayList<>(Arrays.asList(array));

        return list;
    }
}
