package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 담엔 BFS로 하자...

public class BJ_16954 {
    static int N = 8; // 격자 크기
    static int SX = 7, SY = 0, EX = 0, EY = 7; // 출발지와 도착지 위치
    static int curX, curY; // 현재 캐릭터가 위치한 좌표

    static char[][] grid;
    static boolean[][] visited;

    static List<Wall> walls;
    static int wallCnt;

    static class Wall { // 벽에 대한 정보를 담는 클래스
        int x, y;
        boolean isValid; // isValid : 유효한 벽(내려가서 없어지지 않고 아직 격자 안에 있는)인지 유무 저장

        public Wall(int x, int y, boolean isValid) {
            this.x = x;
            this.y = y;
            this.isValid = isValid;
        }
    }

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1, 0}; // 움직이지 않는 경우를 가장 마지막에 넣어준다.
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        grid = new char[N][N];
        visited = new boolean[N][N];

        walls = new ArrayList<>();   // 벽의 위치정보를 담을 List
        wallCnt = 0;                 // 현재 유효한 벽의 개수

        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                grid[i][j] = chars[j];
                if (grid[i][j] == '#') {
                    wallCnt++;
                    walls.add(new Wall(i, j, true));
                }
            }
        }

        curX = SX; // 현재위치를 줄발지로 지정
        curY = SY;

        dfs(SX, SY);

        System.out.println(0);
    }

    private static void dfs(int x, int y) {
        // 종료 조건
        if ((curX == EX && curY == EY) || wallCnt == 0) { // 유효한 벽이 없다는 건 도착지에 도달할 수 있다는 말과 동일하므로 탐색 종료
            System.out.println(1);
            System.exit(0);
        }

        // 1. 제자리에 머무는 걸 포함한 9개의 방향 탐색
        for (int d = 0; d < 9; d++) {
            int nx = x + dx[d]; // 이동할 위치
            int ny = y + dy[d];

            if (!inRange(nx, ny) || grid[nx][ny] == '#') { // 격자에서 벗어나거나 벽인 경우
                continue;
            }

            curX = nx; // 캐릭터 위치 이동
            curY = ny;

            // 2. 벽을 내렸을 때 캐릭터의 위치와 충돌하는 지 확인
            boolean isCollide = moveWallsDown();
            if (isCollide) { // 벽과 충돌한다면 벽을 다시 이전 상태로 돌려주고 continue
                moveWallsUp();
                continue;
            }

            // 3. 다음 위치에서 다시 탐색 시작
            dfs(nx, ny);

            // 4. 탐색 종료 후 원상태로 복귀
            moveWallsUp(); // 벽을 이전 상태로 돌려줌
            curX = x;      // 캐릭터의 위치를 이전 상태로 돌려줌
            curY = y;
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static boolean moveWallsDown() {
        walls.sort((w1, w2) -> -Integer.compare(w1.x , w2.x)); // 벽을 내릴 때 밑에 행에 있는 벽 먼저 내리기 위해 정렬

        boolean isCollide = false; // 캐릭터와 벽이 충돌하는지 여부

        for (Wall wall : walls) {
            int x = wall.x;
            int y = wall.y;
            boolean isValid = wall.isValid;

            if (isValid) { // 유효한 벽이라면 현재 위치를 길로 변경
                grid[x][y] = '.';
            }

            int nx = x + dx[4]; // 한 행 밑으로 이동
            int ny = y + dy[4];

            if (!inRange(nx, ny) && isValid) { // 격자 내에 있던 벽이 격자 밖으로 벗어나는 경우
                wall.isValid = false;  // 유효하지 않은 벽이라고 표시
                wallCnt--;   // 유효한 벽의 개수 감소
            }

            if (inRange(nx, ny)) { // 이동할 위치가 격자 내라면 벽으로 변경
                grid[nx][ny] = '#';
            }

            if (nx == curX && ny == curY) { // 벽이 캐릭터의 위치와 겹칠 경우
                isCollide = true;
            }

            wall.x = nx; // 실제 벽의 위치를 한 칸 내려준다.
            wall.y = ny;
        }

        return isCollide;
    }

    private static void moveWallsUp() {
        walls.sort((w1, w2) -> Integer.compare(w1.x , w2.x)); // 벽을 올릴 때 위에 있는 벽 먼저 올리기 위해 정렬

        for (Wall wall : walls) {
            int x = wall.x;
            int y = wall.y;
            boolean isValid = wall.isValid;

            if (isValid) { // 유효한 벽이라면 현재 위치를 길로 변경
                grid[x][y] = '.';
            }

            int nx = x + dx[0]; // 한 행 위로 이동
            int ny = y + dy[0];

            if (inRange(nx, ny) && !isValid) { // 격자 밖에 있던 벽이 격자 안으로 들어오는 경우
                wall.isValid = true;  // 유효한 벽이라고 표시
                wallCnt++;   // 유효한 벽의 개수 증가
            }

            if (inRange(nx, ny)) { // 이동할 위치가 격자 내라면 벽으로 변경
                grid[nx][ny] = '#';
            }

            wall.x = nx;  // 실제 벽의 위치를 한 칸 올려준다.
            wall.y = ny;
        }
    }


}
