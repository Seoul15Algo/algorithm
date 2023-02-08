package baekjoon.algo02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_3055 {

    static int r;
    static int c;
    static char[][] map;
    static int[][] dochi;
    static int[][] water;
    static Queue<Pair> q1 = new LinkedList<>();
    static Queue<Pair> q2 = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        dochi = new int[r][c];
        water = new int[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
            Arrays.fill(dochi[i], -1);
            Arrays.fill(water[i], -1);
            for (int j = 0; j < c; j++) {
                // 고슴도치가 있는 곳
                if (map[i][j] == 'S') {
                    dochi[i][j] = 0;
                    q1.offer(new Pair(i, j));
                }
                if (map[i][j] == '*'){
                    water[i][j] = 0;
                    q2.offer(new Pair(i, j));
                }
            }
        }
        spread();
        int result = escape();
        System.out.println(result == -1 ? "KAKTUS" : result);
    }

    static int escape(){

        while (!q1.isEmpty()) {
            Pair cur = q1.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (map[nx][ny] == 'D') return dochi[cur.x][cur.y] + 1;
                if (map[nx][ny] == 'X' || dochi[nx][ny] != -1) continue;
                if (water[nx][ny] != -1 && dochi[cur.x][cur.y] + 1 >= water[nx][ny]) continue;
                dochi[nx][ny] = dochi[cur.x][cur.y] + 1;
                q1.offer(new Pair(nx, ny));
            }
        }

        return -1;
    }

    static void spread(){

        while (!q2.isEmpty()) {
            Pair cur = q2.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (map[nx][ny] == 'X' || map[nx][ny] == 'D' || water[nx][ny] != -1) continue;
                water[nx][ny] = water[cur.x][cur.y] + 1;
                q2.offer(new Pair(nx, ny));
            }
        }
    }

    static class Pair {

        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
