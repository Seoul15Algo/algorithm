package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q9328 {

    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static BufferedReader br;
    private static StringBuilder sb;
    private static int r;
    private static int c;
    private static char[][] map;
    private static boolean[][] visited;
    private static Map<Character, Deque<int[]>> doors;
    private static Map<Character, Boolean> keys;
    private static int documents;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            testCase();
        }

        System.out.print(sb);
    }

    private static void testCase() throws IOException {
        String[] rc = br.readLine().split(" ");

        r = Integer.parseInt(rc[0]);
        c = Integer.parseInt(rc[1]);
        map = new char[r][c];
        visited = new boolean[r][c];
        doors = new HashMap<>();
        keys = new HashMap<>();
        documents = 0;
        for (int i = 65; i <= 90; i++) {
            doors.put((char) i, new ArrayDeque<>());
        }

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        String input = br.readLine();
        if (!input.equals("0")) {
            char[] charArray = input.toCharArray();
            for (char c : charArray) {
                keys.put(c, true);
            }
        }

        for (int i = 0; i < r; i++) {
            checkEdge(i, 0);
        }

        for (int i = 0; i < r; i++) {
            checkEdge(i, c - 1);
        }

        for (int i = 0; i < c; i++) {
            checkEdge(0, i);
        }

        for (int i = 0; i < c; i++) {
            checkEdge(r - 1, i);
        }

        sb.append(documents).append("\n");
    }

    private static void checkEdge(int row, int col) {
        if (isWall(map[row][col])) {
            return;
        }

        if (visited[row][col]) {
            return;
        }

        if (isDoor(row, col)) {
            if (keys.containsKey(toKey(map[row][col]))) {
                search(new int[]{row, col});
                return;
            }

            Deque<int[]> dq = doors.get(map[row][col]);
            dq.offerLast(new int[]{row, col});
            return;
        }

        if (isKey(row, col)) {
            keys.put(map[row][col], true);
            Deque<int[]> doorList = doors.get(toDoor(map[row][col]));
            while (!doorList.isEmpty()) {
                int[] door = doorList.pollFirst();
                search(new int[]{door[0], door[1]});
            }
        }

        if (isDocument(map[row][col])) {
            documents++;
        }

        search(new int[]{row, col});
    }

    public static void search(int[] start) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offerLast(start);
        visited[start[0]][start[1]] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();

            for (int[] direction : DIRECTIONS) {
                int row = cur[0] + direction[0];
                int col = cur[1] + direction[1];

                if (row < 0 || row >= r || col < 0 || col >= c) {
                    continue;
                }

                if (isWall(map[row][col])) {
                    continue;
                }

                if (visited[row][col]) {
                    continue;
                }

                if (isDoor(row, col)) {
                    if (keys.containsKey(toKey(map[row][col]))) {
                        dq.offerLast(new int[]{row, col});
                        visited[row][col] = true;
                        continue;
                    }

                    Deque<int[]> doorList = doors.get(map[row][col]);
                    visited[row][col] = true;
                    doorList.offerLast(new int[]{row, col});
                    continue;
                }

                if (isKey(row, col)) {
                    keys.put(map[row][col], true);
                    visited[row][col] = true;
                    dq.offerLast(new int[]{row, col});
                    Deque<int[]> doorList = doors.get(toDoor(map[row][col]));
                    while (!doorList.isEmpty()) {
                        int[] door = doorList.pollFirst();
                        visited[door[0]][door[1]] = true;
                        dq.offerLast(new int[]{door[0], door[1]});
                    }
                    continue;
                }

                if (isDocument(map[row][col])) {
                    documents++;
                }

                visited[row][col] = true;
                dq.offerLast(new int[]{row, col});
            }
        }
    }

    private static boolean isWall(char value) {
        return value == '*';
    }

    private static char toKey(char door) {
        return (char) (door + 32);
    }

    private static char toDoor(char key) {
        return (char) (key - 32);
    }

    private static boolean isDocument(char value) {
        return value == '$';
    }

    private static boolean isKey(int row, int col) {
        return map[row][col] >= 97 && map[row][col] <= 122;
    }

    private static boolean isDoor(int row, int col) {
        return map[row][col] >= 65 && map[row][col] <= 90;
    }
}