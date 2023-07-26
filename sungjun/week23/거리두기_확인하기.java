package week23;

import java.util.*;

class 거리두기_확인하기 {
    static boolean[][] visited;

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        char[][][] map = new char[5][5][5];

        for (int i = 0; i < 5; i++) {
            Queue<int[]> q = new LinkedList<>();
            visited = new boolean[5][5];

            // 입력을 받으면서 응시자의 위치를 큐에 넣음
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    map[i][j][k] = places[i][j].charAt(k);
                    if(map[i][j][k] == 'P') q.add(new int[] {j, k});
                }
            }

            boolean flag = true;

            // 큐가 빌때까지 각 응시자로부터 bfs 탐색 진행
            while(!q.isEmpty()) {
                int[] cur = q.poll();

                // 탐색 도중 다른 응시자를 만나면 거리두기 지켜지지 않음
                if(!bfs(cur[0], cur[1], map[i])) {
                    flag = false;
                    break;
                }
            }

            // 모든 응시자가 bfs를 통과하면 거리두기 지켜짐
            if(flag) answer[i] = 1;
        }

        return answer;
    }

    private static boolean bfs(int r, int c, char[][] map) {
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c, 0});
        visited[r][c] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[2] >= 2) continue;   // 이미 2 이상의 거리를 왔으면 다음 칸은 탐색 불필요

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if(!check(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc] == 'X') continue;

                visited[nr][nc] = true;

                // 응시자를 만났을 때
                if(map[nr][nc] == 'P') {
                    if(cur[2] < 2) return false;    // 지금까지 이동한 거리가 2 미만이라면 거리두기 X

                    q.add(new int[] {nr, nc, 0});
                    continue;
                }

                q.add(new int[] {nr, nc, cur[2]+1});
            }
        }

        return true;
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }
}