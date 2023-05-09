package baekjoon.algo02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main_2206 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][][] visited;
    static int n;
    static int m;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[2][n][m];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        int result = bfs(0, 0);

        System.out.println(result);
    }

    static int bfs(int r, int c) {

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(r, c, 1, true));

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            // 종착지에 도착했다면, 최단거리를 반환한다.
            if (cur.x == n - 1 && cur.y == m - 1) return cur.distance;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                // 이동 가능한 공간
                if (map[nx][ny] == 0){
                    // 벽을 한번도 부수지 않은 상태
                    if (cur.breakable && !visited[0][nx][ny]) {
                        visited[0][nx][ny] = true;  // 벽을 부수지 않은 경로의 방문여부
                        q.offer(new Pair(nx, ny, cur.distance + 1, true));
                    }
                    // 벽을 한번이라도 부순 상태
                    if (!cur.breakable && !visited[1][nx][ny]){
                        visited[1][nx][ny] = true;  // 벽을 한 번 부순 경로의 방문여부
                        q.offer(new Pair(nx, ny, cur.distance + 1, false));
                    }
                }

                // 벽
                if (map[nx][ny] == 1){
                    // 벽을 부술 수 있는 상태
                    if (cur.breakable && !visited[1][nx][ny]){
                        visited[1][nx][ny] = true;
                        q.offer(new Pair(nx, ny, cur.distance + 1, false));
                    }
                    // 벽을 부술 수 없는 경우에는 아무것도 하지 않는다.
                }
            }

        }

        // 이동 가능한 모든 경로를 탐색했지만, 종착지에 도달하지 못한 상태
        return -1;
    }

    static class Pair {
        public int x;
        public int y;
        public int distance;
        public boolean breakable;

        public Pair(int x, int y, int distance, boolean breakable){
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.breakable = breakable;
        }
    }
}