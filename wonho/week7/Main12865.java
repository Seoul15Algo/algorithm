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
            things[i] = new int[]{w, v};
        }

        for (int i = 1; i <= n; i++) {
            int weight = things[i][0];
            if (weight > k) {
                continue;
            }
            for (int j = k - weight; j >= 0; j--) {
                dp[weight + j] = Math.max(dp[weight + j], things[i][1] + dp[j]);
            }
        }

        int max = 0;
        for (int i = 1; i <= k; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}