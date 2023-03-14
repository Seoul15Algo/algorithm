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
				dp[i+1][j] = dp[i][j];
				
				if (j - w >= 0) {
					dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j-w] + v);
				}
			}
		}
		
		System.out.println(dp[N][K]);
		
		br.close();
	}
}
