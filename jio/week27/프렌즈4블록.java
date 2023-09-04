package week27;

import java.util.*;

class 프렌즈4블록 {

    static char[][] grid;
    static boolean[][] bombGrid;
    static int[] dx = {0, 0, 1, 1};
    static int[] dy = {0, 1, 1, 0};
    static int m, n;

    public int solution(int M, int N, String[] board) {
        m = M;
        n = N;

        grid = new char[m][n];

        for(int x=0; x < board.length; x++) {
            for(int y=0; y < board[x].length(); y++) {
                grid[x][y] = board[x].charAt(y);
            }
        }

        int totalBomb = 0;
        int cnt = 0;

        while(true) {
            bombGrid = new boolean[m][n];
            int bombCnt = checkBomb();

            if(bombCnt == 0) { // 더 이상 폭탄이 존재 하지 않는 경우
                break;
            }

            totalBomb += bombCnt;

            bomb();

            cnt++;
        }


        return totalBomb;
    }

    private static int checkBomb() { // 폭탄 체크
        for(int x=0; x<m-1; x++) {
            for(int y=0; y<n-1; y++) {
                char cur = grid[x][y];

                if(cur < 'A' || cur > 'Z') {
                    continue;
                }

                int cnt = 1;

                for(int dir=1; dir < 4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    if(grid[nx][ny] == cur) {
                        cnt++;
                    }
                }

                if(cnt == 4) {
                    for(int dir=0; dir < 4; dir++) {
                        int nx = x + dx[dir];
                        int ny = y + dy[dir];

                        bombGrid[nx][ny] = true;
                    }
                }
            }
        }

        return countBomb();
    }

    private static int countBomb() { // 폭탄 개수 확인
        int count = 0;

        for(int x=0; x<m; x++) {
            for(int y=0; y<n; y++) {
                if(bombGrid[x][y]) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void bomb() { // 폭탄을 터뜨리고 중력 작용
        char[][] newGrid = new char[m][n];

        for(int y=0; y<n; y++) {
            int idx = m-1;
            for(int x=m-1; x>-1; x--) {
                if(bombGrid[x][y]) {
                    continue;
                }

                newGrid[idx][y] = grid[x][y];
                idx--;
            }
        }

        grid = newGrid;
    }
}