package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main12100 {

    private static int n;
    private static int totalMax;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        totalMax = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        search(0, map);
        System.out.println(totalMax);
    }

    public static void search(int count, int[][] map) {
        if (count == 5) {
            totalMax = Math.max(totalMax, checkMaxBlock(map));
            return;
        }

        for (int i = 0; i < 4; i++) {
            search(count + 1, moveBlocks(map, i));
        }
    }

    public static int[][] moveBlocks(int[][] map, int direction) {
        int[][] copy = new int[n][n];

        if (direction == 0) {
            for (int i = 0; i < n; i++) {
                int prev = -1;
                int copyIndex = 0;
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 0) {
                        continue;
                    }

                    if (prev == -1) {
                        prev = map[i][j];
                        continue;
                    }

                    if (prev == map[i][j]) {
                        copy[i][copyIndex++] = prev + map[i][j];
                        prev = -1;
                        continue;
                    }

                    copy[i][copyIndex++] = prev;
                    prev = map[i][j];
                }
                if (prev != -1) {
                    copy[i][copyIndex] = prev;
                }
            }
            return copy;
        }

        if (direction == 1) {
            for (int i = 0; i < n; i++) {
                int prev = -1;
                int copyIndex = n - 1;
                for (int j = n - 1; j >= 0; j--) {
                    if (map[i][j] == 0) {
                        continue;
                    }

                    if (prev == -1) {
                        prev = map[i][j];
                        continue;
                    }

                    if (prev == map[i][j]) {
                        copy[i][copyIndex--] = prev + map[i][j];
                        prev = -1;
                        continue;
                    }

                    copy[i][copyIndex--] = prev;
                    prev = map[i][j];
                }
                if (prev != -1) {
                    copy[i][copyIndex] = prev;
                }
            }
            return copy;
        }

        if (direction == 2) {
            for (int i = 0; i < n; i++) {
                int prev = -1;
                int copyIndex = 0;
                for (int j = 0; j < n; j++) {
                    if (map[j][i] == 0) {
                        continue;
                    }

                    if (prev == -1) {
                        prev = map[j][i];
                        continue;
                    }

                    if (prev == map[j][i]) {
                        copy[copyIndex++][i] = prev + map[j][i];
                        prev = -1;
                        continue;
                    }

                    copy[copyIndex++][i] = prev;
                    prev = map[j][i];
                }
                if (prev != -1) {
                    copy[copyIndex][i] = prev;
                }
            }
            return copy;
        }

        for (int i = 0; i < n; i++) {
            int prev = -1;
            int copyIndex = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (map[j][i] == 0) {
                    continue;
                }

                if (prev == -1) {
                    prev = map[j][i];
                    continue;
                }

                if (prev == map[j][i]) {
                    copy[copyIndex--][i] = prev + map[j][i];
                    prev = -1;
                    continue;
                }

                copy[copyIndex--][i] = prev;
                prev = map[j][i];
            }
            if (prev != -1) {
                copy[copyIndex][i] = prev;
            }
        }
        return copy;
    }

    public static int checkMaxBlock(int[][] map) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, map[i][j]);
            }
        }

        return max;
    }
}