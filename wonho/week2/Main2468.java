package wonho.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main2468 {

    private static int n;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int answer = 0;
        for (int i = 0; i < 100; i++) {
            int[][] visited = new int[n][n];
            int count = 0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (map[j][k] <= i || visited[j][k] == 1) {
                        continue;
                    }
                    search(visited, i, new int[]{j, k});
                    count += 1;
                }
            }
            if (count == 0) {
                break;
            }

            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }

    public static void search(int[][] visited, int depth, int[] start) {
        int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Deque<int[]> q = new ArrayDeque<>();
        visited[start[0]][start[1]] = 1;
        q.offerLast(start);

        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();

            for (int[] d : direction) {
                int row = cur[0] + d[0];
                int col = cur[1] + d[1];

                if (row < 0 || row >= n || col < 0 || col >= n) {
                    continue;
                }

                if (map[row][col] <= depth) {
                    continue;
                }

                if (visited[row][col] == 1) {
                    continue;
                }

                q.offerLast(new int[]{row, col});
                visited[row][col] = 1;
            }
        }
    }
}
