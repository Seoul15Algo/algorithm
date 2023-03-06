package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1377 {
	static class Num implements Comparable<Num> {
		int data; // ��
		int idx; // �ʱ� �ε���
		
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

			if (i - num.idx >= 0) // ���� �������� �ڷ� �з����� �� ���
				continue;
			
			// �ε����� ���� ũ�� ������ ��(���� �ڷ� ���� �ʴ� ��)�� ���밪�� ���� ���� ���� �ڷ� �� ���� ����(���� ���� Ƚ��)�� ����.
			cnt = Math.min(cnt, i - num.idx);
		}
	
		System.out.println(Math.abs(cnt)+1);
		
		br.close();
	}
}