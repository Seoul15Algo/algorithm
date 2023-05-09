import java.util.Arrays;

public class HeapSort {

    private int[] array;

    public HeapSort() {
        this(new int[] { 5, 7, 3, 1, 2, 6, 8, 4 });
    }

    public HeapSort(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    // 구현 - 필요하면 매개변수와 메서드를 추가해도 됩니다.
    public void sort() {

    }
    //

    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "HeapSort [array=" + Arrays.toString(array) + "]";
    }
}
