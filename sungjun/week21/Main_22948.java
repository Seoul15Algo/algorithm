package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_22948 {
	static int N, start, end, meetPoint;
	static ArrayList<Circle> circles;
	static boolean[] visited;
	static ArrayList<Integer> result;
	
	static class Circle implements Comparable<Circle> {
		int k;
		int x;
		int r;
		
		public Circle(int k, int x, int r) {
			super();
			this.k = k;
			this.x = x;
			this.r = r;
		}
		
		@Override
		public int compareTo(Circle o) {
			return Integer.compare(this.r, o.r);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		circles = new ArrayList<>();
		result = new ArrayList<>();
		visited = new boolean[N+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int k = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			circles.add(new Circle(k, x, r));
		}
		
		st = new StringTokenizer(br.readLine());
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		circles.add(new Circle(0, 0, 1010000));		// 좌표평면
		
		Collections.sort(circles);	// 반지름을 기준으로 정렬
		
		ArrayList<Integer> front = bfs(start);
		ArrayList<Integer> back = bfs(end);
		
		for (int i = 0; i < front.size(); i++) {
			if(front.get(i) == meetPoint) break;	// meetPoint를 만나기 전까지 추가
			result.add(front.get(i));
		}
				
		for (int i = back.size()-1; i >= 0; i--) {
			result.add(back.get(i));	// meetPoint를 포함하여 역순으로 전부 다 추가
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(result.size()).append("\n");
		
		for (int i = 0; i < result.size(); i++) {
			sb.append(result.get(i)).append(" ");
		}
		
		System.out.println(sb);
	}
	
	private static ArrayList<Integer> bfs(int startCircle) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		ArrayList<Integer> order = new ArrayList<>();
		
		int startIdx = 0;
		
		for (int i = 0; i < circles.size(); i++) {
			int n = circles.get(i).k;
			if(startCircle == n) {
				startIdx = i;	// 출발 원의 인덱스
				break;
			}
		}
		
		pq.add(startIdx);	// 출발 인덱스를 큐에 넣음
		order.add(startCircle);		// 출발 원의 번호를 순서 리스트에 추가
		
		if(visited[startIdx]) return order;		// 이미 방문한 원이면 지금까지의 순서를 반환
		visited[startIdx] = true;
		
		while(!pq.isEmpty()) {
			int x = pq.poll();
			
			for (int i = x; i < circles.size(); i++) {
				// 현재 원이 다음 원에 포함되는 관계라면
				if(getType(circles.get(x), circles.get(i)) == 0) {
					if(!visited[i]) {	// 다음 원이 아직 방문하지 않은 원이라면
						visited[i] = true;	
						pq.offer(i);
						order.add(circles.get(i).k);	// 순서에 다음 원의 번호 추가
						
						break;
					}
					
					meetPoint = circles.get(i).k;	// 이미 방문한 원이라면 가운데 지점으로 저장 (순서 리스트 이어 붙일 때 사용)
					order.add(meetPoint);
					return order;	// 지금까지의 순서 반환하고 종료
				}
			}
		}
		
		return order;
	}
	
	private static int getType(Circle a, Circle b) {
		int d = Math.abs(a.x - b.x);
		
		if(Math.abs(a.r - b.r) > d) return 0;	// A가 B에 포함됨
		if(a.r + b.r < d) return 1;		// B가 A에 포함됨
		
		return 2;
	}
}
