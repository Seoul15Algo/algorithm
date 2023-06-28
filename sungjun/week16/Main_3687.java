package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_3687 {
	static int[] matchs = {1, 7, 4, 2, 0, 8};	// 성냥개비 2~7개로 만들 수 있는 가장 작은 수
	static long[] dp;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			
			dp = new long[101];
			Arrays.fill(dp, Long.MAX_VALUE);
			
			dp[2] = 1L;
			dp[3] = 7L;
			dp[4] = 4L;
            dp[5] = 2L;
            dp[6] = 6L;	// 0으로 시작할 수 없음
            dp[7] = 8L;
            dp[8] = 10L;
            
            sb.append(getMin(n)).append(" ").append(getMax(n)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static long getMin(int n) { 
		for (int j = 9; j <= n; j++) {
			for (int k = 2; k <= 7; k++) {
				dp[j] = Math.min(dp[j], dp[j-k]*10L+(long)matchs[k-2]);
			}
		}
		
		return dp[n];
	}
	
	private static String getMax(int n) {
		 int digits = n / 2;
         int maxDigit = n % 2;
         String max = "";
         
         if(maxDigit == 1) {
         	max += "7";
         }
         
         if(maxDigit == 0) {
         	max += "1";
         }
         
         for (int j = 1; j < digits; j++) {
			max += "1";
		}
         
         return max;
	}
}
