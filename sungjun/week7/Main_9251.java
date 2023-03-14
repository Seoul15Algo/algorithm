package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9251 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();
		
		int len1 = s1.length;
		int len2 = s2.length;
		
		int[][] dp = new int[len2+1][len1+1];
		
		for (int i = 0; i < len2; i++) {
			for (int j = 0; j < len1; j++) {
				if(s2[i] == s1[j]) {		// 현재 문자가 j번째 문자와 같다면
					dp[i+1][j+1] = dp[i][j]+1;		// 현재 위치에서의 LCS는 j-1번째 위치에서의 LCS+1이 보장됨
					continue;
				}
				// 다르다면 현재 문자를 직전 위치까지 비교한 LCS와 직전 문자를 현재 위치까지 비교한 LCS중에 긴 것을 선택
				dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
			}
		}
		
		System.out.println(dp[len2][len1]);
	}
}