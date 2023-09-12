package week27;

import java.util.*;

class 프렌즈4블록 {
    static int M,N;
    static char[][] board;
    static boolean[][] bombBoard;

    static int[][] direction = {{1, 0}, {0, 1}, {1, 1}};

    static void checkBomb(int row, int col) {
        char target = board[row][col];

        for (int[] d : direction) {
            if (board[row + d[0]][col + d[1]] != target) {
                return;
            }
        }

        bombBoard[row][col] = true;
        for (int[] d : direction) {
            bombBoard[row + d[0]][col + d[1]] = true;
        }
    }

    static int bomb() {
        int count = 0;

        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                if (bombBoard[row][col]) {
                    count++;
                    board[row][col] = ' ';
                    bombBoard[row][col] = false;
                }
            }
        }

        return count;
    }

    static void check() {
        for (int row = 0; row < M - 1; row++) {
            for (int col = 0; col < N - 1; col++) {
                if (board[row][col] == ' ') {
                    continue;
                }

                checkBomb(row, col);
            }
        }
    }

    static void down() {
        for (int col = 0; col < N; col++) {
            Queue<int[]> emptyNodes = new LinkedList<>();

            for (int row = M - 1; row >= 0; row--) {
                if (board[row][col] == ' ') {
                    emptyNodes.offer(new int[]{row, col});

                    continue;
                }

                if (emptyNodes.isEmpty()) {
                    continue;
                }

                int[] node = emptyNodes.poll();
                board[node[0]][node[1]] = board[row][col];
                board[row][col] = ' ';
                emptyNodes.offer(new int[]{row, col});
            }
        }
    }

    public int solution(int m, int n, String[] input) {
        int answer = 0;

        M = m;
        N = n;
        board = new char[M][N];
        bombBoard = new boolean[M][N];

        for (int row = 0; row < M; row++) {
            board[row] = input[row].toCharArray();
        }

        while (true) {
            check();

            int count = bomb();
            if (count == 0) {
                break;
            }

            answer += count;
            down();
        }

        return answer;
    }
}
