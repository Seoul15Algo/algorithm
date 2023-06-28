import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16985 {
    static final int SIZE = 5;

    static int[][][] grid, originGrid;
    static boolean[][][] visited;

    static int minDis;
    static int[] orders;
    static boolean[] permVisited;

    static class Pair {
        int x, y, z, dis;

        public Pair(int x, int y, int z, int dis) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.dis = dis;
        }
    }

    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        originGrid = new int[5][5][5]; // 입력으로 주어지는 격자를 저장할 3차원 배열

        for (int height = 0; height < SIZE; height++) {
            for (int row = 0; row < SIZE; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int col = 0; col < SIZE; col++) {
                    originGrid[height][row][col] = Integer.parseInt(st.nextToken());
                }
            }
        }

        minDis = Integer.MAX_VALUE; // 정답이 되는 최소 이동거리

        orders = new int[SIZE];  // 순열 정보를 담을 배열
        permVisited = new boolean[SIZE]; // 순열을 사용하기 위한 방문 배열

        perm(0);

        if (minDis == Integer.MAX_VALUE) { // 도착지에 도달하지 못하는 경우
            minDis = -1;
        }

        System.out.println(minDis);
    }

    private static void perm(int cnt) { // 순열을 활용하여 판을 쌓는 순서의 모든 경우를 확인한다.
        if (cnt == SIZE) {
            grid = new int[SIZE][SIZE][SIZE]; // 회전 시킬 판

            for (int z = 0; z < SIZE; z++) {
                for (int x = 0; x < SIZE; x++) {
                    for (int y = 0; y < SIZE; y++) {
                        grid[z][x][y] = originGrid[orders[z]][x][y];
                    }
                }
            }

            simulation(0);
            return;
        }

        for (int i = 0; i < SIZE; i++) {
            if (permVisited[i]) {
                continue;
            }

            orders[cnt] = i;
            permVisited[i] = true;

            perm(cnt + 1);

            permVisited[i] = false;
        }
    }

    private static void simulation(int height) { // 각 판을 90, 180, 270, 360도 돌려보며 그 때의 도착지 까지의 최소 거리를 확인(4^5번 연산)
        if (height == SIZE) { // 모든 판을 다 돌린 경우
            minDis = Integer.min(minDis, findMinDis());
            return;
        }

        for (int i = 0; i < SIZE-1; i++) {
            rotate(height);
            simulation(height + 1);
        }
    }

    private static void rotate(int z) { // 시계 방향으로 회전
        int[][] rotate = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                rotate[col][SIZE-1-row] = grid[z][row][col];
            }
        }

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[z][row][col] = rotate[row][col];
            }
        }
    }

    private static int findMinDis() { // BFS로 도착지 까지의 최소 거리 계산
        if (grid[0][0][0] != 1 || grid[SIZE-1][SIZE-1][SIZE-1] != 1) { // 출발지나 도착지가 이동 불가능한 지역일 경우
            return Integer.MAX_VALUE;
        }

        Queue<Pair> que = new ArrayDeque<>();
        que.offer(new Pair(0, 0, 0, 0));

        visited = new boolean[SIZE][SIZE][SIZE];
        visited[0][0][0] = true;

        while (!que.isEmpty()) {
            Pair cur = que.poll();
            int x = cur.x;
            int y = cur.y;
            int z = cur.z;
            int dis = cur.dis;

            if (dis > minDis) { // 앞서 구한 최소 이동 거리보다 더 많이 이동하는 경우 탐색 중단
                return minDis;
            }

            if (x == SIZE-1 && y == SIZE-1 && z == SIZE-1) { // 종료 지점 도달 시 이동거리 return
                return dis;
            }

            for (int d = 0; d < 6; d++) { // 상하좌우 4방 + 위층 + 아래층 탐색
                int nx = x + dx[d];
                int ny = y + dy[d];
                int nz = z + dz[d];

                if (!canGo(nx, ny, nz)) {
                    continue;
                }

                visited[nz][nx][ny] = true;
                que.offer(new Pair(nx, ny, nz, dis + 1));
            }
        }

        return Integer.MAX_VALUE; // 도착지에 도달하지 못하는 경우
    }

    private static boolean canGo(int x, int y, int z) { // BFS 수행 시 이동할 수 있는 칸인지 확인
        if (!inRange(x, y, z)) { // 격자 범위 밖으로 벗어나는 경우
            return false;
        }

        if (grid[z][x][y] == 0) { // 길이 아닌 경우
            return false;
        }

        if (visited[z][x][y]) { // 이미 방문한 경우
            return false;
        }

        return true;
    }

    private static boolean inRange(int x, int y, int z) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE && z >= 0 && z < SIZE;
    }

}
