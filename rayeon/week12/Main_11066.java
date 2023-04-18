package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BJ 11066: 파일 합치기
public class Main_11066 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 0; testcase < T; testcase++) {
			int K = Integer.parseInt(br.readLine());
			int[][] fileSize = new int[K][K]; // 파일 크기
			int[][] dp = new int[K][K]; // 누적 합
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				fileSize[0][i] = Integer.parseInt(st.nextToken());
				dp[0][i] = fileSize[0][i];
			}

			for (int i = 1; i < K; i++) {
				for (int j = i; j < K; j++) {
					fileSize[i][j] = fileSize[i-1][j] + fileSize[0][j-i];
					
					if (i == 1) {
						dp[i][j] = fileSize[i][j];
						continue;
					}

					dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]);

					for (int k = 1; k < (i - 1); k++) {
						dp[i][j] = Math.min(dp[i][j], dp[k][j-i+k] + dp[i-1-k][j]);
					}

					dp[i][j] += fileSize[i][j];
				}
			}
			
			sb.append(dp[K-1][K-1]).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}

}