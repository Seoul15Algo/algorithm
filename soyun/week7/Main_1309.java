package com.study.algo0315.soyun.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// BOJ 1309 동물원
public class Main_1309 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][3];
        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for (int i = 2; i <= n; i++) {
            // i번째 행의 양쪽 칸에 사자가 없는 경우 -> 이전 칸에는 사자가 없거나 왼쪽 혹은 오른쪽에 사자가 있을 수 있음
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
            // i번째 행의 왼쪽 칸에 사자가 있는 경우 -> 이전 칸에는 사자가 없거나 오른쪽에 사자가 있을 수 있음
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
            // i번째 행의 오른쪽 칸에 사자가 있는 경우 -> 이전 칸에는 사자가 없거나 왼쪽에 사자가 있을 수 있음
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
        }

        // 너무 큰 수이기 때문에 9901로 나눈 나머지를 반환
        System.out.println((dp[n][0] + dp[n][1] + dp[n][2]) % 9901);
    }
}