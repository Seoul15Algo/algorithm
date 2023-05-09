package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Q16946 {

    private static int[][] map;
    private static int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    private static int n;
    private static int m;
    private static boolean[][] visited;
    private static Deque<int[]> walls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new int[n][m];
        visited = new boolean[n][m];
        walls = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    int count = search(new int[] { i, j });

                    while (!walls.isEmpty()) {
                        int[] wall = walls.pollFirst();
                        map[wall[0]][wall[1]] += count;
                        visited[wall[0]][wall[1]] = false;
                    }
                }
            }
        }

        for (int[] row : map) {
            for (int v : row) {
                sb.append(v % 10);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static int search(int[] start) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offerLast(start);
        visited[start[0]][start[1]] = true;

        int count = 1;
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();

            for (int[] direction : directions) {
                int row = cur[0] + direction[0];
                int col = cur[1] + direction[1];

                if (row < 0 || row >= n || col < 0 || col >= m) {
                    continue;
                }

                if (visited[row][col]) {
                    continue;
                }

                if (map[row][col] > 0) {
                    walls.offerLast(new int[] { row, col });
                    visited[row][col] = true;
                    continue;
                }

                dq.offerLast(new int[] { row, col });
                visited[row][col] = true;
                count++;
            }
        }
        return count;
    }
}
