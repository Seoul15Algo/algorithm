package com.ssafy.algo230228_DataStructure.soyun.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1655 {

    static int n;
    static PriorityQueue<Integer> increase; // MinHeap -> 오름차순 정렬
    static PriorityQueue<Integer> decrease; // MaxHeap -> 내림차순 정렬

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        increase = new PriorityQueue<>();
        decrease = new PriorityQueue<>((o1, o2) -> -1 * Integer.compare(o1, o2));
        
        for (int i = 0; i < n; i++) {
            // 두 큐의 크기가 다른 상태라면 -> MinHeap 에 offer
            if (i % 2 == 1){
                increase.offer(Integer.parseInt(br.readLine()));
            }
            
            // 두 큐의 크기가 같은 상태라면 -> MaxHeap 에 offer
            if (i % 2 == 0) {
                decrease.offer(Integer.parseInt(br.readLine()));
            }

            // 숫자가 단 한 개만 들어왔을 때 처리
            if (increase.isEmpty() || decrease.isEmpty()){
                sb.append(decrease.peek()).append("\n");
                continue;
            }

            // 만약 MinHeap 의 최솟값이 MaxHeap 의 최댓값보다 작다면 swap 해준다
            if (increase.peek() < decrease.peek()) {
                int temp = decrease.poll();
                decrease.offer(increase.poll());
                increase.offer(temp);
            }
            sb.append(decrease.peek()).append("\n");
        }
        System.out.println(sb.toString());

    }
}
