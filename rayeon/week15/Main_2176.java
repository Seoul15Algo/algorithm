package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ 2176 : 합리적인 이동경로
public class Main_2176 {
	static final int S = 0;
	static final int T = 1;
	static int N, M;
	static List<int[]>[] graph; 
	static int[] distance;
	static int[] dp;
	
	static void dijksta() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[1]);
			}
		});
		boolean[] visited = new boolean[N];
		
		pq.offer(new int[] {T, 0});
		distance[T] = 0;
		
		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			
			visited[T] = true;
			
			for (int[] next : graph[now[0]]) {
				if (visited[next[0]]) {
					continue;
				}
				
				int newDistance = next[1] + distance[now[0]]; 
				if (newDistance >= distance[next[0]]) {
					continue;
				}
				
				distance[next[0]] = newDistance;
				pq.offer(new int[] {next[0], newDistance});
			}
		}
	}
	
	static int dfs(int now) {
		dp[now] = 0;
		
		for (int[] next : graph[now]) {
			if (next[0] == T) {
				dp[now]++;
				continue;
			}
			
			if (distance[next[0]] < distance[now]) {
				if (dp[next[0]] > -1) {
					dp[now] += dp[next[0]];
				} else {
					dp[now] += dfs(next[0]);
				}
			}
		}
		
		return dp[now];
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	graph = new ArrayList[N];
    	for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<int[]>();
		}
    	
    	distance = new int[N];
    	Arrays.fill(distance, (int)1e9);
    	
    	dp = new int[N];
    	Arrays.fill(dp, -1);

    	for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			graph[s].add(new int[] {e, w});
			graph[e].add(new int[] {s, w});
		}
    	
    	dijksta();
    	dfs(S);
    	
    	System.out.println(dp[S]);
    	br.close();
    }
}

