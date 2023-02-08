package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main2206 {

    private static int n;
    private static int m;
    private static String[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        map = new String[n][m];
        int[][] visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().split("");
        }

        search(visited);
    }

    public static void search(int[][] visited) {
        int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Deque<int[]> q = new ArrayDeque<>();
        q.offerLast(new int[]{0, 0, 1});
        visited[0][0] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();

            if (cur[0] == n - 1 && cur[1] == m - 1) {
                System.out.println(cur[2]);
                return;
            }

            for (int[] d : direction) {
                int row = cur[0] + d[0];
                int col = cur[1] + d[1];

                if (row < 0 || row >= n || col < 0 || col >= m) {
                    continue;
                }

                if (visited[row][col] == 1) {
                    continue;
                }

                if(visited[cur[0]][cur[1]] == 2) {
                    if (map[row][col].equals("0") && visited[row][col] != 2 ) {
                        q.offerLast(new int[]{row, col, cur[2] + 1});
                        visited[row][col] = 2;
                        
                        continue;
                    }
                }
                
                if(visited[cur[0]][cur[1]] == 1) {
                    if (map[row][col].equals("1") && visited[row][col] != 2 ) {
                        q.offerLast(new int[]{row, col, cur[2] + 1});
                        visited[row][col] = 2;
                        
                        continue;
                    }
                    
                    if (map[row][col].equals("0")) {
                        q.offerLast(new int[]{row, col, cur[2] + 1});
                        visited[row][col] = 1;
                        
                        continue;
                    }
                }
            }
        }
        System.out.println(-1);
    }
}