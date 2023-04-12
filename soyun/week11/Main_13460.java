package algo230412.soyun.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13460 {

    static int N, M;
    static char[][] board;
    static Marble red;  // 빨강공
    static Marble blue; // 파랑공

    // 상 좌 하 우
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int min;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            char[] inputs = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = inputs[j];
                if (board[i][j] == 'R'){
                    red = new Marble(i, j);
                    continue;
                }
                if (board[i][j] == 'B') {
                    blue = new Marble(i, j);
                }
            }
        }

        min = Integer.MAX_VALUE;
        dfs(1, red.x, red.y, blue.x, blue.y);

        if (min == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else {
            System.out.println(min);
        }
    }

    static void dfs(int count, int rx, int ry, int bx, int by) {

        if (count > 10) {
            return;
        }

        for (int d = 0; d < 4; d++) {
            boolean isDone = false;
            Marble red = new Marble(rx, ry);
            Marble blue = new Marble(bx, by);

            // 애초에 둘 다 움직일 수 없는 경우는 짤라줌
            if (red.isBlocked(dx[d], dy[d]) && blue.isBlocked(dx[d], dy[d])) {
                continue;
            }

            while (true) {
                // 빨간 공, 파란 공 둘 다 움직일 수 있는 경우
                if (!red.isBlocked(dx[d], dy[d]) && !blue.isBlocked(dx[d], dy[d])){
                    red.move(dx[d], dy[d]);
                    blue.move(dx[d], dy[d]);

                    // 빨간 공이 구멍에 빠진 경우 -> 파란 공은 빠지지 않는 지 확인
                    if (red.isEscaped()) {
                        // 파란 공이 빠졌다면 -> 불가한 케이스
                        if (check(blue, dx[d], dy[d])) {
                            isDone = true;
                            break;
                        }
                        // 빠지지 않았다면 -> clear!
                        min = Math.min(min, count);
                        return;
                    }
                    // 파란 공이 구멍에 빠진 경우 -> 게임 끝
                    if (blue.isEscaped()) {
                        isDone = true;
                        break;
                    }
                    continue;
                }
                // 파란 공만 움직일 수 있는 경우 (벽이 아니고, 빨간공과 겹치지 않음)
                if (!blue.isBlocked(dx[d], dy[d]) && red.isBlocked(dx[d], dy[d]) && !blue.isConflicted(dx[d], dy[d], red)){
                    blue.move(dx[d], dy[d]);
                    // 파란 공이 구멍에 빠진 경우 -> 게임 끝
                    if (blue.isEscaped()){
                        isDone = true;
                        break;
                    }
                    continue;
                }
                // 빨간 공만 움직일 수 있는 경우 (벽이 아니고, 파란공과 겹치지 않음)
                if (!red.isBlocked(dx[d], dy[d]) && blue.isBlocked(dx[d], dy[d]) && !red.isConflicted(dx[d], dy[d], blue)){
                    red.move(dx[d], dy[d]);
                    // 빨간 공이 구멍에 빠진 경우 -> clear!
                    if (red.isEscaped()){
                        min = Math.min(min, count);
                        return;
                    }
                    continue;
                }
                // 둘 다 움직일 수 없는 경우
                break;
            }
            // 한 번의 움직임이 끝났지만, 어떠한 구슬도 탈출하지 못한 경우 -> 다음 케이스 탐색
            if (!isDone){
                dfs(count + 1, red.x, red.y, blue.x, blue.y);
            }
        }
    }

    static boolean check(Marble blue, int dx, int dy){
        while (!blue.isBlocked()) {
            // 구멍에 빠지면 true 리턴
            if (blue.isEscaped()) {
                return true;
            }
            blue.move(dx, dy);
        }
        return false;
    }

    static class Marble {
        int x;
        int y;

        public Marble(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isBlocked(){
            if (board[x][y] == '#') {
                return true;
            }
            return false;
        }

        public boolean isBlocked(int dx, int dy){
            if (board[x + dx][y + dy] == '#') {
                return true;
            }
            return false;
        }

        public boolean isConflicted(int dx, int dy, Marble other){
            if (x + dx == other.x && y + dy == other.y) {
                return true;
            }
            return false;
        }

        public boolean isEscaped(){
            if (board[x][y] == 'O') {
                return true;
            }
            return false;
        }

        public void move(int dx, int dy){
            this.x = this.x + dx;
            this.y = this.y + dy;
        }
    }
}