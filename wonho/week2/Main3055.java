package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main3055 {
    private static String[][] map;
    private static int r;
    private static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] rc = br.readLine().split(" ");
        r = Integer.parseInt(rc[0]);
        c = Integer.parseInt(rc[1]);

        map = new String[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().split("");
        }

        int[] beaver = new int[2];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j].equals("S")) {
                    beaver = new int[]{i, j};
                }
            }
        }

        search(new int[]{beaver[0], beaver[1]}, 1);
    }

    public static void search(int[] beaver, int order) {
        int[][] direction = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        Deque<int[]> dq = new ArrayDeque<>();
        Deque<Integer> oq = new ArrayDeque<>();
        dq.offerLast(beaver);
        oq.offerLast(order);

        int move = 0;
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int curOrder = oq.pollFirst();

            if (move != curOrder) {
                settingWater();
                move = curOrder;
            }

            for (int[] direct : direction) {
                int row = cur[0] + direct[0];
                int col = cur[1] + direct[1];

                if (row < 0 || row >= r || col < 0 || col >= c) {
                    continue;
                }

                if (map[row][col].equals("D")) {
                    System.out.println(curOrder);
                    return;
                }

                if (!map[row][col].equals(".")) {
                    continue;
                }

                map[row][col] = String.valueOf(curOrder);
                dq.offerLast(new int[]{row, col});
                oq.offerLast(curOrder + 1);
            }
        }
        System.out.println("KAKTUS");
    }

    public static void settingWater() {
        int[][] direction = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        List<int[]> waters = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!map[i][j].equals("*")) {
                    continue;
                }

                for (int[] d: direction) {
                    int row = i + d[0];
                    int col = j + d[1];

                    if (row < 0 || row >= r || col < 0 || col >= c) {
                        continue;
                    }

                    if (map[row][col].equals("D") || map[row][col].equals("X")) {
                        continue;
                    }

                    waters.add(new int[]{row, col});
                }
            }
        }

        for (int[] water : waters) {
            map[water[0]][water[1]] = "*";
        }
    }
}
