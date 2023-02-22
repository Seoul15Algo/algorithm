package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9663 {

    private static int n;
    private static boolean[] visitedCol;
    private static boolean[] visitedRightUpCross;
    private static boolean[] visitedRightDownCross;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        count = 0;
        visitedCol = new boolean[n];
        visitedRightUpCross = new boolean[n * 2 + 1];
        visitedRightDownCross = new boolean[n * 2 + 1];

        for (int i = 0; i < n; i++) {
            visitedCol[i] = true;
            visitedRightUpCross[i] = true;
            visitedRightDownCross[n - 1 - i] = true;
            search(0, i);
            visitedCol[i] = false;
            visitedRightUpCross[i] = false;
            visitedRightDownCross[n - 1 - i] = false;
        }
        System.out.println(count);
    }

    public static void search(int row, int col) {
        if (row == n - 1) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i >= col - 1 && i <= col + 1) {
                continue;
            }

            if (visitedCol[i]) {
                continue;
            }

            if (visitedRightUpCross[row + 1 + i]) {
                continue;
            }

            if (visitedRightDownCross[n + (row - i)]) {
                continue;
            }

            visitedCol[i] = true;
            visitedRightUpCross[row + 1 + i] = true;
            visitedRightDownCross[n + (row - i)] = true;
            search(row + 1, i);
            visitedCol[i] = false;
            visitedRightUpCross[row + 1 + i] = false;
            visitedRightDownCross[n + (row - i)] = false;
        }
    }
}