package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14621 {
	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int w;
		
		public Edge(int s, int e, int w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
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
		
		char[] university = new char[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			university[i] = st.nextToken().charAt(0);
		}
		
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());

			if (university[s] == university[e])
				continue;
			
			// 다른 성별인 경우 도로 후보 추가
			edges.add(new Edge(s, e, w));
		}
		
		P = new int[N];
		R = new int[N];
		for (int i = 0; i < N; i++) {
			P[i] = i;
			R[i] = 1;
		}
		
		int count = 0;
		int result = 0;
		while (!edges.isEmpty()) {
			if (count == N-1)
				break;
			
			Edge edge = edges.poll();
			if (union(edge.s, edge.e)) {
				result += edge.w;
				count++;
			}
		}
		
		if (count < N-1)
			result = -1;
		
		System.out.println(result);
		
		br.close();
	}
}