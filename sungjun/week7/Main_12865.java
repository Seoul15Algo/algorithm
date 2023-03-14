package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12865 {
	static int N, K;
	static int[][] item;
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		item = new int[N+1][2];
		int[][] dp = new int[N+1][K+1];
		
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			item[i][0] = Integer.parseInt(st.nextToken());
			item[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// dp[i][j] = i번째 물품까지만을 사용했을 경우에 무게가 j일때의 최대 가치
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < K+1; j++) {
				// 현재 물품을 가방에 넣을 수 있다면
				if(item[i][0] <= j) {
					// 현재 물품을 넣지 않았을 경우와 넣었을 경우 중 큰 값으로 dp테이블 갱신
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-item[i][0]]+item[i][1]);
					continue;
				}
				
				// 현재 물품을 가방에 넣을 수 없다면 이전 물품까지를 사용했을 경우의 최대값이 그대로 유지됨
				dp[i][j] = dp[i-1][j];
			}
		}
		
		System.out.println(dp[N][K]);
	}

}
