package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s1 = br.readLine();
        int s1Length = s1.length();
        String s2 = br.readLine();
        int s2Length = s2.length();

        int[][] dp = new int[s1Length + 1][s2Length + 1];

        // 2차원 배열을 통해 LCS 연산
        for (int i = 1; i <= s1Length; i++) {
            char s1Char = s1.charAt(i - 1);
            for (int j = 1; j <= s2Length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);

                char s2Char = s2.charAt(j - 1);
                if (s1Char == s2Char) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        System.out.println(dp[s1Length][s2Length]);
    }
}
