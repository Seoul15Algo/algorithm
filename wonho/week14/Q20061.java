package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q20061 {

    private static boolean[][] map;
    private static int point;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new boolean[10][10];
        point = 0;
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int t = Integer.parseInt(input[0]);
            int x = Integer.parseInt(input[1]);
            int y = Integer.parseInt(input[2]);
            setBlock(t, x, y);
            checkGreenPoints();
            checkGreenSoftBlocks();
            checkBluePoints();
            checkBlueSoftBlocks();
        }
        System.out.println(point);
        System.out.println(countRemainBlueBlocks() + countRemainGreenBlocks());
    }

    public static void setBlock(int type, int x, int y) {
        switch (type) {
            case 1:
                moveRight(Arrays.asList(new int[] { x, y }));
                moveDown(Arrays.asList(new int[] { x, y }));
                break;
            case 2:
                moveRight(Arrays.asList(new int[] { x, y + 1 }, new int[] { x, y }));
                moveDown(Arrays.asList(new int[] { x, y + 1 }, new int[] { x, y }));
                break;
            case 3:
                moveRight(Arrays.asList(new int[] { x + 1, y }, new int[] { x, y }));
                moveDown(Arrays.asList(new int[] { x + 1, y }, new int[] { x, y }));
                break;
        }
    }

    public static void moveRight(List<int[]> squares) {
        List<int[]> prev = new ArrayList<>(squares);
        while (true) {
            List<int[]> newSquares = new ArrayList<>();
            for (int[] square : prev) {
                if (square[1] == 9 || map[square[0]][square[1] + 1]) {
                    for (int[] result : prev) {
                        map[result[0]][result[1]] = true;
                    }
                    return;
                }

                newSquares.add(new int[] { square[0], square[1] + 1 });
            }

            prev = new ArrayList<>(newSquares);
        }
    }

    public static void moveDown(List<int[]> squares) {
        List<int[]> prev = new ArrayList<>(squares);
        while (true) {
            List<int[]> newSquares = new ArrayList<>();
            for (int[] square : prev) {
                if (square[0] == 9 || map[square[0] + 1][square[1]]) {
                    for (int[] result : prev) {
                        map[result[0]][result[1]] = true;
                    }
                    return;
                }

                newSquares.add(new int[] { square[0] + 1, square[1] });
            }

            prev = new ArrayList<>(newSquares);
        }
    }

    public static void checkGreenPoints() {
        for (int i = 6; i < 10; i++) {
            boolean correct = true;
            for (int j = 0; j < 4; j++) {
                if (!map[i][j]) {
                    correct = false;
                    break;
                }
            }
            if (correct) {
                for (int j = 0; j < 4; j++) {
                    map[i][j] = false;
                }
                point++;
                shiftGreenBlocks(i);
            }
        }
    }

    private static void shiftGreenBlocks(int x) {
        for (int i = x; i > 4; i--) {
            System.arraycopy(map[i - 1], 0, map[i], 0, 4);
        }

        for (int i = 0; i < 4; i++) {
            map[4][i] = false;
        }
    }

    public static void checkGreenSoftBlocks() {
        int row = 0;
        for (int i = 4; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j]) {
                    row++;
                    break;
                }
            }
        }

        while (row > 0) {
            for (int i = 0; i < 4; i++) {
                map[9][i] = false;
            }
            shiftGreenBlocks(9);
            row--;
        }
    }

    public static void checkBluePoints() {
        for (int i = 6; i < 10; i++) {
            boolean correct = true;
            for (int j = 0; j < 4; j++) {
                if (!map[j][i]) {
                    correct = false;
                    break;
                }
            }
            if (correct) {
                for (int j = 0; j < 4; j++) {
                    map[j][i] = false;
                }
                point++;
                shiftBlueBlocks(i);
            }
        }
    }

    private static void shiftBlueBlocks(int x) {
        for (int i = x; i > 4; i--) {
            for (int j = 0; j < 4; j++) {
                map[j][i] = map[j][i - 1];
            }
        }

        for (int i = 0; i < 4; i++) {
            map[i][4] = false;
        }
    }

    public static void checkBlueSoftBlocks() {
        int col = 0;
        for (int i = 4; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[j][i]) {
                    col++;
                    break;
                }
            }
        }

        while (col > 0) {
            for (int i = 0; i < 4; i++) {
                map[i][9] = false;
            }
            shiftBlueBlocks(9);
            col--;
        }
    }

    public static int countRemainGreenBlocks() {
        int count = 0;
        for (int i = 6; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int countRemainBlueBlocks() {
        int count = 0;
        for (int i = 6; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[j][i]) {
                    count++;
                }
            }
        }
        return count;
    }
}