package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1377 {
	static class Num implements Comparable<Num> {
		int data; // 값
		int idx; // 초기 인덱스
		
		public Num(int data, int idx) {
			this.data = data;
			this.idx = idx;
		}

		@Override
		public int compareTo(Num o) {
			if (this.data == o.data) 
				return Integer.compare(this.idx, o.idx); 
			return Integer.compare(this.data, o.data); 
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Num> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pq.offer(new Num(Integer.parseInt(br.readLine()), i));
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			Num num = pq.poll();

			if (i - num.idx >= 0) // 정렬 과정에서 뒤로 밀려나게 된 경우
				continue;
			
			// 인덱스가 가장 크게 감소한 값(절대 뒤로 가지 않는 수)의 절대값이 정렬 과정 동안 뒤로 간 수의 개수(정렬 수행 횟수)와 같다.
			cnt = Math.min(cnt, i - num.idx);
		}
	
		System.out.println(Math.abs(cnt)+1);
		
		br.close();
	}
}