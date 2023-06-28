import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1799 {
    static int N, maxOddCnt, maxEvenCnt;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {1, 1};
    static int[] dy = {-1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        if (N % 2 != 0) {
            simulationOdd(0, 0);
            ans += maxOddCnt;

            maxOddCnt = 0;

            simulationOdd(1, 0);
            ans += maxOddCnt;
        }

        if (N % 2 == 0) {
            simulationEven(0, 0);
            ans += maxEvenCnt;

            maxEvenCnt = 0;

            simulationEven(1, 0);
            ans += maxEvenCnt;
        }

        System.out.println(ans);
    }

    private static void simulationOdd(int index, int cnt) {
        if (index >= N * N) {
            maxOddCnt = Integer.max(maxOddCnt, cnt);
            return;
        }

        int row = index / N;
        int col = index % N;

        if (grid[row][col] == 1) { // 비숍을 놓는 경우
            paintGrid(row, col);
            simulationOdd(index + 2, cnt + 1);
            clearGrid(row, col);
        }

        simulationOdd(index + 2, cnt); // 비숍을 놓지 않는 경우
    }

    private static void simulationEven(int index, int cnt) {
        if (index >= N * N) {
            maxEvenCnt = Integer.max(maxEvenCnt, cnt);
            return;
        }

        int row = index / N;
        int col = index % N;
        int move = 2; // 다음 행으로 바뀌는 경우가 아닐 경우 2칸 이동

        if (col == N - 2) { // 검정 칸에서 다음 행으로 바뀌는 경우
            move = 3;
        }

        if (col == N - 1) { // 흰색 칸에서 다음 행으로 바뀌는 경우
            move = 1;
        }

        if (grid[row][col] == 1) { // 비숍을 놓는 경우
            paintGrid(row, col);
            simulationEven(index + move, cnt + 1);
            clearGrid(row, col);
        }

        simulationEven(index + move, cnt); // 비숍을 놓지 않는 경우
    }



    private static void clearGrid(int row, int col) {
        for (int d = 0; d < 2; d++) {
            clearDiagonal(row, col, d);
        }
    }

    private static void clearDiagonal(int row, int col, int dir) {
        int x = row;
        int y = col;
        while (inRange(x, y)) {
            grid[x][y] += 1;
            x += dx[dir];
            y += dy[dir];
        }
    }

    private static void paintGrid(int row, int col) {
        for (int d = 0; d < 2; d++) {
            paintDiagonal(row, col, d);
        }
    }

    private static void paintDiagonal(int row, int col, int dir) {
        int x = row;
        int y = col;
        while (inRange(x, y)) {
            grid[x][y] -= 1;
            x += dx[dir];
            y += dy[dir];
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
