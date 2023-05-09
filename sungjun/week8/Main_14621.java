package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_14621 {
	static int N, M;
	static char[] gender;
	static Edge[] edges;
	static int[] parent;
	static int result = 0;
	
	static class Edge implements Comparable<Edge>{
		int s;
		int e;
		int v;
		
		public Edge(int s, int e, int v) {
			super();
			this.s = s;
			this.e = e;
			this.v = v;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.v, o.v);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		gender = new char[N+1];
		edges = new Edge[M];
		
		for (int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
				
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i < N+1; i++) {
			gender[i] = st.nextToken().charAt(0);
		}
		
		// 입력받은 데이터를 바탕으로 간선 생성
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(s, e, v);
		}
		
		// 가중치에 따라 오름차순 정렬
		Arrays.sort(edges);
				
		// 크루스칼 알고리즘
		for (int i = 0; i < M; i++) {
			if(find(edges[i].s) != find(edges[i].e)) {
				// 남초에서는 여초로, 여초에서는 남초로만 이동 가능
				if(gender[edges[i].s] != gender[edges[i].e]) {
					union(edges[i].s, edges[i].e);
					result += edges[i].v;
				}
			}
		}
		
		// 모든 학교가 이어졌는지 체크하기 위한 Set
		Set<Integer> linked = new HashSet<>();
		
		for (int i = 1; i < N+1; i++) {
			linked.add(find(parent[i]));
		}
		
		// Set의 크기가 1이라면 모든 학교가 이어진 것이고, 아니라면 이어지지 않은 학교가 존재
		System.out.println(linked.size() == 1 ? result : -1);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) {
			parent[y] = x;
			return;
		}
		
		parent[x] = y;
		return;
	}

	private static int find(int x) {
		if(x == parent[x]) return x;
		return find(parent[x]);
	}
}