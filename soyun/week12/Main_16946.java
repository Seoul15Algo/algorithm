package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16946 {

    static int N, M;
    static int[][] map;
    static int[][][] paintedMap;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        paintedMap = new int[2][N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        int idx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j]){
                    paintMap(i, j, idx++);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                // 벽인 구간을 찾아 해당 벽을 허물면 몇 칸이 나오는 지 계산
                if (map[i][j] == 1){
                    int count = 1;
                    Set<Integer> indexes = new HashSet<>(); // 중복해서 더하는 경우를 피하기 위함
                    // 사방으로 이동 가능한 칸이 몇 칸인지 구함
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        // 범위 밖이거나, 이미 셌었던 구역이라면
                        if (!check(nx, ny) || indexes.contains(paintedMap[1][nx][ny])){
                            continue;
                        }
                        // 셌었던 구역에 추가
                        indexes.add(paintedMap[1][nx][ny]);
                        count = count + paintedMap[0][nx][ny];
                    }
                    map[i][j] = count % 10;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result.append(map[i][j]);
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    // 전체가 0인 구역 하나에 0이 몇개 있는지 센다, 그 후 0인 부분에 해당 숫자를 넣어준다.
    static void paintMap(int x, int y, int idx){
        List<int[]> changed = new ArrayList<>();

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        changed.add(new int[]{x, y});
        visited[x][y] = true;

        int count = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!check(nx, ny) || map[nx][ny] == 1 || visited[nx][ny]){
                    continue;
                }
                q.offer(new int[]{nx, ny});
                changed.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                count++;
            }
        }

        // 한 구역에 0이 몇 개가 있는지
        /*  원본          결과
            001    000    330
            010 -> 000 -> 301
            101    000    010
         */
        for (int i = 0; i < changed.size(); i++) {
            int[] cur = changed.get(i);
            paintedMap[0][cur[0]][cur[1]] = count;
            // idx를 둔 이유 -> 이따가 이동 가능한 칸을 계산할 때, 중복 계산을 피하기 위함
            paintedMap[1][cur[0]][cur[1]] = idx;
        }
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        }
        return true;
    }
}