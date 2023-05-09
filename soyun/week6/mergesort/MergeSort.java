package com.ssafy.algo230307_Sort.soyun.week6.mergesort;

import java.util.Arrays;

public class MergeSort {

    private int[] array;
    private int[] temp;

    public MergeSort() {
        this(new int[] { 5, 7, 3, 1, 2, 6, 8, 4 });
    }

    public MergeSort(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        this.temp = new int[array.length];
    }


    public void sort() {

        mergeSort(0, array.length - 1);
    }

    // 배열을 두쪽으로 나누어가며 정렬 진행
    // start: 정렬을 진행할 배열의 시작 index
    // end: 마지막으로 포함될 배열의 index
    private void mergeSort(int start, int end){

        // 배열을 분할할 수 있는 상태라면
        if (start < end){

            int mid = (start + end) / 2;

            // 분할한 배열에서도 mergeSort 진행 - Divide
            mergeSort(start, mid);
            mergeSort(mid + 1, end);


            // 분할한 배열이 정렬된 상태라면 합침 - Conquer & Combine
            int p = start;  // 분할 배열1의 첫번째 index
            int q = mid + 1;    // 분할 배열2의 첫번째 index
            int idx = p;    // 분할된 배열을 저장하기 시작할 위치

            // 두 배열의 끝을 넘어서면 종료 - 모두 합쳐진 상태
            while (p <= mid || q <= end) {
                // 1. 분할 배열2의 원소를 이미 다 가져온 경우 -> 분할 배열1에 남아있는 원소들을 가져오면 됨
                // 2. 분할 배열1, 2 모두 가져오지 않은 원소가 있으며, 분할 배열1의 원소의 값이 더 작은 경우
                if (q > end || (p <= mid && array[p] < array[q])) {
                    temp[idx++] = array[p++];
                }
                // 위와 동일하나, 분할 배열2 기준임
                else {
                    temp[idx++] = array[q++];
                }
            }

            // temp 에 저장된 값을 array 에 copy
            for (int i = start; i <= end; i++) {
                array[i] = temp[i];
            }
        }
    }

    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "[array=" + Arrays.toString(array) + "]";
    }
}
