package com.ssafy.algo230307_Sort.soyun.week6.heapsort;

import java.util.Arrays;

public class HeapSort {

    private int[] array;    // 배열로 구현한 Heap 구조, max heap 기준
    private int size;

    public HeapSort() {
        this(new int[] { 5, 7, 3, 1, 2, 6, 8, 4 });
    }

    public HeapSort(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        this.size = array.length;
    }


    public void sort() {

        // 부모 노드와의 heapify 과정에서 음수가 발생한다면 -> ArrayIndexOutOfBounds Exception
        // 노드가 0개 혹은 1개일 경우에는 정렬할 필요가 없음, return
        if (size < 2){
            return;
        }

        int parentIdx = getParent(size - 1);

        for (int i = parentIdx; i >= 0; i--) {
            heapify(i, size - 1);
        }

        for (int i = size - 1; i > 0; i--){
            // root 노드인 0번째와 i번째 index 인 노드의 값을 교환
            // 0 ~ (i - 1) 까지의 서브트리에 대해 max heap 을 만족하도록 재구성
            swap(0, i);
            heapify(0, i - 1);
        }
    }

    // 부모 노드의 인덱스를 얻음
    private int getParent(int child){
        // 부모 노드 = (해당 노드 인덱스 - 1) / 2
        return (child - 1) / 2;
    }

    // 두 노드의 값을 교환
    private void swap(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void heapify(int parentIdx, int lastIdx){

        /*
            왼쪽 자식 노드 = 해당 노드 인덱스 * 2 + 1
            오른쪽 자식 노드 = 해당 노드 인덱스 * 2 + 2
         */
        int leftChildIdx;
        int rightChildIdx;
        int largestIdx;

        // 왼쪽 자식노드(오른쪽 자식노드가 없을 경우도 있으므로) 기준
        // 자식 노드의 index 가 마지막 index 를 넘을 때까지 반복
        while ((parentIdx * 2) + 1 <= lastIdx){
            leftChildIdx = (parentIdx * 2) + 1;
            rightChildIdx = (parentIdx * 2) + 2;
            largestIdx = parentIdx;

            // 왼쪽 자식 노드와 비교
            // if 문의 첫번째 조건의 경우 위의 while()에서 검사를 해주기 때문에 생략해도 무방
            if (leftChildIdx <= lastIdx &&
                array[leftChildIdx] > array[largestIdx]) {
                largestIdx = leftChildIdx;
            }

            // 오른쪽 자식 노드와 비교
            if (rightChildIdx <= lastIdx &&
                array[rightChildIdx] > array[largestIdx]
            ){
                largestIdx = rightChildIdx;
            }

            // 자식노드가 부모노드보다 클 경우
            // 자식노드가 부모 노드가 되도록 두 노드를 교체한다.
            if (largestIdx != parentIdx){
                swap(parentIdx, largestIdx);
                parentIdx = largestIdx;
                continue;
            }
            return;
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
