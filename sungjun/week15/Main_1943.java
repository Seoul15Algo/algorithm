package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1943 {
	static int N;
	static boolean[] dp;
	static Coin[] coins;
	
	static class Coin {
		int val;
		int num;
		
		public Coin(int val, int num) {
			super();
			this.val = val;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < 3; t++) {
			N = Integer.parseInt(br.readLine());
			dp = new boolean[50001];
			dp[0] = true;
			coins = new Coin[N];
			
			int sum = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				coins[i] = new Coin(a, b);
				sum += a*b;
			}
			
			if(sum % 2 == 1) {
				sb.append(0).append("\n");
				continue;
			}
			
			if(dp[sum/2]) {
				sb.append(1).append("\n");
				continue;
			}
			
			for (int i = 0; i < N; i++) {
				int val = coins[i].val;
				int num = coins[i].num;
				
				for (int j = sum/2; j >= val; j--) {
					if(dp[j-val]) {
						for (int k = 0; k < num; k++) {
							int tmp = j + val*k;
							if(tmp > sum/2) break;
							dp[tmp] = true;
						}
					}
				}
			}
			
			sb.append(dp[sum/2] ? 1 : 0).append("\n");
		}
		
		System.out.println(sb);
	}
}
