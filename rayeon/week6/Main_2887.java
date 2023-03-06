package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * @rt3310 
 */

public class Main_2887 {
	static int N;
	static int[] P;
	static int[] R;
	static ArrayList<Planet> planets;
	static PriorityQueue<Edge> edges;
	
	static class Planet {
		int idx;
		int x, y, z;

		public Planet(int idx, int x, int y, int z) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		long dist;
		
		public Edge(int s, int e, long dist) {
			this.s = s;
			this.e = e;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.dist, o.dist);
		}
	}
	
	static void makeSet() {
		P = new int[N];
		R = new int[N];
		
		for (int i = 0; i < N; i++) {
			P[i] = i;
			R[i] = 1;
		}
	}
	
	static int find(int x) {
		if (P[x] == x)
			return P[x];
		
		return P[x] = find(P[x]);
	}
	
	static boolean union(int s, int e) {
		int ps = find(s);
		int pe = find(e);
		
		if (ps == pe)
			return false;
		
		if (R[ps] < R[pe]) {
			P[ps] = pe;
			R[pe] += R[ps];
		} else {
			P[pe] = ps;
			R[ps] += R[pe];
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		planets = new ArrayList<>();
		edges = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			planets.add(new Planet(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		// x 기준 정렬
		planets.sort(new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return Integer.compare(o1.x, o2.x);
			}
		});
		// x 값이 가까운 행성들을 연결하는 간선 생성
		for (int i = 0; i < N-1; i++) {
			edges.offer(new Edge(planets.get(i).idx, planets.get(i+1).idx, Math.abs(planets.get(i).x - planets.get(i+1).x)));
		}
		
		// y 기준 정렬
		planets.sort(new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return Integer.compare(o1.y, o2.y);
			}
		});
		// y 값이 가까운 행성들을 연결하는 간선 생성
		for (int i = 0; i < N-1; i++) {
			edges.offer(new Edge(planets.get(i).idx, planets.get(i+1).idx, Math.abs(planets.get(i).y - planets.get(i+1).y)));
		}
		
		// z 기준 정렬
		planets.sort(new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return Integer.compare(o1.z, o2.z);
			}
		});
		// x 값이 가까운 행성들을 연결하는 간선 생성
		for (int i = 0; i < N-1; i++) {
			edges.offer(new Edge(planets.get(i).idx, planets.get(i+1).idx, Math.abs(planets.get(i).z - planets.get(i+1).z)));
		}
		
		int cnt = 0;
		int result = 0;
		
		makeSet();
		while (cnt < N-1) {
			Edge edge = edges.poll();
			
			if (union(edge.s, edge.e)) {
				cnt++;
				result += edge.dist;
			}
		}
		
		System.out.println(result);
		
		br.close();
	}
}
