package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q16952 {

    private static int[][] directions = {
            { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 },
            { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 },
            { 0, 0 }
    };
    private static char[][] map = new char[8][8];
    private static List<int[]> walls = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == '#') {
                    walls.add(new int[] { i, j });
                }
            }
        }

        System.out.println(move());
    }

    public static int move() {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offerLast(new int[] { 7, 0, 0 });

        int currentMove = 0;
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();

            if (cur[0] == 0 && cur[1] == 7) {
                return 1;
            }

            if (cur[2] > currentMove) {
                moveWall();
                currentMove = cur[2];
            }

            if (map[cur[0]][cur[1]] == '#') {
                continue;
            }

            for (int[] direction : directions) {
                int row = cur[0] + direction[0];
                int col = cur[1] + direction[1];

                if (row < 0 || row >= 8 || col < 0 || col >= 8) {
                    continue;
                }

                if (map[row][col] == '#') {
                    continue;
                }

                dq.offerLast(new int[] { row, col, cur[2] + 1 });
            }
        }
        return 0;
    }

    private static void moveWall() {
        List<int[]> newWalls = new ArrayList<>();

        for (int[] wall : walls) {
            map[wall[0]][wall[1]] = '.';
            if (wall[0] + 1 >= 8) {
                continue;
            }

            newWalls.add(new int[] { wall[0] + 1, wall[1] });
        }

        for (int[] newWall : newWalls) {
            map[newWall[0]][newWall[1]] = '#';
        }

        walls = newWalls;
    }
}