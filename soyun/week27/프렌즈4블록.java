import java.util.*;

class Solution {

    private char[][] board;
    private int popped;

    public int solution(int m, int n, String[] input) {
        board = new char[m][n];
        popped = 0;
        for (int i = 0; i < m; i++) {
            board[i] = input[i].toCharArray();
        }
        while(pop(m, n)) {
            drag(m, n);
        }
        return popped;
    }

    public boolean pop(int m, int n) {
        boolean isPopped = false;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (isSame(i, j)) {
                    board[i][j] = Character.toLowerCase(board[i][j]);
                    board[i + 1][j] = Character.toLowerCase(board[i + 1][j]);
                    board[i][j + 1] = Character.toLowerCase(board[i][j + 1]);
                    board[i + 1][j + 1] = Character.toLowerCase(board[i + 1][j + 1]);
                    isPopped = true;
                }
            }
        }
        return isPopped;
    }

    public boolean isSame(int x, int y) {
        if (board[x][y] == ' ') {
            return false;
        }
        char a = Character.toUpperCase(board[x][y]);
        char b = Character.toUpperCase(board[x + 1][y]);
        char c = Character.toUpperCase(board[x][y + 1]);
        char d = Character.toUpperCase(board[x + 1][y + 1]);
        return (a == b) && (a == c) && (a == d);
    }

    public void drag(int m, int n) {
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (Character.isLowerCase(board[i][j])) {
                    board[i][j] = ' ';
                    popped++;
                    continue;
                }
                if (i + 1 >= m) {
                    continue;
                }
                if (Character.isUpperCase(board[i + 1][j])) {
                    continue;
                }
                for (int next = i + 2; next < m; next++) {
                    if (Character.isUpperCase(board[next][j])) {
                        board[next - 1][j] = board[i][j];
                        board[i][j] = ' ';
                        break;
                    }
                }
            }
        }
    }
}