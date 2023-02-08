package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1987 {

    public static final int ALPHABET_COUNT = 26;
    private static int r;
    private static int c;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);
        char[][] map = new char[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        search(map, new int[ALPHABET_COUNT], new int[]{0, 0}, 1);

        System.out.println(max);
    }

    public static void search(char[][] map, int[] checkAlpha, int[] cur, int depth) {
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        checkAlpha[map[cur[0]][cur[1]] - 'A'] = 1;

        boolean isEnable = false;
        for (int[] d : direction) {
            int y = cur[0] + d[0];
            int x = cur[1] + d[1];

            if (y < 0 || y >= r || x < 0 || x >= c) {
                continue;
            }

            if (checkAlpha[map[y][x] - 'A'] == 1) {
                continue;
            }

            search(map, checkAlpha.clone(), new int[]{y, x}, depth+1);
            isEnable = true;
        }

        if (!isEnable) {
            max = Math.max(max, depth);
        }
    }
}