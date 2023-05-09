package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ 1943 : 동전 분배
public class Main_1943 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answers = new StringBuilder();
		
		for (int testcase = 0; testcase < 3; testcase++) {
			int N = Integer.parseInt(br.readLine());
			int[] dp = new int[100001];
			List<int[]> coins = new ArrayList<>();
			int total = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int coin = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				
				total += coin * count;
				coins.add(new int[] {coin, count});
			}
	
			// 금액의 총합이 홀수라면 똑같이 반으로 나눌 수 없다.
			if (total % 2 != 0) {
				answers.append(0).append("\n");
				continue;
			}

			dp[0] = 1;

			for (int[] coin : coins) {
				for (int i = (total / 2); i >= 0; i--) {
					if (dp[i] > 0) { // coin[0] = 동전 단위, coin[1] = 동전 개수
						for (int j = 1; j <= coin[1]; j++) {
							if (i + (coin[0] * j) <= 100001) {
								dp[i + (coin[0] * j)] = 1;
							}
						}
					}
				}
			}

			answers.append(dp[total / 2]).append("\n");
		}
		
		System.out.println(answers);
		br.close();
	}

}