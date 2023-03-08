package com.ssafy.algo230307_Sort.soyun.week6.mergesort;


public class MergeSortTest {

    public static void main(String[] args) throws Exception {

        MergeSort array = new MergeSort();

        array.printArray(); // 정렬 전
        long beforeTime = System.nanoTime();
        array.sort();
        long afterTime = System.nanoTime();
        long diffTime = (afterTime - beforeTime);
        array.printArray(); // 정렬 후
        System.out.println("Merge Sort: " + diffTime + "ns");
    }
}
