package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q19238 {

    private static int n;
    private static int m;
    private static int oil;
    private static int[][] map;
    private static int[][] directions = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
    private static int[] curPos;
    private static boolean[][] visited;
    private static List<int[]> destinations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        oil = Integer.parseInt(input[2]);
        map = new int[n][n];
        destinations = new ArrayList<>();
        destinations.add(new int[0]);
        destinations.add(new int[0]);

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String[] start = br.readLine().split(" ");
        curPos = new int[] { Integer.parseInt(start[0]) - 1, Integer.parseInt(start[1]) - 1 };

        for (int i = 2; i < m + 2; i++) {
            String[] customer = br.readLine().split(" ");
            int startRow = Integer.parseInt(customer[0]) - 1;
            int startCol = Integer.parseInt(customer[1]) - 1;
            int endRow = Integer.parseInt(customer[2]) - 1;
            int endCol = Integer.parseInt(customer[3]) - 1;
            map[startRow][startCol] = i;
            destinations.add(new int[] { endRow, endCol });
        }

        while (m > 0) {
            oil = search(curPos);
            if (oil == -1) {
                System.out.println(oil);
                return;
            }
            m--;
        }

        System.out.println(oil);
    }

    public static int search(int[] start) {
        Queue<int[]> pq = new PriorityQueue<>((v1, v2) -> {
            if (v1[0] == v2[0]) {
                if (v1[1] == v2[1]) {
                    return v1[2] - v2[2];
                }
                return v1[1] - v2[1];
            }
            return v1[0] - v2[0];
        });
        Deque<int[]> dq = new ArrayDeque<>();
        visited = new boolean[n][n];
        if (map[start[0]][start[1]] > 1) {
            pq.offer(new int[] { 0, start[0], start[1] });
        }
        dq.offerLast(new int[] { start[0], start[1], oil, 0 });
        visited[start[0]][start[1]] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();

            if (cur[2] <= 0) {
                continue;
            }

            for (int[] direction : directions) {
                int row = cur[0] + direction[0];
                int col = cur[1] + direction[1];

                if (row < 0 || row >= n || col < 0 || col >= n) {
                    continue;
                }

                if (visited[row][col]) {
                    continue;

                }

                if (map[row][col] == 1) {
                    continue;
                }

                if (map[row][col] > 1) {
                    pq.offer(new int[] { cur[3] + 1, row, col });
                }
                visited[row][col] = true;
                dq.offerLast(new int[] { row, col, cur[2] - 1, cur[3] + 1 });
            }
        }

        if (pq.isEmpty()) {
            return -1;
        }

        int[] closeCustomer = pq.poll();
        int remainOil = takeCustomer(new int[] { closeCustomer[1], closeCustomer[2], oil - closeCustomer[0] },
                destinations.get(map[closeCustomer[1]][closeCustomer[2]]));
        map[closeCustomer[1]][closeCustomer[2]] = 0;
        return remainOil;
    }

    public static int takeCustomer(int[] start, int[] dest) {
        Deque<int[]> dq = new ArrayDeque<>();
        visited = new boolean[n][n];
        dq.offerLast(new int[] { start[0], start[1], start[2], 0 });
        visited[start[0]][start[1]] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();

            if (cur[2] <= 0) {
                continue;
            }

            for (int[] direction : directions) {
                int row = cur[0] + direction[0];
                int col = cur[1] + direction[1];

                if (row < 0 || row >= n || col < 0 || col >= n) {
                    continue;
                }

                if (visited[row][col]) {
                    continue;
                }

                if (map[row][col] == 1) {
                    continue;
                }

                if (row == dest[0] && col == dest[1]) {
                    curPos = new int[] { row, col };
                    return (cur[2] - 1) + ((cur[3] + 1) * 2);
                }
                visited[row][col] = true;
                dq.offerLast(new int[] { row, col, cur[2] - 1, cur[3] + 1 });
            }
        }
        return -1;
    }
}
