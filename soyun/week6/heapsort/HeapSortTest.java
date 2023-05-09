package com.ssafy.algo230307_Sort.soyun.week6.heapsort;


public class HeapSortTest {
    public static void main(String[] args) throws Exception {

        HeapSort array = new HeapSort();

        array.printArray(); // 정렬 전
        long beforeTime = System.nanoTime();
        array.sort();
        long afterTime = System.nanoTime();
        long diffTime = (afterTime - beforeTime);
        array.printArray(); // 정렬 후
        System.out.println("Heap Sort: " + diffTime + "ns");
    }
}
