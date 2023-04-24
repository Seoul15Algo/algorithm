package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11066 {
	static int K;
	static boolean[] visited;
	static int[] file, sum;
	static int[][] dp;
	static ArrayList<Integer> arr = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine());
			file = new int[K+1];
			sum = new int[K+1];
			dp = new int[K+1][K+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
						
			for (int i = 1; i < K+1; i++) {
				file[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + file[i];	// i번째 파일까지의 총 합
			}
			
			for (int i = 1; i < K+1; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);	// dp 배열 초기화
			}
			
			for (int i = 1; i < K+1; i++) {
				dp[i][i] = 0;
			}
			
			// dp[j][i] = j~i까지의 파일을 합칠 때 필요한 최소 비용
			// 규칙을 찾아보면 마지막에는 무조건 j~i까지의 파일 크기의 총 합이 더해지는 것을 알 수 있다  -> sum[i]-sum[j-1]
			// j~i를 두 개의 구간으로 나눈다고 가정하면 j~k, k+1~i가 된다 -> dp[j][i] = dp[j][k]+dp[k+1][i] 
			// 위의 규칙과 합친다면 dp[j][i] = dp[j][k]+dp[k+1][i]+sum[i]-sum[j-1]이라는 식이 도출된다
			// k가 변화함에 따라 모든 경우의 수를 고려하여 dp테이블을 갱신해준다
			for (int i = 2; i < K+1; i++) {
				for (int j = i-1; j > 0; j--) {
					for (int k = j; k < i; k++) {
						dp[j][i] = Math.min(dp[j][i], dp[j][k]+dp[k+1][i]+sum[i]-sum[j-1]);
					}
				}
			}
			
			sb.append(dp[1][K]).append("\n");
		}	
		System.out.println(sb);
	}
}
