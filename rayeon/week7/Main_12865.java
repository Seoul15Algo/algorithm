package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://fbtmdwhd33.tistory.com/60
*/
public class Main_12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N+1][K+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			for (int j = 1; j <= K; j++) {
				// 우선 이전 값을 저장
				dp[i+1][j] = dp[i][j];
				
				if (j - w >= 0) { // 무게가 남는 경우, 현재 무게의 가치와 남는 무게의 가치를 더한 값과 기존 가치를 비교
					dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j-w] + v);
				}
			}
		}
		
		System.out.println(dp[N][K]);
		
		br.close();
	}
}
