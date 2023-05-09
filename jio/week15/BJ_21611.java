package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_21611 {
    static int N, M;
    static int SX, SY;
    static int[] bombCnt = new int[4];
    static int[][] grid;

    static Blizzard[] blizzards;
    static class Blizzard{
        int dir, dis;

        public Blizzard(int dir, int dis) {
            this.dir = dir;
            this.dis = dis;
        }
    }

    static class Pair{
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        SX = (N + 1) / 2 - 1; // 상어 위치
        SY = (N + 1) / 2 - 1;

        grid = new int[N][N]; // 격자 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        blizzards = new Blizzard[M]; // 블리자드 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            blizzards[i] = new Blizzard(dir, dis);
        }

        for (int i = 0; i < M; i++) {

            // 1. 블리자드 마법 실행
            doBlizzard(i);

            // 2. 연속된 구슬 폭발
            bomb();

            // 3. 구슬 변환
            changeMarble();
        }

        int ans = 0;
        for (int i = 1; i <= 3; i++) {
            ans += i * bombCnt[i];
        }
        System.out.println(ans);
    }

    private static void bomb() { // 4개 이상 연속한 구슬을 폭발 시켜줌
        while (true) {
            List<List<Pair>> groups = findGroup(); // 격자 내 존재하는 그룹 정보를 받아옴
            int possibleCnt = 0; // 4개 이상 연속한 그룹의 개수

            for (List<Pair> group : groups) {
                if (group.size() >= 4) { // 그룹 내 존재하는 원소가 4개 이상인 경우
                    Pair cur = group.get(0);
                    int marbleType = grid[cur.x][cur.y];
                    bombCnt[marbleType] += group.size(); // 폭발하는 구슬의 개수 갱신

                    for (Pair pair : group) { // 폭발한 구슬은 -1로 변경
                        grid[pair.x][pair.y] = -1;
                    }

                    possibleCnt++;
                }
            }

            if (possibleCnt == 0) { // 4개 이상 원소를 지닌 그룹이 없는 경우
                break;
            }

            moveSquare(); //
        }
    }

    private static void doBlizzard(int round) {
        int dir = blizzards[round].dir;
        int dis = blizzards[round].dis;

        int[] dx = {0, -1, 1, 0, 0};
        int[] dy = {0, 0, 0, -1, 1};

        for (int i = 1; i <= dis; i++) { // 얼음 파편으로 구슬 파괴
            int nx = SX + dx[dir] * i;
            int ny = SY + dy[dir] * i;
            grid[nx][ny] = -1;
        }

        moveSquare();

    }

    private static void moveSquare() { // (0, 0) 부터 달팽이 모양으로 순회하며 파괴되거나 폭발한 구슬이 있을 경우 땡겨 줌
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int x = 0;  // 초기 위치
        int y = 0;
        int dir = 0; // 이동 방향
        int num = N * N - 1;

        int[][] visited = new int[N][N]; // 방문 처리를 위해 방문한 순서를 기입
        visited[0][0] = num;

        while (true) {
            if (x == SX && y == SY) { // 상어 위치에 도달 시 종료
                break;
            }

            if (grid[x][y] == -1) { // 얼음 파편에 파괴되거나 폭발한 구슬일 경우
                pullMarble(x, y, dir, visited, dx, dy);
            }

            int nx = x + dx[dir];
            int ny = y + dy[dir];


            if (!inRange(nx, ny) || visited[nx][ny] != 0) { // 격자를 벗어나거나 이미 방문한 경우
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            visited[nx][ny] = --num;
            x = nx; // 다음 위치로 이동
            y = ny;
        }
    }

    private static void pullMarble(int x, int y, int dir, int[][] visited, int[] dx, int[] dy) {
        dir = (dir + 2) % 4; // 탐색할 방향을 기존 방향의 반대 방향으로 설정

        while (true) {
            if (grid[x][y] == 0) { // 구슬이 없는 칸에 도달 시
                break;
            }

            if (x == 0 && y == 0) { // 0, 0 도달 시
                grid[x][y] = 0;
                break;
            }

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (!inRange(nx, ny) || (visited[nx][ny] - visited[x][y]) != 1) { // 격자를 벗어나거나 이미 방문한 경우
                dir = (dir - 1 + 4) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            grid[x][y] = grid[nx][ny]; // 다음 칸에 있던 값을 현재 칸에 덮어 씌워준다.(한 칸씩 당기는 작업)
            x = nx;
            y = ny;
        }
    }

    private static List<List<Pair>> findGroup() {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int x = 0;
        int y = 0;
        int dir = 0;
        int num = N * N - 1;

        int[][] visited = new int[N][N];
        visited[0][0] = num;

        List<List<Pair>> groups = new ArrayList<>();
        List<Pair> group = new ArrayList<>();

        while (true) {
            if (x == SX && y == SY) { // 상어 위치에 도달 시 종료
                break;
            }

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (!inRange(nx, ny) || visited[nx][ny] != 0) { // 격자를 벗어나거나 이미 방문한 경우
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            if (grid[x][y] == 0) { // 구슬이 아닐 경우 이동만 한다.
                visited[nx][ny] = --num;
                x = nx;
                y = ny;
                continue;
            }

            // 구슬일 경우
            group.add(new Pair(x, y)); // 그룹에 이동할 위치 추가

            if (grid[nx][ny] != grid[x][y]) { // 연속되지 않는 구슬을 만날 경우
                List<Pair> pairs = new ArrayList<>();
                for (Pair cur : group) {
                    pairs.add(new Pair(cur.x, cur.y));
                }
                groups.add(pairs);
                group.clear();
            }

            visited[nx][ny] = --num;
            x = nx;
            y = ny;
        }

        return groups;
    }

    private static void changeMarble() {
        int[][] changeGrid = new int[N][N]; // 변화한 구슬 상태를 저장할 격자

        Stack<Integer> stack = new Stack<>();

        List<List<Pair>> groups = findGroup(); // 1. 현재 격자의 그룹 정보를 받아옴

        for (List<Pair> group : groups) { // 2. 각 그룹에 맞게 A, B 정보를 스택에 추가(추후 꺼낼 떄 A 먼저 꺼내기 위해 B를 먼저 넣어준다.)
            Pair cur = group.get(0);
            stack.push(grid[cur.x][cur.y]); // stack B 정보 추가
            stack.push(group.size()); // stack에 A 정보 추가
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        int x = SX;
        int y = SY - 1;
        int dir = 0;    // 이동 방향
        int round = 1;  // 몇 번째 겹 까지 이동 가능한지

        while (true) {
            if (x == 0 && y == 0) { // 0, 0 도달 시 종료
                break;
            }

            if (stack.isEmpty()) {  // 스택에 더 이상 그룹에 대한 정보가 없다면 종료
                break;
            }

            changeGrid[x][y] = stack.pop(); // 격자에 그룹 정보 표시

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (!reverseInRange(nx, ny, round)) { // 현재 round에서 이동할 수 있는 범위를 벗어난 경우
                dir = (dir + 1) % 4; // 다음 이동 방향으로 변경

                if (dir == 0) {  // 네 방향을 모두 돌고 다시 초기 이동 방향으로 돌아오는 경우 라운드 증가
                    round++;
                }

                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            x = nx; // 다음 위치로 이동
            y = ny;
        }

        for (int i = 0; i < N; i++) {  // 변화한 구슬 상태를 원본 격자에 복사
            for (int j = 0; j < N; j++) {
                grid[i][j] = changeGrid[i][j];
            }
        }
    }

    private static boolean reverseInRange(int x, int y, int round) {
        return x >= SX - round && x <= SX + round && y >= SY - round && y <= SY + round;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
