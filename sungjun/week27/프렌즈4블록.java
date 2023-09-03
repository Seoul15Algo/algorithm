package week27;

public class 프렌즈4블록 {
    static char[][] map;
    static boolean[][] visited;

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        map = new char[m][n];

        for(int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        while(true) {
            boolean flag = true;
            visited = new boolean[m][n];

            for(int i = 0; i < m-1; i++) {
                for(int j = 0; j < n-1; j++) {
                    if(visited[i][j]) continue;
                    if(map[i][j] == '@') continue;
                    if(isFourBlockRight(i, j)) {     // 2x2 형태인지 체크
                        checkLinkedBlock(i, j);     // 이어진 2x2 블록이 있는지 체크
                        flag = false;
                    }
                }
            }

            if(flag) break;     // 더이상 지울 블록이 없음
            pop();      // 블록 지우기
            move();     // 블록 내리기
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] != '@') answer++;
            }
        }

        return m * n - answer;
    }

    // 오른쪽 아래 방향으로 2x2 블록이 존재하는지 확인
    public boolean isFourBlockRight(int r, int c) {
        for(int i = r; i < r+2 && i < map.length; i++) {
            for(int j = c; j < c+2 && j < map[0].length; j++) {
                if(map[i][j] == map[r][c]) continue;
                return false;
            }
        }

        visited[r][c] = true;
        return true;
    }

    // 왼쪽 아래 방향으로 2x2 블록이 존재하는지 확인
    public boolean isFourBlockLeft(int r, int c) {
        for(int i = r; i < r+2 && i < map.length; i++) {
            for(int j = c; j > c-2 && j >= 0; j--) {
                if(map[i][j] == map[r][c]) continue;
                return false;
            }
        }

        visited[r][c-1] = true;
        return true;
    }

    // 2x2 블록 안에서 이어지는 다른 2x2 블록이 있는지 확인
    public void checkLinkedBlock(int r, int c) {
        for(int i = r; i < r+2 && i < map.length; i++) {
            for(int j = c; j < c+2 && j < map[0].length; j++) {
                if(visited[i][j]) continue;
                // 오른쪽 아래 방향 확인
                if(isFourBlockRight(i, j)) {
                    checkLinkedBlock(i, j);
                }
                // 왼쪽 아래 방향 확인
                if(j > 0 && i == r+1 && j == c) {
                    if(isFourBlockLeft(i, j)) {
                        checkLinkedBlock(i, j-1);
                    }
                }
            }
        }
    }

    // 블록 지우기
    public void pop() {
        for(int i = 0; i < map.length-1; i++) {
            for(int j = 0; j < map[0].length-1; j++) {
                if(visited[i][j]) {
                    map[i][j] = '@';
                    map[i+1][j] = '@';
                    map[i][j+1] = '@';
                    map[i+1][j+1] = '@';
                }
            }
        }
    }

    // 블록 내리기
    public void move() {
        for(int i = 0; i < map[0].length; i++) {
            for(int j = map.length-2; j >= 0; j--) {
                if(map[j][i] != '@') {
                    if(j == map.length-1) break;
                    if(map[j+1][i] == '@') {
                        int idx = j+1;

                        while(idx < map.length) {
                            if(map[idx][i] != '@') {
                                break;
                            }
                            idx++;
                        }

                        idx--;

                        map[idx][i] = map[j][i];
                        map[j][i] = '@';
                    }
                }
            }
        }
    }
}
