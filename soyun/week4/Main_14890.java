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

            // 수평, 수직 경로 -> 두 가지로 나눔
            int[] horizontal = new int[n];
            int[] vertical = new int[n];

            for (int j = 0; j < n; j++) {
                horizontal[j] = map[i][j];
                vertical[j] = map[j][i];
            }

            // 수평 경로 탐색
            if (go(horizontal)){
                //System.out.println(Arrays.toString(horizontal));
                count++;
            }

            // 수직 경로 탐색
            if (go(vertical)){
                //System.out.println(Arrays.toString(vertical));
                count++;
            }

        }
    }

    static boolean go(int[] road){
        boolean[] slope = new boolean[n];   // 경사로를 놓았는지 여부를 저장

        int prev = 0;

        for (int now = 1; now < n; now++) {
            // 이전과 현재의 높이에 차이가 있을 경우 -> 경사로가 필요
            if (road[prev] != road[now]){
                // 차이가 2 이상 -> 진행 불가
                if (Math.abs(road[prev] - road[now]) >= 2)
                    return false;

                // 낮은 곳에서 높은 곳으로 경사로를 놓는 경우 -> 왔던 길을 역으로 탐색하면서 경사로를 놓을 수 있는 지 확인
                if (road[prev] < road[now]){
                    // 경사로를 놓을 수 없는 경우
                    if (!back(prev, road, slope))
                        return false;
                }
                // 높은 곳에서 낮은 곳으로 경사로를 놓는 경우 -> 앞으로 갈 길을 탐색하면서 경사로를 놓을 수 있는 지 확인
                if (road[prev] > road[now]){

                    if (!forward(now, road, slope))
                        return false;
                }
            }
            prev = now;
        }

        return true;
    }

    static boolean forward(int start, int[] road, boolean[] slope){

        // 경사로 길이보다 놓을 수 있는 범위가 짧을 경우 (범위 초과)
        if (start + l - 1 >= n) return false;
        // 경사로를 이미 설치한 경우
        for (int i = 0; i < l; i++) {
            if (slope[start + i])
                return false;
        }
        // 경사로 길이보다 놓을 수 있는 범위가 짧을 경우 (높이 차이)
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