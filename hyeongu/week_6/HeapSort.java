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
        int n = array.length;

        for(int i = n / 2 - 1; i >= 0; i--){
            Heap(n, i);
        }

        for(int i = n - 1; i > 0; i--){
            swap(0, i);
            Heap(i, 0);
        }
    }
    //

    private void Heap(int n, int i){
        int parent = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;

        if(left < n && array[parent] < array[left]){
            parent = left;
        }
        if(right < n && array[parent] < array[right]){
            parent = right;
        }

        if(i != parent){
            swap(parent, i);
            Heap(n, parent);
        }
    }

    private void swap(int a, int b){
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "HeapSort [array=" + Arrays.toString(array) + "]";
    }
}
