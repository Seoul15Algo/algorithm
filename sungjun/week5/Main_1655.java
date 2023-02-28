package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main_1655 {
	static int N;
	// 왼쪽 큐는 내림차순 정렬, peek 실행 시 가장 큰 값을 조회할 수 있도록 설정
	static PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
	// 오른쪽 큐는 오름차순 정렬, peek 또는 poll 실행 시 가장 작은 값을 조회할 수 있도록 설정
	static PriorityQueue<Integer> right = new PriorityQueue<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 왼쪽 큐를 오른쪽 큐의 크기보다 항상 크거나 같게 유지
			if(left.size() <= right.size()) {
				left.offer(num);
			} 
			
			if(left.size() > right.size()) {
				right.offer(num);
			}
			
			// 첫번째 수가 아니라면
			if(i != 0) {
				// 왼쪽 큐의 가장 큰 값이 오른쪽 큐의 가장 작은 값보다 크다면
				if(left.peek() > right.peek()) {
					// 두 수 스위칭
					int tmp = right.poll();
					right.offer(left.poll());
					left.offer(tmp);
				}
			}
			
			// 왼쪽 큐의 가장 큰 값이 곧 중간값
			sb.append(left.peek() + "\n");
		}
		
		System.out.println(sb);
	}
}
