package week23;

import java.util.*;

class 거리두기_확인하기 {
    static final int ROOM_SIZE = 5;
    static int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static boolean process(String[] place) {
        for (int row = 0; row < ROOM_SIZE; row++) {
            for (int col = 0; col < ROOM_SIZE; col++) {
                if (place[row].charAt(col) != 'P') {
                    continue;
                }

                if (!check(row, col, place)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean check(int row, int col, String[] place) {
        boolean[] flag = new boolean[4];
        Arrays.fill(flag, true);

        for (int d = 0; d < 4; d++) {
            int nRow = row + direction[d][0];
            int nCol = col + direction[d][1];

            if (!inMap(nRow, nCol)) {
                continue;
            }

            if (place[nRow].charAt(nCol) == 'X') {
                continue;
            }

            flag[d] = false;

            if (place[nRow].charAt(nCol) == 'P') {
                return false;
            }

            // O : 빈테이블
            nRow += direction[d][0];
            nCol += direction[d][1];

            if (!inMap(nRow, nCol)) {
                continue;
            }

            if (place[nRow].charAt(nCol) == 'P') {
                return false;
            }
        }

        for (int d = 0; d < 3; d++) {
            int nRow = row + (direction[d][0] + direction[d + 1][0]);
            int nCol = col + (direction[d][1] + direction[d + 1][1]);

            if (!inMap(nRow, nCol)) {
                continue;
            }

            if (place[nRow].charAt(nCol) != 'P') {
                continue;
            }

            if (flag[d] && flag[d + 1]) {
                continue;
            }

            return false;
        }

        return true;
    }

    public static boolean inMap(int row, int col) {
        return row >= 0 && row < ROOM_SIZE && col >= 0 && col < ROOM_SIZE;
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[ROOM_SIZE];

        for (int i = 0; i < ROOM_SIZE; i++) {
            if (process(places[i])) {
                answer[i] = 1;
            }
        }

        return answer;
    }
}