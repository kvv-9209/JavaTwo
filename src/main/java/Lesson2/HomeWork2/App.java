package Lesson2.HomeWork2;


import java.util.Arrays;

public class App {
    public final static int X = 4;
    public final static int Y = 4;
    public static String[][] arr = new String[][]{
            {"00", "01", "02", "03"},
            {"10", "11", "12", "13"},
            {"20", "21", "22", "23"},
            {"30", "31", "32", "ЗЗ"}
    };

    public static void main(String[] args) {
        try {
            int arraySumm = dimArrayFixSumm(arr);
            System.out.println("Сумма всех элементов массива: " + arraySumm);
        } catch (MyArraySizeException re) {
            System.out.println(re.getMessage());
        } catch (MyArrayDataException de) {
            System.out.println("Не верный формат значения в строке " + (de.i + 1) + " столбце " + (de.j + 1));
        }
    }

    public static int dimArrayFixSumm(String[][] array) {
        int arrayValue = 0;
        for (int i = 0; i < X; i++) {
            if (array[i].length != Y) {
                throw new MyArraySizeException("Не корректный размер массива, должен быть размером: " + X + "x" + Y);
            }
            for (int j = 0; j < Y; j++) {
                try {
                    arrayValue += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException nfe) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return arrayValue;
    }
}
