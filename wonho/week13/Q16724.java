package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Q16724 {

    private static String[][] map;
    private static int[][] visited;
    private static int total;
    private static Map<String, int[]> direction;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        map = new String[n][m];
        visited = new int[n][m];
        direction = new HashMap<>();
        direction.put("U", new int[]{-1, 0});
        direction.put("D", new int[]{1, 0});
        direction.put("L", new int[]{0, -1});
        direction.put("R", new int[]{0, 1});
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().split("");
        }

        total = 0;
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] != 0) {
                    continue;
                }
                visited[i][j] = count;
                search(new int[]{i, j}, count);
                count++;
            }
        }

        System.out.println(total);
    }

    public static void search(int[] cur, int count) {
        int curRow = cur[0];
        int curCol = cur[1];

        int[] addDirection = direction.get(map[curRow][curCol]);
        int row = curRow + addDirection[0];
        int col = curCol + addDirection[1];
        if (visited[row][col] != 0) {
            if (visited[row][col] == count) {
                total++;
            }
            return;
        }
        visited[row][col] = count;
        search(new int[]{row, col}, count);
    }
}