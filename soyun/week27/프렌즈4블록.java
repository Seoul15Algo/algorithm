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
                if (board[i][j] == ' '){
                    continue;
                }
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
                int row = i + 1;
                while(row < m) {
                    if (board[row][j] == ' ') {
                        board[row][j] = board[row - 1][j];
                        board[row - 1][j] = ' ';
                    }
                    row++;
                }
            }
        }
    }
}