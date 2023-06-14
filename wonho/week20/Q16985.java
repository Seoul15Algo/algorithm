package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Q16985 {

    private static int[][][] map;
    private static int[][][] newMap;
    private static boolean[] visited;
    private static int[] order;
    private static int[][] directions = {
            { 0, 0, 1 },
            { 0, 1, 0 },
            { 0, -1, 0 },
            { 0, 0, -1 },
            { -1, 0, 0 },
            { 1, 0, 0 }
    };

    private static int count = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[5][5][5];
        newMap = new int[5][5][5];
        order = new int[5];
        visited = new boolean[5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        perm(0);
        search(0);

        System.out.println(count == Integer.MAX_VALUE ? -1 : count);
    }

    public static void perm(int cur) {
        if (cur == 5) {
            search(0);
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (visited[i]) {
                continue;
            }
            order[cur] = i;
            visited[i] = true;
            perm(cur + 1);
            visited[i] = false;
        }
    }

    public static void search(int cur) {
        if (cur == 5) {
            if (newMap[0][0][0] == 1) {
                count = Math.min(count, bfs(new int[] { 0, 0, 0 }));
            }
            if (newMap[0][4][0] == 1) {
                count = Math.min(count, bfs(new int[] { 0, 4, 0 }));
            }
            if (newMap[0][0][4] == 1) {
                count = Math.min(count, bfs(new int[] { 0, 0, 4 }));
            }
            if (newMap[0][4][4] == 1) {
                count = Math.min(count, bfs(new int[] { 0, 4, 4 }));
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            rotate(cur, order[cur], i);
            search(cur + 1);
        }
    }

    public static void rotate(int index, int cur, int rot) {
        if (rot == 0) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newMap[index][i][j] = map[cur][i][j];
                }
            }
            return;
        }
        if (rot == 1) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newMap[index][j][4 - i] = map[cur][i][j];
                }
            }
            return;
        }
        if (rot == 2) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newMap[index][4 - i][4 - j] = map[cur][i][j];
                }
            }
            return;
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                newMap[index][4 - j][i] = map[cur][i][j];
            }
        }
    }

    public static int bfs(int[] start) {
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][][] visited = new boolean[5][5][5];
        dq.offerLast(new int[] { start[0], start[1], start[2], 0 });
        visited[start[0]][start[1]][start[2]] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            for (int[] direction : directions) {
                int upDown = direction[0] + cur[0];
                int row = direction[1] + cur[1];
                int col = direction[2] + cur[2];

                if (upDown < 0 || upDown >= 5 || row < 0 || row >= 5 || col < 0 || col >= 5) {
                    continue;
                }

                if (newMap[upDown][row][col] == 0) {
                    continue;
                }

                if (visited[upDown][row][col]) {
                    continue;
                }

                if (upDown == 4) {
                    if ((start[1] == 4 && row == 0) || (start[1] == 0 && row == 4)) {
                        if ((start[2] == 4 && col == 0) || (start[2] == 0 && col == 4)) {
                            return cur[3] + 1;
                        }
                    }
                }
                visited[upDown][row][col] = true;
                dq.offerLast(new int[] { upDown, row, col, cur[3] + 1 });
            }
        }
        return Integer.MAX_VALUE;
    }
}