package week26;

import java.util.*;

class 프렌즈4블록 {
    private char[][] map;
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        map = new char[m][n];
        
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }
        
        while (true) {
            boolean transformed = false;
            char[][] prevMap = new char[m][n];
            for (int i = 0; i < m; i++) {
                prevMap[i] = Arrays.copyOf(map[i], n);
            }

            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char stand = prevMap[i][j];
                    if (stand == '0') {
                        continue;
                    }
                    if (stand != prevMap[i + 1][j] || stand != prevMap[i][j + 1] || stand != prevMap[i + 1][j + 1]) {
                        continue;
                    }
                    transformed = true;
                    if (map[i][j] != '0') {
                        answer++;
                        map[i][j] = '0';
                    }
                    if (map[i + 1][j] != '0') {
                        answer++;
                        map[i + 1][j] = '0';
                    }
                    if (map[i][j + 1] != '0') {
                        answer++;
                        map[i][j + 1] = '0';
                    }
                    if (map[i + 1][j + 1] != '0') {
                        answer++;
                        map[i + 1][j + 1] = '0';
                    }
                }
            }
            if (!transformed) {
                return answer;
            }

            for (int col = 0; col < n; col++) {
                for (int row = m - 1; row >= 0; row--) {
                    if (map[row][col] == '0') {
                        int start = row;
                        while (start >= 0 && map[start][col] == '0') {
                            start--;
                        }
                        if (start == -1) {
                            break;
                        }
                        map[row][col] = map[start][col];
                        map[start][col] = '0';
                    }
                }
            }
        }
    }
}