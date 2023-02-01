package com.ssafy.baekjoon.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] floor;

    static int n;
    static int m;
    static int r;
    static int c;
    static int d;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        floor = new int[n][m];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        int dirty = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                floor[i][j] = Integer.parseInt(st.nextToken());
                if (floor[i][j] == 0)
                    dirty++;
            }
        }

        int cleaned = 1;    // 청소한 칸 개수
        floor[r][c] = -1;   // 초기 위치 청소
        while (dirty != cleaned){
            // 4방이 모두 청소가 되어있거나 벽인 경우인지 확인
            boolean isStick = true;
            for (int i = 0; i < 4; i++){
                int nx = r + dx[i];
                int ny = c + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (!(floor[nx][ny] == -1) && !(floor[nx][ny] == 1)) {
                    isStick = false;
                    break;
                }
            }
            if (isStick) {
                // 후진할 수도 없는 상태라면 작동 중지
                if (!back())
                    break;
                continue;
            }

            // index 범위 초과 방지 (복 -> 서 일 경우)
            if (d - 1 < 0) d = 3;
            else d = d - 1;
            int nx = r + dx[d];
            int ny = c + dy[d];
            // 직진하려는 방향이 floor 배열 범위 밖인 경우
            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue;
            // 직진하려는 방향이 청소가 되어있거나 벽인 경우
            if (floor[nx][ny] == -1 || floor[nx][ny] == 1)
                continue;
            floor[nx][ny] = -1;
            r = nx;
            c = ny;
            cleaned++;
        }
        System.out.println(cleaned);
    }


    static boolean back() {
        int savedDirection = d;
        // index 범위 초과 방지
        if (d - 2 < 0) d = d + 2;
        else d = d - 2;
        // 후진하였을 때의 좌표로 이동
        int nx = r + dx[d];
        int ny = c + dy[d];
        // 후진이 불가능한 상황 - floor 배열의 범위를 벗어나는 경우
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) return false;
        // 후진이 불가능한 상황 - 뒤가 벽인 경우
        if (floor[nx][ny] == 1) return false;
        r = nx;
        c = ny;
        d = savedDirection;
        return true;
    }
}
