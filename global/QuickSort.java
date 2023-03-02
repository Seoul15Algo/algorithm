import java.util.Arrays;

public class QuickSort {

    private int[] array;

    public QuickSort(int[] array) {
        Arrays.copyOf(array, array.length);
    }

    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "QuickSort [array=" + Arrays.toString(array) + "]";
    }
}
