package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q3687 {

    private static long[] matchsticks = { 0, 0, 1, 7, 4, 2, 0, 8 };
    private static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        dp = new long[101];
        dp[1] = 9;
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        for (int i = 8; i <= 100; i++) {
            dp[i] = Long.MAX_VALUE;
            for (int j = 2; j <= 7; j++) {
                dp[i] = Math.min(dp[i], matchsticks[j] + (dp[i - j] * 10));
            }
        }

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int c = Integer.parseInt(br.readLine());
            sb.append(dp[c]).append(" ");

            if (c % 2 == 0) {
                for (int j = 0; j < c / 2; j++) {
                    sb.append("1");
                }
            } else {
                sb.append("7");
                for (int j = 0; j < (c - 3) / 2; j++) {
                    sb.append("1");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}