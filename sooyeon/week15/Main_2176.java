import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2176 {
	static int N,M;
	static List<Edge>[] list;
	static int[] dist;
	static int[] dp;
	static boolean[] checked;
	static class Edge implements Comparable<Edge>{
		int v;
		int w;
		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for (int i = 1; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list[s].add(new Edge(e,v));
			list[e].add(new Edge(s,v));
		}
		
		dist = new int[N+1];
		dp = new int[N+1];
		dijkstra();
		
		dp();
		
		System.out.println(dp[1]);
	}
	static void dp() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 1; i < N+1; i++) {
			if(i==2) continue;
			pq.offer(new Edge(i,dist[i]));
		}
		
		dp[2] = 1;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			for (Edge edge : list[cur.v]) {
				//현재 노드에 연결되어 있는 노드의 최단경로가 짧다면 연결되어있는 노드의 가짓수를 더해줌
				if(dist[edge.v] < dist[cur.v]) {
					dp[cur.v] += dp[edge.v];
				}
			}
			
			if(cur.v == 1) break;
		}
		
		
	}
	private static void dijkstra() {
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		checked = new boolean[N+1];
		
		dist[2] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(2,0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(checked[cur.v] == true) continue;
			checked[cur.v] = true;
			
			for (Edge next : list[cur.v]) {
				if(dist[next.v] > dist[cur.v] + next.w) {
					dist[next.v] = dist[cur.v] + next.w;
					pq.offer(new Edge(next.v, dist[next.v]));
				}
				
			}
		}	
	}

}
