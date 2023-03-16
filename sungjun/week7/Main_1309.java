package week7;

import java.util.Scanner;

public class Main_1309 {
	static int N;
	static long[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	
		dp = new long[N+1];
		dp[0] = 1;
		dp[1] = 3;	// 왼쪽 칸에 있거나, 오른쪽 칸에 있거나, 아예 없거나
		
		for (int i = 2; i < N+1; i++) {
			dp[i] = (dp[i-1]*2 + dp[i-2])%9901;
		}
		
		System.out.println(dp[N]);
		sc.close();
	}
}