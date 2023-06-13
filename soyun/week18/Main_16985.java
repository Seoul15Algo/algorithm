package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16985 {

    static final int SIZE = 5;
    static final int DIR = 4;

    static int[][][][] boards;
    static int[] selectedLevel;
    static boolean[] visited;
    static int[] selectedDir;
    static int min;
    static int[] start = {0, 0, 0};
    static int[] end = {4, 4, 4};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boards = new int[DIR][SIZE][SIZE][SIZE];
        selectedLevel = new int[SIZE];
        visited = new boolean[SIZE];
        selectedDir = new int[SIZE];

        min = Integer.MAX_VALUE;

        for (int k = 0; k < SIZE; k++) {
            for (int i = 0; i < SIZE; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < SIZE; j++) {
                    boards[0][k][i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int d = 1; d < DIR; d++) {
                boards[d][k] = rotate(boards[d - 1][k]);
            }
        }

        makeMaze(0);
        if (min == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }

    // 미로 미리 돌려놓기
    static int[][] rotate(int[][] origin){
        int[][] rotated = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                rotated[i][j] = origin[j][(SIZE - 1) - i];
            }
        }

        return rotated;
    }

    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, -1 ,0, 1};
    static final int[] DZ = {-1, 1};

    // 미로 만들기
    static void makeMaze(int count){

        // 판떼기들 선택 완료
        if (count == SIZE){
            int[][][] maze = new int[SIZE][SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                int level = selectedLevel[i];
                int dir = selectedDir[level];
                maze[i] = boards[dir][level];
            }

            // 시작점, 끝점이 막혀있는 경우
            if (maze[start[0]][start[1]][start[2]] == 0 || maze[end[0]][end[1]][end[2]] == 0){
                return;
            }

            // 미로탈출
            escape(maze);

            return;
        }

        // 몇번째 판을 선택할지 결정
        for (int idx = 0; idx < SIZE; idx++) {
            if (visited[idx]){
                continue;
            }

            selectedLevel[count] = idx;
            visited[idx] = true;

            // 어느 방향으로 회전시킬지 설정
            for (int d = 0; d < DIR; d++) {
                selectedDir[idx] = d;
                makeMaze(count + 1);
            }
            visited[idx] = false;
        }
    }

    // 탈출하기
    static void escape(int[][][] maze){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[SIZE][SIZE][SIZE];
        q.offer(new int[]{start[0], start[1], start[2], 0});
        visited[start[0]][start[1]][start[2]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[3] > min){
                return;
            }
            if (cur[0] == end[0] && cur[1] == end[1] && cur[2] == end[2]) {
                min = Math.min(min, cur[3]);
                return;
            }
            // 2차원 평면 상에서 상하좌우 이동
            for (int d = 0; d < DIR; d++) {
                int nx = cur[1] + DX[d];
                int ny = cur[2] + DY[d];
                int nz = cur[0];
                // 해당 칸으로 이동할 수 없는 경우
                if (isOutOfBound(nx, ny, nz) || maze[nz][nx][ny] == 0 || visited[nz][nx][ny]){
                    continue;
                }
                q.offer(new int[]{nz, nx, ny, cur[3] + 1});
                visited[nz][nx][ny] = true;
            }
            // 3차원 평면 상에서 상하 이동
            for (int d = 0; d < 2; d++) {
                int nx = cur[1];
                int ny = cur[2];
                int nz = cur[0] + DZ[d];
                if (isOutOfBound(nx, ny, nz) || maze[nz][nx][ny] == 0 || visited[nz][nx][ny]){
                    continue;
                }
                q.offer(new int[]{nz, nx, ny, cur[3] + 1});
                visited[nz][nx][ny] = true;
            }
        }
    }

    static boolean isOutOfBound(int x, int y, int z){
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE || z < 0 || z >= SIZE){
            return true;
        }
        return false;
    }
}
