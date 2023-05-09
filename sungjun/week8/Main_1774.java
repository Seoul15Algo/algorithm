package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1774 {
	static int N, M;
	static int[] parent;
	static int[][] coords;
	static ArrayList<Edge> edges;
	static double result = 0;
	
	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		double v;
		
		public Edge(int s, int e, double v) {
			super();
			this.s = s;
			this.e = e;
			this.v = v;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.v, o.v);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		coords = new int[N][2];
		parent = new int[N+1];
		edges = new ArrayList<>();
		
		for (int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			coords[i][0] = Integer.parseInt(st.nextToken());
			coords[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 주어진 좌표들로 두 좌표 사이의 거리를 가중치로 갖는 간선 생성
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				edges.add(new Edge(i+1, j+1, 
						Math.sqrt(Math.pow(Math.abs(coords[i][0] - coords[j][0]), 2) 
								+ Math.pow(Math.abs(coords[i][1] - coords[j][1]), 2))));
			}
		}
		
		// 가중치에 따라 오름차순 정렬
		Collections.sort(edges);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// 크루스칼 알고리즘
		for (int i = 0; i < edges.size(); i++) {
			if(find(edges.get(i).s) != find(edges.get(i).e)) {
				union(edges.get(i).s, edges.get(i).e);
				result += edges.get(i).v;
			}
		}
		
		System.out.printf("%.2f", result);
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