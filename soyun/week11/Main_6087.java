package algo230412.soyun.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_6087 {

    static int W, H;
    static char[][] map;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        int[] start = new int[2];
        for (int i = 0; i < H; i++) {
            char[] inputs = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                map[i][j] = inputs[j];
                if (map[i][j] == 'C'){
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        System.out.println(bfs(start[0], start[1]));
    }

    static int bfs(int x, int y){
        map[x][y] = '.';
        // 우선순위 큐 사용 -> 거울 설치 횟수가 더 적은 경우를 먼저 빼내기 위해
        PriorityQueue<Point> q = new PriorityQueue<>();
        // x, y, dir, 거울설치횟수
        q.offer(new Point(x, y, -1, 0));

        int[][][] visited = new int[H][W][4];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++){
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (map[cur.x][cur.y] == 'C'){
                return cur.turn;
            }
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                int nMirrors = (cur.dir == d || cur.dir  == -1) ? cur.turn : cur.turn + 1;

                // 범위 벗어나거나 벽인 경우, 방문할 필요 없는 경우 -> continue
                if (!check(nx, ny) || map[nx][ny] == '*' || visited[nx][ny][d] <= nMirrors){
                    continue;
                }

                q.offer(new Point(nx, ny, d, nMirrors));
                visited[nx][ny][d] = nMirrors;
            }
        }

        return 0;
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= H || y < 0 || y >= W) {
            return false;
        }
        return true;
    }

    private static class Point implements  Comparable<Point>{
        int x;
        int y;
        int dir;
        int turn;

        public Point(int x, int y, int dir, int turn) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.turn = turn;
        }

        @Override
        public int compareTo(Point other) {
            return this.turn - other.turn;
        }
    }
}