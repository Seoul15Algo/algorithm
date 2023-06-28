package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1368 {
	static int N, result;
	static int[] well, parent;
	static int[][] cost;
	static PriorityQueue<Field> pq;
	
	static class Field implements Comparable<Field> {
		int s;
		int e;
		int v;
		
		public Field(int s, int e, int v) {
			super();
			this.s = s;
			this.e = e;
			this.v = v;
		}
		
		@Override
		public int compareTo(Field o) {
			return this.v - o.v;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		well = new int[N+1];
		parent = new int[N+1];
		cost = new int[N+1][N+1];
		pq = new PriorityQueue<>();
		
		for (int i = 1; i < N+1; i++) {
			well[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Field(0, i, well[i]));	// 논에 우물을 파는 경우를 따로 추가
			
			for (int j = 1; j < N+1; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
				
				if(i == j) continue;	// 자기 자신으로 연결하는 경우는 무의미
				pq.add(new Field(i, j, cost[i][j]));	// 물 끌어오는 비용 추가
			}
		}
		
		for (int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		
		while(!pq.isEmpty()) {
			Field cur = pq.poll();
			if(find(cur.s) == find(cur.e)) continue;
			
			union(cur.s, cur.e);
			result += cur.v;
		}
		
		System.out.println(result);
	}


	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		parent[x] = y;
	}

	private static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}

}
