package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_20188 {
	static int N;
	static long result;
	static int[] dp;
	static ArrayList<Integer> road[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		road = new ArrayList[N+1];
		dp = new int[N+1];
		
		for (int i = 1; i < N+1; i++) {
			road[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			road[a].add(b);
			road[b].add(a);
		}
		
		solve(1);
		System.out.println(result);
	}

	private static int solve(int n) {
		dp[n] = 1;
		
		for (int i = 0; i < road[n].size(); i++) {
			int next = road[n].get(i);
			if(dp[next] > 0) continue;
			dp[n] += solve(next);
		}
		
		if(n != 1) {
			result += calc(N) - calc(N-dp[n]);
		}
		
		return dp[n];
	}

	private static long calc(int n) {
		return (long)n* (long)(n-1)/2L;
	}

}
