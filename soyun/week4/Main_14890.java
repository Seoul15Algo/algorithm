package com.ssafy.algo230222_BruteForce.soyun.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14890 {

    static int n;
    static int l;
    static int[][] map;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        count = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeRoad();

        System.out.println(count);
    }

    static void makeRoad(){

        for (int i = 0; i < n; i++) {

            int[] horizontal = new int[n];
            int[] vertical = new int[n];

            for (int j = 0; j < n; j++) {
                horizontal[j] = map[i][j];
                vertical[j] = map[j][i];
            }

            if (go(horizontal)){
                //System.out.println(Arrays.toString(horizontal));
                count++;
            }

            if (go(vertical)){
                //System.out.println(Arrays.toString(vertical));
                count++;
            }

        }
    }

    static boolean go(int[] road){
        boolean[] slope = new boolean[n];

        int prev = 0;

        for (int now = 1; now < n; now++) {
            if (road[prev] != road[now]){
                // 차이가 2 이상 -> 진행 불가
                if (Math.abs(road[prev] - road[now]) >= 2)
                    return false;

                if (road[prev] < road[now]){

                    if (slope[prev] || !back(prev, road, slope))
                        return false;
                }
                if (road[prev] > road[now]){

                    if (slope[now] || !forward(now, road, slope))
                        return false;
                }
            }
            prev = now;
        }

        return true;
    }

    static boolean forward(int start, int[] road, boolean[] slope){

        if (start + l - 1 >= n) return false;
        for (int i = 0; i < l; i++) {
            if (slope[start + i])
                return false;
        }
        for (int i = start; i < start + l; i++) {
            if (road[start] != road[i])
                return false;
            slope[i] = true;
        }
        return true;
    }

    static boolean back(int start, int[] road, boolean[] slope){

        if (start - l + 1 < 0) return false;
        for (int i = 0; i < l; i++) {
            if (slope[start - i])
                return false;
        }
        slope[start] = true;
        for (int i = start; i > start - l; i--){
            if (road[start] != road[i])
                return false;
            slope[i] = true;
        }

        return true;
    }
}