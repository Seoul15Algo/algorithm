package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_2887 {
	static int N;
	static Planet[] planets;
	static ArrayList<Edge> edges;
	static boolean[] visited;
	static int[] parent;
	static int result = 0;
	
	static class Planet {
		int num;
		int x;
		int y;
		int z;
		
		public Planet(int num, int x, int y, int z) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int v;
		
		public Edge(int from, int to, int v) {
			super();
			this.from = from;
			this.to = to;
			this.v = v;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.v, o.v);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		planets = new Planet[N];
		edges = new ArrayList<>();
		visited = new boolean[N];
		parent = new int[N+1];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			planets[i] = new Planet(i+1, x, y, z);
		}
		
		for (int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		
		// x좌표 오름차순 정렬
		Arrays.sort(planets, (p1, p2) -> Integer.compare(p1.x, p2.x));
		
		// x좌표 거리 간선 추가
		for (int i = 0; i < N-1; i++) {
			int v = Math.abs(planets[i].x - planets[i+1].x);
			
			edges.add(new Edge(planets[i].num, planets[i+1].num, v));
		}
		
		// y좌표 오름차순 정렬
		Arrays.sort(planets, (p1, p2) -> Integer.compare(p1.y, p2.y));
		
		// y좌표 거리 간선 추가
		for (int i = 0; i < N-1; i++) {
			int v = Math.abs(planets[i].y - planets[i+1].y);
			
			edges.add(new Edge(planets[i].num, planets[i+1].num, v));
		}
		
		// z좌표 오름차순 정렬
		Arrays.sort(planets, (p1, p2) -> Integer.compare(p1.z, p2.z));
		
		// z좌표 거리 간선 추가
		for (int i = 0; i < N-1; i++) {
			int v = Math.abs(planets[i].z - planets[i+1].z);
			
			edges.add(new Edge(planets[i].num, planets[i+1].num, v));
		}
		
		// 전체 간선 가중치 순으로 오름차순 정렬
		Collections.sort(edges);
		
		// 크루스칼
		for (int i = 0; i < edges.size(); i++) {
			if(find(edges.get(i).from) != find(edges.get(i).to)) {
				union(edges.get(i).from, edges.get(i).to);
				result += edges.get(i).v;
			}
		}
				
		System.out.println(result);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) {
			parent[x] = y;
		} else {
			parent[y] = x;
		}
	}

	private static int find(int x) {
		if(x == parent[x]) return x;
		return find(parent[x]);
	}
}