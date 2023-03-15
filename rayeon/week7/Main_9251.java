package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9251 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();

		int R = str1.length + 1;
		int C = str2.length + 1;
		
		int[][] dp = new int[R][C];
		for (int i = 1; i < R; i++) {
			char c1 = str1[i-1];

			for (int j = 1; j < C; j++) {
				char c2 = str2[j-1];

				if (c1 == c2) {// 두 문자가 같은 경우, 이전 대각선 dp값 + 1 저장
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}

		System.out.println(dp[R-1][C-1]);
		
		br.close();
	}
}
