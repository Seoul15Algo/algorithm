package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 비숍
public class Main_1799 {

    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int max;
    static int total;

    static int[] dx = {-1, -1};
    static int[] dy = {-1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[N][N];
        total = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 흰색 체스판 칸
        max = 0;
        dfs(0, 0, 0);
        total = total + max;

        // 검은색 체스판 칸
        max = 0;
        dfs(0, 1, 0);
        total = total + max;

        System.out.println(total);
    }

    static void dfs(int x, int y, int cnt) {

        // y의 범위를 넘어갔을 때 -> 다음 행 탐색
        if (y >= N) {
            x = x + 1;
            // y가 짝수 -> 다음 행의 시작 열은 1
            if (y % 2 == 0) {
                y = 1;
            }
            // y가 홀수 -> 다음 행의 시작 열은 0
            else {
                y = 0;
            }
        }
        // 전체 행 탐색 완료
        if (x >= N) {
            max = Math.max(max, cnt);
            return;
        }

        if (isValid(x, y)){
            visited[x][y] = true;
            dfs(x, y + 2, cnt + 1);
            visited[x][y] = false;
        }

        dfs(x, y + 2, cnt);
    }

    static boolean isValid(int x, int y) {
        // 아예 못 놓는 칸이었을 때
        if (board[x][y] == 0) {
            return false;
        }

        // 대각선 방향으로 비숍이 있을 때
        for (int d = 0; d < 2; d++) {
            int nx = x;
            int ny = y;
            while (true) {
                nx = nx + dx[d];
                ny = ny + dy[d];
                if (isOutOfBound(nx, ny)) {
                    break;
                }
                if (visited[nx][ny]){
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isOutOfBound(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            return true;
        }
        return false;
    }
}
