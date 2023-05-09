package com.ssafy.algo230222_BruteForce.soyun.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9663 {

    static int n;
    static boolean[][] chessBoard;
    static int count;

    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        chessBoard = new boolean[n][n];
        count = 0;

        validate(0, 0);

        System.out.println(count);
    }

    static void validate(int x, int depth){

        // 경우의 수를 찾았을 경우 -> count 증가
        if (depth == n){
            count++;
            return;
        }

        for (int y = 0; y < n; y++) {
            for (int i = x - 1; i >= 0; i++){

            }
            // 해당 위치에 놓을 수 없는 경우라면 cut
            if (!validQueen(x, y))
                continue;
            // 백트래킹
            chessBoard[x][y] = true;
            validate(x + 1, depth + 1);
            chessBoard[x][y] = false;
        }
    }

    // Queen 이 공격할 수 있는 말이 있는지 탐색
    static boolean validQueen(int x, int y){

        for (int d = 0; d < 8; d++) {

            int sx = x;
            int sy = y;
            while(true){
                int nx = sx + dx[d];
                int ny = sy + dy[d];
                // 보드 밖을 벗어났다면 -> 해당 방향은 valid, true return
                if (!check(nx, ny))
                    break;
                // 공격할 수 있는 말이 있다면 -> valid하지 않음, false return
                if (chessBoard[nx][ny])
                    return false;
                sx = nx;
                sy = ny;
            }
        }
        return true;
    }

    static boolean check(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n)
            return false;
        return true;
    }
}
