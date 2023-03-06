package com.ssafy.algo230307_Sort.soyun.week6.quicksort;

public class QuickSortTest {

    public static void main(String[] args) throws Exception {

        QuickSort array = new QuickSort();

        array.printArray(); // 정렬 전
        long beforeTime = System.nanoTime();
        array.sort();
        long afterTime = System.nanoTime();
        long diffTime = (afterTime - beforeTime);
        array.printArray(); // 정렬 후
        System.out.println("Quick Sort: " + diffTime + "ns");
    }
}
