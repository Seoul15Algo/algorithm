package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2176 {
	static int N, M, result;
	static ArrayList<Road> nodes[];
	static PriorityQueue<Road> pq;
	static int[] dist;
	static int[] dp;
	
	static class Road implements Comparable<Road> {
		int e;
		int v;
		
		public Road(int e, int v) {
			super();
			this.e = e;
			this.v = v;
		}

		@Override
		public int compareTo(Road o) {
			return this.v - o.v;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pq = new PriorityQueue<>();
		nodes = new ArrayList[N+1];
		dist = new int[N+1];
		dp = new int[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(dp, -1);
		
		dist[2] = 0;
		
		for (int i = 1; i < N+1; i++) {
			nodes[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			nodes[a].add(new Road(b, c));
			nodes[b].add(new Road(a, c));
		}
		
		initDist();
		
		System.out.println(dfs(1));
	}
	
	private static int dfs(int n) {
		if(dp[n] > -1) {
			return dp[n];
		}
		if(n == 2) {
			dp[n] = 1;
			return dp[n];
		}
		
		dp[n] = 0;
				
		for (Road road : nodes[n]) {
			int next = road.e;
			if(dist[n] > dist[next]) {
				dp[n] += dfs(next);
			}
		}
		
		return dp[n];
	}

	private static void initDist() {
		pq.add(new Road(2, 0));
		
		while(!pq.isEmpty()) {
			Road cur = pq.poll();
			int now = cur.e;
			
			if(cur.v > dist[now]) continue;
			
			for (Road road : nodes[now]) {
				int next = road.e;
				int distance = road.v;
				
				if(dist[next] > dist[now] + distance) {
					dist[next] = dist[now] + distance;
					pq.add(new Road(next, dist[next]));
				}
			}
		}
	}
}
