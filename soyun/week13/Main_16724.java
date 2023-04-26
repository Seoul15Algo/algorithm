package baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16724 {

    static int N, M;
    static int[][] map;
    static int numberOfBooth;
    // 하 좌 상 우
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        numberOfBooth = 0;

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                switch(input[j]){
                    case "D":
                        map[i][j] = 0;
                        break;
                    case "L":
                        map[i][j] = 1;
                        break;
                    case "U":
                        map[i][j] = 2;
                        break;
                    case "R":
                        map[i][j] = 3;
                        break;
                }
            }
        }

        // 싸이클이 존재하는 구간 하나당 하나의 방음 부스 설치 -> 방음 부스 개수의 최솟값
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != -1){
                    bfs(i, j);
                    numberOfBooth++;
                }
            }
        }
        System.out.println(numberOfBooth);
    }

    static void bfs(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int dir = map[cur[0]][cur[1]];
            map[cur[0]][cur[1]] = -1;
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!check(nx, ny) || map[nx][ny] == -1){
                    continue;
                }
                // 진행 방향에 해당 || 진행 방향에 해당하지는 않지만, 싸이클 안에 속해있는 경우
                if (d == dir || d == (map[nx][ny] + 2) % 4){
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        }
        return true;
    }
}