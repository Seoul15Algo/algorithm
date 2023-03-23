package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1774 {
	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		double w;
		
		public Edge(int s, int e, double w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}
	
	static int[][] pos;
	static int[] P;
	static int[] R;
	
	static int find(int x) {
		if (x == P[x])
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		pos = new int[N][2]; // 좌표 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
		}

		P = new int[N];
		R = new int[N];
		for (int i = 0; i < N; i++) {
			P[i] = i;
			R[i] = 1;
		} 
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;

			union(s, e); // 이미 연결된 통로
		}
		
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				double dist = Math.sqrt(Math.pow(Math.abs(pos[i][0] - pos[j][0]), 2) + Math.pow(Math.abs(pos[i][1] - pos[j][1]), 2));
				
				// 통로 후보 삽입
				edges.add(new Edge(i, j, dist));
			}
		}

		double result = 0;
		while(!edges.isEmpty()) {
			Edge edge = edges.poll();
			
			if (union(edge.s, edge.e)) {
				result += edge.w;
			}
		}

		System.out.printf("%.2f\n", result);
		
		br.close();
	}
}