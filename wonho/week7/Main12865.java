package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        int[][] things = new int[n + 1][2];
        int[] dp = new int[k + 1];

        for (int i = 1; i <= n; i++) {
            String[] wv = br.readLine().split(" ");
            int w = Integer.parseInt(wv[0]);
            int v = Integer.parseInt(wv[1]);
            things[i] = new int[] { w, v };
        }

        for (int i = 1; i <= n; i++) {
            int weight = things[i][0]; // 현재 물건의 무게
            if (weight > k) {
                continue;
            }
            // 가방의 최대 무게부터 현재 무게까지
            for (int j = k; j >= weight; j--) {
                // 해당 무게에 있는 최대 가치와 (현재 + 현재를 뺀 최대 무게일 때 가치)를 비교하여 최대값 삽입
                dp[j] = Math.max(dp[j], things[i][1] + dp[j - weight]);
            }
        }

        int max = 0;
        for (int i = 1; i <= k; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}