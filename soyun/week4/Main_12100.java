package com.ssafy.algo230222_BruteForce.soyun.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main_12100 {

    static int n;
    static int[][] map;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        game(0);

        System.out.println(max);

    }

    static void game(int cnt){

        // 5번 움직인 후의 최댓값을 구한다.
        if (cnt == 5){
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, map[i][j]);
                }
            }
            return;
        }

        // 원본 배열을 저장할 copied 배열
        int[][] copied = new int[n][n];
        for (int i = 0; i < n; i++){
            copied[i] = Arrays.copyOf(map[i], n);
        }

        // 모든 경우의 수 탐색
        for (int dir = 0; dir < 4; dir++) {
            swipe(dir);
            game(cnt + 1);
            for (int i = 0; i < n; i++) {
                map[i] = Arrays.copyOf(copied[i], n);
            }
        }
    }

    // dir: 0 - up, 1 - down, 2 - left, 3 - right
    static void swipe(int dir){

        switch (dir){
            case 0:
                for (int i = 0; i < n; i++) {
                    int idx = 0;
                    int number = 0;
                    for (int j = 0; j < n; j++) {
                        // 해당 칸이 0이 아니라면 -> 합치거나 이동시키거나 해야 함
                        if (map[j][i] != 0){
                            // number(직전 블록의 숫자)와 같은 수라면 -> 합친다
                            if (number == map[j][i]){
                                // 해당 블록을 0으로 만듦
                                map[j][i] = 0;
                                // 직전 블록의 숫자를 2배
                                map[idx - 1][i] = number * 2;
                                // 한 번 합쳐진 블록은 또 합쳐질 수 없으므로
                                number = 0;
                            }
                            // 직전 블록의 숫자와 다른 수라면 -> 이동시킨다
                            else {
                                // number를 갱신한다
                                number = map[j][i];
                                // 기존 블록을 이동시킬 것이므로 기존 블록은 0
                                map[j][i] = 0;
                                // 목적지 블록의 숫자를 기존 블록의 숫자로 갱신
                                map[idx][i] = number;
                                // 목적지 블록의 뒤를 가리킴(목적지 갱신)
                                idx++;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < n; i++) {
                    int idx = n - 1;
                    int number = 0;
                    for (int j = n - 1; j >= 0 ; j--) {
                        if (map[j][i] != 0){
                            if (number == map[j][i]){
                                map[j][i] = 0;
                                map[idx + 1][i] = number * 2;
                                number = 0;
                            }
                            else {
                                number = map[j][i];
                                map[j][i] = 0;
                                map[idx][i] = number;
                                idx--;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < n; i++) {
                    int idx = 0;
                    int number = 0;
                    for (int j = 0; j < n; j++) {
                        if (map[i][j] != 0){
                            if (number == map[i][j]){
                                map[i][j] = 0;
                                map[i][idx - 1] = number * 2;
                                number = 0;
                            }
                            else {
                                number = map[i][j];
                                map[i][j] = 0;
                                map[i][idx] = number;
                                idx++;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < n; i++) {
                    int idx = n - 1;
                    int number = 0;
                    for (int j = n - 1; j >= 0; j--) {
                        if (map[i][j] != 0){
                            if (number == map[i][j]){
                                map[i][j] = 0;
                                map[i][idx + 1] = number * 2;
                                number = 0;
                            }
                            else {
                                number = map[i][j];
                                map[i][j] = 0;
                                map[i][idx] = number;
                                idx--;
                            }
                        }
                    }
                }
                break;
        }
    }
}