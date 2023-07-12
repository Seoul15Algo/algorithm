package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 궁금한 민호
public class Main_1507 {

    static int N;
    static int[][] cost;
    static boolean[][] invalid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        invalid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == k || j == k){
                        continue;
                    }
                    if (cost[i][j] == cost[i][k] + cost[k][j]){
                        invalid[i][j] = true;
                    }
                    if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (invalid[i][j]){
                    continue;
                }
                sum = sum + cost[i][j];
            }
        }
        System.out.println(sum);
    }
}
