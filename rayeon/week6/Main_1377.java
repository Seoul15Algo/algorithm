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

			if (i - num.idx >= 0) // 정렬 과정에 의해 뒤로 밀려난 수
				continue;
			
			// 인덱스가 가장 많이 감소한 값이 정렬 과정에 의해 뒤로 밀려난 수의 개수(정렬이 수행된 횟수)
			cnt = Math.min(cnt, i - num.idx);
		}
	
		System.out.println(Math.abs(cnt)+1);
		
		br.close();
	}
}