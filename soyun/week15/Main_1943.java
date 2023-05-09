package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1943 {

    static int N;
    static Coin[] coins;
    static boolean[] dp;
    static int total;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < 3; t++){
            N = Integer.parseInt(br.readLine());
            coins = new Coin[N];
            total = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int value = Integer.parseInt(st.nextToken());
                int quantity = Integer.parseInt(st.nextToken());
                coins[i] = new Coin(value, quantity);
                total = total + (value * quantity);
            }
            // total이 홀수 -> 반으로 쪼갤 수 없다
            if (total % 2 == 1){
                sb.append(0).append("\n");
                continue;
            }

            dp = new boolean[100001];
            // 현재 존재하는 동전 한 종류만으로 만들 수 있는 금액
            for (int i = 0; i < N; i++){
                for (int j = 1; j <= coins[i].quantity; j++) {
                    dp[coins[i].value * j] = true;  // 1, 2, 3, ... 개를 사용했을 때
                }
            }
            // 한 종류 만으로 total / 2를 만들 수 있는 경우
            if (dp[total / 2]){
                sb.append(1).append("\n");
                continue;
            }

            boolean flag = false;
            // 동전을 분배하여 total / 2를 만들 수 있는 지
            for (int i = 0; i < N; i++){

                for (int j = total / 2; j >= coins[i].value; j--){

                    // dp[j - coins[i].value]가 가능해야만 해당 금액에 다른 동전을 더해줄 수 있음
                    if (!dp[j - coins[i].value]){
                        continue;
                    }
                    // 동전을 하나씩 더해감
                    for (int k = 1; k <= coins[i].quantity; k++) {
                        if (j - coins[i].value + k * coins[i].value > total / 2){
                            break;
                        }
                        dp[j - coins[i].value + k * coins[i].value] = true;
                    }
                    if (dp[total / 2]){
                        flag = true;
                        break;
                    }
                }
                if (flag){
                    break;
                }
            }
            sb.append((dp[total / 2] ? 1 : 0)).append("\n");
        }
        System.out.println(sb);
    }

    static class Coin {
        int value;
        int quantity;

        public Coin(int value, int quantity) {
            this.value = value;
            this.quantity = quantity;
        }
    }
}
