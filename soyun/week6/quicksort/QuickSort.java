package com.ssafy.algo230307_Sort.soyun.week6.quicksort;

import java.util.Arrays;

public class QuickSort {

    private int[] array;

    public QuickSort() {
        this(new int[] { 5, 7, 3, 1, 2, 6, 8, 4 });
    }

    public QuickSort(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    public void sort() {

        quickSort(0, array.length - 1);
    }

    private void quickSort(int start, int end){
        // 파티션을 나눈다
        int part = partition(start, end);
        // 나눈 파티션에서 quickSort() 진행
        if (start < part - 1) quickSort(start, part - 1);
        if (end > part) quickSort(part, end);
    }

    private int partition(int start, int end){
        int pivot = array[(start + end) / 2];   // pivot 값은 시작점과 끝점의 중점
        while (start <= end){

            // 좌측: pivot 보다 큰 값을 찾음
            while (array[start] < pivot){
                start++;
            }

            // 우측: pivot 보다 작은 값을 찾음
            while (array[end] > pivot){
                end--;
            }

            // 두 값을 찾았다면, 두 값을 swap
            if (start <= end){
                swap(start, end);
                start++;
                end--;
            }
            // 위 과정을 반복한다
        }
        return start;
    }

    private void swap(int start, int end){

        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;
    }

    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "[array=" + Arrays.toString(array) + "]";
    }
}
