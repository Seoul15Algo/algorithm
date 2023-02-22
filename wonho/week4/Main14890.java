package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14890 {

    private static int n;
    private static int l;
    private static int[][] map;
    private static boolean[][] runways;
    private static boolean[] checkLeftRow;
    private static boolean[] checkRightRow;
    private static boolean[] checkDownCol;
    private static boolean[] checkUpCol;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nl = br.readLine().split(" ");
        n = Integer.parseInt(nl[0]);
        l = Integer.parseInt(nl[1]);
        map = new int[n][n];
        runways = new boolean[n][n];
        checkLeftRow = new boolean[n];
        checkRightRow = new boolean[n];
        checkDownCol = new boolean[n];
        checkUpCol = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        searchLeftPath();
        searchRightPath();
        runways = new boolean[n][n];
        searchDownPath();
        searchUpPath();

        int total = 0;
        for (int i = 0; i < n; i++) {
            if (checkRightRow[i] && checkLeftRow[i]) {
                total++;
            }

            if (checkDownCol[i] && checkUpCol[i]) {
                total++;
            }
        }
        System.out.println(total);
    }

    public static void searchLeftPath() {
        for (int i = 0; i < n; i++) {
            boolean isValid = true;
            for (int j = 0; j < n - 1; j++) {
                int cur = map[i][j];
                int next = map[i][j + 1];

                if (Math.abs(cur - next) > 1) {
                    isValid = false;
                    break;
                }

                if (cur - next == 1) {
                    if (j + l >= n) {
                        isValid = false;
                        break;
                    }
                    if (isInValidRowRunWay(i, j + 1, j + 1 + l, map[i][j + 1])) {
                        isValid = false;
                        break;
                    }
                }
            }
            checkLeftRow[i] = isValid;
        }
    }

    public static void searchRightPath() {
        for (int i = 0; i < n; i++) {
            boolean isValid = true;
            for (int j = n - 1; j > 0; j--) {
                int cur = map[i][j];
                int next = map[i][j - 1];

                if (Math.abs(cur - next) > 1) {
                    isValid = false;
                    break;
                }

                if (cur - next == 1) {
                    if (j - l < 0) {
                        isValid = false;
                        break;
                    }
                    if (isInValidRowRunWay(i, j - l, j, map[i][j - 1])) {
                        isValid = false;
                        break;
                    }
                }
            }
            checkRightRow[i] = isValid;
        }
    }

    public static void searchDownPath() {
        for (int i = 0; i < n; i++) {
            boolean isValid = true;
            for (int j = 0; j < n - 1; j++) {
                int cur = map[j][i];
                int next = map[j + 1][i];

                if (Math.abs(cur - next) > 1) {
                    isValid = false;
                    break;
                }

                if (cur - next == 1) {
                    if (j + l >= n) {
                        isValid = false;
                        break;
                    }
                    if (isInValidColRunWay(i, j + 1, j + 1 + l, map[j + 1][i])) {
                        isValid = false;
                        break;
                    }
                }
            }
            checkDownCol[i] = isValid;
        }
    }

    public static void searchUpPath() {
        for (int i = 0; i < n; i++) {
            boolean isValid = true;
            for (int j = n - 1; j > 0; j--) {
                int cur = map[j][i];
                int next = map[j - 1][i];

                if (Math.abs(cur - next) > 1) {
                    isValid = false;
                    break;
                }

                if (cur - next == 1) {
                    if (j - l < 0) {
                        isValid = false;
                        break;
                    }
                    if (isInValidColRunWay(i, j - l, j, map[j - 1][i])) {
                        isValid = false;
                        break;
                    }
                }
            }
            checkUpCol[i] = isValid;
        }
    }

    public static boolean isInValidRowRunWay(int row, int start, int end, int height) {
        for (int i = start; i < end; i++) {
            if (runways[row][i]) {
                return true;
            }
            if (map[row][i] != height) {
                return true;
            }
        }
        for (int i = start; i < end; i++) {
            runways[row][i] = true;
        }
        return false;
    }

    public static boolean isInValidColRunWay(int col, int start, int end, int height) {
        for (int i = start; i < end; i++) {
            if (runways[i][col]) {
                return true;
            }
            if (map[i][col] != height) {
                return true;
            }
        }
        for (int i = start; i < end; i++) {
            runways[i][col] = true;
        }
        return false;
    }
}
