package week6;

import java.util.Arrays;

public class MergeSort {

    private int[] array;

    public MergeSort() {
        this(new int[] { 5, 7, 3, 1, 2, 6, 8, 4 });
    }

    public MergeSort(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    // 구현 - 필요하면 매개변수와 메서드를 추가해도 됩니다.
    public void sort() {
        sort(0, 7);
    }

    public void sort(int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        sort(start, mid);
        sort(mid + 1, end);
        merging(start, mid, mid + 1, end);
    }

    public void merging(int start1, int end1, int start2, int end2) {
        int[] copy = new int[array.length];
        int s1 = start1;
        int s2 = start2;
        int count = start1;
        while (s1 <= end1 && s2 <= end2) {
            if (array[s1] < array[s2]) {
                copy[count++] = array[s1++];
                continue;
            }

            copy[count++] = array[s2++];
        }

        while (s1 <= end1) {
            copy[count++] = array[s1++];
        }
        while (s2 <= end2) {
            copy[count++] = array[s2++];
        }

        for (int i = start1; i <= end2; i++) {
            this.array[i] = copy[i];
        }
    }
    //

    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "MergeSort [array=" + Arrays.toString(array) + "]";
    }
}
