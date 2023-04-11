package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q6087 {
    private static int h;
    private static int w;
    private static String[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] wh = br.readLine().split(" ");
        w = Integer.parseInt(wh[0]);
        h = Integer.parseInt(wh[1]);
        map = new String[h][w];
        List<int[]> cList = new ArrayList<>();

        for (int i = 0; i < h; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < w; j++) {
                map[i][j] = row[j];
                if (map[i][j].equals("C")) {
                    cList.add(new int[]{i, j});
                }
            }
        }

        System.out.println(Math.min(search(cList.get(0), cList.get(1)), search(cList.get(1), cList.get(0))));
    }

    public static int search(int[] start, int[] dest) {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[2]));
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int[][] weights = new int[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(weights[i], Integer.MAX_VALUE);
        }
        pq.offer(new int[]{start[0], start[1], -1, 0});
        weights[start[0]][start[1]] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curDirection = cur[2];
            int curMirror = cur[3];

            if (weights[cur[0]][cur[1]] < curMirror) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int row = cur[0] + directions[i][0];
                int col = cur[1] + directions[i][1];

                if (row < 0 || row >= h || col < 0 || col >= w) {
                    continue;
                }

                if (map[row][col].equals("*")) {
                    continue;
                }

                int nextMirror = 0;
                if (curDirection != -1 && curDirection != i) {
                    nextMirror++;
                }

                if (weights[row][col] > weights[cur[0]][cur[1]] + nextMirror) {
                    weights[row][col] = weights[cur[0]][cur[1]] + nextMirror;
                    pq.offer(new int[]{row, col, i, weights[cur[0]][cur[1]] + nextMirror});
                }
            }
        }

        return weights[dest[0]][dest[1]];
    }
}
