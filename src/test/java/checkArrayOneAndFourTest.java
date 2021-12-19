import lesson14.HomeWork14.ArrayApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class checkArrayOneAndFourTest {
    private ArrayApp arr;
    @BeforeEach
    public void init() {
        arr = new ArrayApp();
    }
    int[] array = new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7};
    int[] array1 = new int[]{2, 4, 4, 2, 3};
    int[] array2 = new int[]{2, 2, 3, 7};
    int[] array3 = new int[]{2, 2, 3, 7, 1};

    @Test
    void testFourNextMethodFirst() {
        Assertions.assertEquals(true, arr.checkArrayOneAndFour(array));
    }
    @Test
    void testFourNextMethodSecond() {
        Assertions.assertEquals(false, arr.checkArrayOneAndFour(array1));
    }
    @Test
    void testFourNextMethodThird() {
        Assertions.assertEquals(false, arr.checkArrayOneAndFour(array2));
    }
    @Test
    void testFourNextMethodFourth() {
        Assertions.assertEquals(false, arr.checkArrayOneAndFour(array3));
    }

}
