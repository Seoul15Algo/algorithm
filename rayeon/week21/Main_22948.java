package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ 22948: 원 이동하기 2
public class Main_22948 {
	static int N;
	static Circle[] circles;
	static int A, B;
	
	static class Circle implements Comparable<Circle>{
		int k;
		int x;
		int r;
		
		public Circle(int k, int x, int r) {
			this.k = k;
			this.x = x;
			this.r = r;
		}

		@Override
		public int compareTo(Circle o) {
			return Integer.compare(o.r, this.r);
		}
	}

	// 두 원이 겹치는지 확인
	public static boolean overlap(int c1, int c2) {
		if (Math.abs(circles[c1].x - circles[c2].x) > circles[c1].r + circles[c2].r) {
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		circles = new Circle[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int k = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			circles[k - 1] = new Circle(k, x, r);
		}
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken()) - 1;
		B = Integer.parseInt(st.nextToken()) - 1;
		
		// 각 원을 감싸고 있는 원들
		PriorityQueue<Circle> pqA = new PriorityQueue<>();
		PriorityQueue<Circle> pqB = new PriorityQueue<>();
		
		// 두 원 모두 0번 원 안에 있으므로 0번 원 추가
		pqA.offer(new Circle(0, 0, Integer.MAX_VALUE));
		pqB.offer(new Circle(0, 0, Integer.MAX_VALUE));
		
		for (int i = 0; i < N; i++) {
			if (overlap(A, i)) {
				// A와 i가 겹치면서 i 반지름이 A 반지름보다 크다면 i가 A를 감싸고 있다. 
				if (circles[A].r <= circles[i].r) {
					pqA.offer(circles[i]);
				}
			}
			
			if (overlap(B, i)) {
				if (circles[B].r <= circles[i].r) {
					pqB.offer(circles[i]);
				}
			}
		}
		
		int backgroundCircle = 0;
		// 두 원을 감싸는 원 중 가장 작은 원 찾기
		while (!pqA.isEmpty() && !pqB.isEmpty() && pqA.peek().k == pqB.peek().k) {
			backgroundCircle = pqA.poll().k;
			pqB.poll();
		}
		
		int count = 0;
		StringBuilder order = new StringBuilder();
		
		/*
			A에서 B로 이동하는 것이므로
			A를 감싸고 있는 작은 원들부터 차례대로 이동한 뒤
			두 원을 감싸는 가장 작은 원을 지나
			B를 감싸고 있는 큰 원에서 작은 원 순서대로 방문한다.
		 */
		Deque<Integer> dq = new ArrayDeque<>();
		// A를 감싸고 있는 원 중 가장 작은 원이 맨 앞에 올 수 있도록 앞에 추가
		while(!pqA.isEmpty()) { 
			count++;
			dq.addFirst(pqA.poll().k);
		}
		
		count++;
		// 두 원을 감싸는 원 중 가장 작은 원을 맨 뒤에 추가
		dq.addLast(backgroundCircle);
		
		// B를 감싸고 있는 작은 원들이 점점 뒤로 가도록 맨 뒤에 추가
		while(!pqB.isEmpty()) {
			count++;
			dq.addLast(pqB.poll().k);
		}
		
		while(!dq.isEmpty()) {
			order.append(dq.poll()).append(" ");
		}
		
		System.out.println(count);
		System.out.println(order);
		
		br.close();
	}
}

