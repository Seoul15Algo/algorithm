package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1949 {
	static int N, result;
	static int[] population;
	static int[][] dp;
	static boolean[] visited;

	static ArrayList<Integer> town[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		dp = new int[N+1][2];
		town = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for (int i = 1; i < N+1; i++) {
			town[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i < N+1; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 양방향 노드 추가
			town[a].add(b);	
			town[b].add(a);
		}
				
		dfs(1);
		
		result = Math.max(dp[1][0], dp[1][1]);	// 시작 노드가 우수 마을일 때와 우수 마을이 아닐 때
		System.out.println(result);
	}
	
	public static void dfs(int n) {
		visited[n] = true;	// 방문 처리
		
		for (int i = 0; i < town[n].size(); i++) {
			int next = town[n].get(i);
			if(visited[next]) continue;		// 이미 방문한 곳은 스킵
			
			dfs(next);
			visited[next] = false;		// 방문 초기화
			
			// 이번 마을이 우수 마을이 아니라면 -> 다음 마을은 우수마을이어도 되고 아니어도 된다
			dp[n][0] += Math.max(dp[next][0], dp[next][1]);
			// 이번 마을이 우수 마을이라면 다음 마을은 무조건 우수 마을이 아니다
			dp[n][1] += dp[next][0];
		}
		
		// 이번 마을이 우수 마을이면 이번 마을의 인구를 더해준다
		dp[n][1] += population[n];
	}
}
