package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13334 {
	static int N, result, D;
	static Pos[] coords;
	static PriorityQueue<Integer> pq;
	
	static class Pos implements Comparable<Pos> {
		int start;	// 시작점
		int end;	// 끝점
		
		public Pos(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.end, o.end);	// 끝점 기준으로 정렬될 수 있도록
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		coords = new Pos[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int house = Integer.parseInt(st.nextToken());
			int office = Integer.parseInt(st.nextToken());
			
			// 집이 사무실보다 항상 앞쪽에 있는 것이 아니기 때문에 집과 사무실로 구분하지 않고 더 먼저 오는것과 나중에 오는 것으로 구분
			int start = Math.min(house, office);
			int end = Math.max(house, office);
						
			coords[i] = new Pos(start, end);
		}
		
		D = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		
		Arrays.sort(coords);	// 정렬
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			// 현재 끝점과의 거리가 철로의 길이를 초과하는 값들을 pq에서 제거
			while(!pq.isEmpty() && coords[i].end - pq.peek() > D) {
				pq.poll();
				count--;
			}
			
			// 끝점과 시작점의 차이가 철로의 길이를 넘지 않는 경우 pq에 추가
			if(coords[i].end-coords[i].start <= D) {
				pq.add(coords[i].start);
				count++;
			}
			
			result = Math.max(result, count);
		}
		
		System.out.println(result);
	}

}