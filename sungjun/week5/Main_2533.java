package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2533 {
	static int N;
	static int[][] dp;
	static ArrayList<ArrayList<Integer>> Node = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][2];
		
		for (int i = 0; i < N+1; i++) {
			Node.add(new ArrayList<>());
		}
		
		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			// 양방향 노드 추가
			Node.get(s).add(e);
			Node.get(e).add(s);
		}
		
		dp(1, -1);
		
		// 루트 노드가 얼리아답터일때와 아니었을 때 중에 최소값 출력
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	private static void dp(int cur, int parent) {
		dp[cur][0] = 0; 	// 부모 노드가 얼리아답터가 아닐 때
		dp[cur][1] = 1;		// 부모 노드가 얼리아답터일 때
		
		for (int next : Node.get(cur)) {
			if(next != parent) {
				dp(next, cur);
				// 부모 노드가 얼리아답터가 아니라면 자식 노드는 모두 얼리아답터여야 한다
				dp[cur][0] += dp[next][1];	
				// 부모 노드가 얼리아답터라면 자식 노드는 얼리아답터일 수도, 아닐 수도 있다
				dp[cur][1] += Math.min(dp[next][0], dp[next][1]);	
			}
		}
	}

}
