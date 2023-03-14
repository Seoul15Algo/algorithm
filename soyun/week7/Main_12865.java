package com.study.algo0315.soyun.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12865 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] weights = new int[n + 1];
        int[] values = new int[n + 1];
        int[] dp = new int[k + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            // 짐의 무게
            weights[i] = Integer.parseInt(st.nextToken());
            // 짐의 가치
            values[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            // 가방의 수용량을 1씩 늘린다
            for (int j = k; j - weights[i] >= 0; j--) {
                // 수용량이 k일 때, i번째 짐을 넣지 않을 때의 최대 가치와 넣었을 때의 최대 가치를 비교
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        System.out.println(dp[k]);
    }
}