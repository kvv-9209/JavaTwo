import lesson14.HomeWork14.ArrayApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArrayTest {
private ArrayApp arr;
    @BeforeEach
    public void init() {
        arr = new ArrayApp();
    }

    int[] array = new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7};
    int[] result = new int[]{1, 7};
    int[] array1 = new int[]{1, 2, 4, 4, 2, 3};
    int[] result1 = new int[]{2, 3};
    int[] array2 = new int[]{1, 2, 2, 3, 1, 7};

    @Test
    void testFourNextMethodFirst() {
        Assertions.assertEquals(Arrays.toString(result), Arrays.toString(arr.arrayFourNext(array)));
    }
    @Test
    void testFourNextMethodSecond() {
        Assertions.assertEquals(Arrays.toString(result1), Arrays.toString(arr.arrayFourNext(array1)));
    }
    @Test
    public void checkSomething() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Assertions.assertEquals(0, Arrays.toString(arr.arrayFourNext(array2)));
        });
    }


}
