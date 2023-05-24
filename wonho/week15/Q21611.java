package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q21611 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] startOrder = { 7, 3, 1, 5 };
    private static int[][] moveDirections = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
    private static int[] boomCounts = { 0, 0, 0 };
    private static List<Integer> marbles = new ArrayList<>();
    private static int n, m;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        input();
        addMarbles();
        if (marbles.size() == 0) {
            System.out.println(0);
            return;
        }

        blizzard();

        System.out.println(boomCounts[0] + (boomCounts[1] * 2) + (boomCounts[2] * 3));
    }

    private static void input() throws IOException {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }
    }

    private static void addMarbles() {
        int straightDistance = 1;
        int straightCount = 0;
        int turnCount = 0;
        int curRow = n / 2;
        int curCol = n / 2;
        int curDirection = 0;
        while (true) {
            curRow += moveDirections[curDirection][0];
            curCol += moveDirections[curDirection][1];
            if (map[curRow][curCol] == 0) {
                break;
            }
            marbles.add(map[curRow][curCol]);

            if (curRow == 0 && curCol == 0) {
                break;
            }

            straightCount++;
            if (straightCount == straightDistance) {
                straightCount = 0;
                curDirection = (curDirection + 1) % 4;
                turnCount++;
                if (turnCount == 2) {
                    turnCount = 0;
                    straightDistance++;
                }
            }
        }
    }

    private static void blizzard() throws IOException {
        for (int i = 0; i < m; i++) {
            String[] blizzard = br.readLine().split(" ");
            int direction = Integer.parseInt(blizzard[0]) - 1;
            int distance = Integer.parseInt(blizzard[1]);
            Deque<Integer> indexes = new ArrayDeque<>();

            int curMarbleCount = marbles.size();
            int count = 2;
            for (int j = 0; j < distance; j++) {
                int index = (4 * count * (count - 1)) - ((8 - startOrder[direction]) * (count - 1)); // 공식
                if (index > curMarbleCount) {
                    break;
                }
                indexes.offerLast(index - 1);
                count++;
            }

            while (!indexes.isEmpty()) {
                int index = indexes.pollLast();
                marbles.remove(index);
            }

            while (boom()) {
            }

            transform();
        }
    }

    public static boolean boom() {
        boolean boom = false;
        List<Integer> newMarbles = new ArrayList<>();
        int marbleCount = marbles.size();
        int left = 0;
        int right = 0;
        while (right < marbleCount) {
            if (marbles.get(left).equals(marbles.get(right))) {
                right++;
                continue;
            }

            int num = marbles.get(left);
            if (right - left < 4) {
                for (int i = 0; i < right - left; i++) {
                    newMarbles.add(num);
                }
            } else {
                boom = true;
                boomCounts[num - 1] += (right - left);
            }
            left = right;
        }

        if (left < marbleCount) {
            int num = marbles.get(left);
            if (right - left < 4) {
                for (int i = 0; i < right - left; i++) {
                    newMarbles.add(num);
                }
            } else {
                boom = true;
                boomCounts[num - 1] += (right - left);
            }
        }

        marbles = new ArrayList<>(newMarbles);
        return boom;
    }

    public static void transform() {
        List<Integer> newMarbles = new ArrayList<>();
        int marbleCount = marbles.size();
        int left = 0;
        int right = 0;
        while (right < marbleCount) {
            if (marbles.get(left).equals(marbles.get(right))) {
                right++;
                continue;
            }

            int num = marbles.get(left);
            newMarbles.add(right - left);
            newMarbles.add(num);
            left = right;
        }

        if (left < marbleCount) {
            int num = marbles.get(left);
            newMarbles.add(right - left);
            newMarbles.add(num);
        }

        marbles = new ArrayList<>(newMarbles.subList(0, Math.min(newMarbles.size(), n * n - 1)));
    }
}
