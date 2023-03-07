package week6;

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

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(0, i);
            heapify(i, 0);
        }
    }

    public void heapify(int n, int i) {
        int parent = i;
        int leftChild = i * 2 + 1;
        int rightChild = i * 2 + 2;

        if (leftChild < n && array[parent] < array[leftChild]) {
            parent = leftChild;
        }

        if (rightChild < n && array[parent] < array[rightChild]) {
            parent = rightChild;
        }

        if (i != parent) {
            swap(parent, i);
            heapify(n, parent);
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
