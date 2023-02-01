package com.ssafy.baekjoon.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1138 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] inputs = new int[n];
        int[] result = new int[n];
        boolean[] check = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
            result[i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            int cnt = inputs[i];
            for (int j = 0; j < n; j++) {
                if (cnt== 0) {
                    for (int k = j; k < n; k++) {
                        if (!check[k]) {
                            check[k] = true;
                            break;
                        }
                    }
                    for (int k = j + 1; k < n; k++) {
                        if (!check[k]) {
                            result[k]++;
                        }
                    }
                    break;
                }
                if (!check[j]) {
                    result[j]++;
                    cnt--;
                }
            }
        }
        for (int i : result)
            System.out.print(i + " ");
    }
}
