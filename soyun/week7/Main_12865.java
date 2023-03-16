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
        int[] dp = new int[k + 1]; // 배열 index만큼의 무게를 들 수 있을 때, 최대의 가치
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            // 짐의 무게
            weights[i] = Integer.parseInt(st.nextToken());
            // 짐의 가치
            values[i] = Integer.parseInt(st.nextToken());
        }
        // 1번 짐부터 넣어본다.
        for (int i = 1; i <= n; i++) {
            
            // 담을 수 있는 무게의 한계치를 넘지 않을 때까지 반복
            // ex) weight[i] = 3 (i번째 짐의 무게가 3), k = 7 이라면,
            // 7, 6, 5, 4, 3까지 반복 -> 2부터는 i번째 짐을 수용할 수 없으므로 제외 (반복 횟수 줄이기)
            for (int j = k; j - weights[i] >= 0; j--) {
                // 최대 가치를 갱신해준다
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        System.out.println(dp[k]);
    }
}
