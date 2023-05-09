package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main_1655 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 중간값보다 같거나 작은 수를 담는 PQ. 내림차순 정렬
		PriorityQueue<Integer> max = new PriorityQueue<Integer>(Collections.reverseOrder());
		// 중간값보다 큰 수를 담는 PQ. 오름차순 정렬
		PriorityQueue<Integer> min = new PriorityQueue<Integer>(); 
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 처음 데이터를 입력하는 경우, 작은 수를 담는 PQ에 데이터 추가
			if (max.size() == 0) {
				max.add(num);
				
				System.out.println(max.peek());
				continue;
			}
			
			// 입력값이 중간값보다 크다면 큰 수를 담는 PQ에 추가
			if (max.peek() < num) {
				min.add(num);
			} else { // 입력값이 중간값보다 같거나 작다면 작은 수를 담는 PQ에 추가
				max.add(num);
			}
			
			// 큰 수를 담는 PQ의 크기가 작은 수를 담는 PQ의 크기보다 크다면, 큰 수를 담는 PQ의 가장 작은 값을 작은 수를 담는 PQ로 이동
			if (max.size() < min.size()) {
				max.add(min.poll());
			}else {
				// 큰 수를 담는 PQ의 크기와 작은 수를 담는 PQ의 크기의 차가 1보다 크다면, 작은 수를 담는 PQ의 가장 큰 값을 큰 수를 담는 PQ로 이동
				if (max.size() - min.size() > 1)
					min.add(max.poll());
			}
			
			sb.append(max.peek() + "\n");
		}
		
		System.out.println(sb);
	}

}