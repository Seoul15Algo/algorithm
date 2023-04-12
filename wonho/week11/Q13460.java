package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q13460 {
    private static int n;
    private static int m;
    private static String[][] map;
    private static int[] red;
    private static int[] redInit;
    private static int[] blueInit;
    private static int[] blue;
    private static int[] visited;
    private static boolean redSink;
    private static boolean blueSink;
    private static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        visited = new int[10];
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        map = new String[n][m];
        min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split("");
            map[i] = row;
            for (int j = 0; j < m; j++) {
                if (row[j].equals("R")) {
                    redInit = new int[] { i, j };
                }
                if (row[j].equals("B")) {
                    blueInit = new int[] { i, j };
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            visited[0] = i;
            search(0, i);
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    public static void search(int count, int direction) {
        if (count == 9) {
            redSink = false;
            blueSink = false;
            int round = round();
            if (blueSink || round == -1) {
                return;
            }
            if (redSink) {
                min = Math.min(min, round);
            }
            return;
        }

        visited[count + 1] = (direction + 1) % 2;
        search(count + 1, (direction + 1) % 2);
        visited[count + 1] = (direction + 1) % 2 + 2;
        search(count + 1, (direction + 1) % 2 + 2);
    }

    public static int round() {
        String[][] copy = new String[n][m];
        for (int i = 0; i < n; i++) {
            copy[i] = Arrays.copyOf(map[i], m);
        }
        blue = Arrays.copyOf(blueInit, 2);
        red = Arrays.copyOf(redInit, 2);

        for (int i = 0; i < 10; i++) {
            tilt(visited[i], copy);
            if (redSink || blueSink) {
                return i + 1;
            }
        }
        return -1;
    }

    public static void tilt(int direction, String[][] map) {
        if (direction == 1) {
            for (int i = m - 2; i > 0; i--) {
                if (map[blue[0]][i].equals("B") || map[blue[0]][i].equals("R")) {
                    moveBallInColumn(blue[0], i, 1, map);
                }
                if (map[red[0]][i].equals("B") || map[red[0]][i].equals("R")) {
                    moveBallInColumn(red[0], i, 1, map);
                }
            }
            return;
        }
        if (direction == 3) {
            for (int i = 1; i < m - 1; i++) {
                if (map[blue[0]][i].equals("B") || map[blue[0]][i].equals("R")) {
                    moveBallInColumn(blue[0], i, -1, map);
                }
                if (map[red[0]][i].equals("B") || map[red[0]][i].equals("R")) {
                    moveBallInColumn(red[0], i, -1, map);
                }
            }
            return;
        }
        if (direction == 0) {
            for (int i = 1; i < n - 1; i++) {
                if (map[i][blue[1]].equals("B") || map[i][blue[1]].equals("R")) {
                    moveBallInRow(i, blue[1], -1, map);
                }
                if (map[i][red[1]].equals("B") || map[i][red[1]].equals("R")) {
                    moveBallInRow(i, red[1], -1, map);
                }
            }
            return;
        }
        for (int i = n - 2; i > 0; i--) {
            if (map[i][blue[1]].equals("B") || map[i][blue[1]].equals("R")) {
                moveBallInRow(i, blue[1], 1, map);
            }
            if (map[i][red[1]].equals("B") || map[i][red[1]].equals("R")) {
                moveBallInRow(i, red[1], 1, map);
            }
        }
    }

    private static void moveBallInColumn(int row, int col, int step, String[][] map) {
        String ball = map[row][col];
        map[row][col] = ".";
        int cur = col;
        while (map[row][cur].equals(".")) {
            cur += step;
            if (map[row][cur].equals("O")) {
                if (ball.equals("R")) {
                    redSink = true;
                    return;
                }
                blueSink = true;
                return;
            }
        }
        map[row][cur - step] = ball;
        if (ball.equals("R")) {
            red = new int[] { row, cur - step };
        } else {
            blue = new int[] { row, cur - step };
        }
    }

    private static void moveBallInRow(int row, int col, int step, String[][] map) {
        String ball = map[row][col];
        map[row][col] = ".";
        int cur = row;
        while (map[cur][col].equals(".")) {
            cur += step;
            if (map[cur][col].equals("O")) {
                if (ball.equals("R")) {
                    redSink = true;
                    return;
                }
                blueSink = true;
                return;
            }
        }
        map[cur - step][col] = ball;
        if (ball.equals("R")) {
            red = new int[] { cur - step, col };
        } else {
            blue = new int[] { cur - step, col };
        }
    }
}
