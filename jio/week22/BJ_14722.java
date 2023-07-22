package week22;

/*
답을 봤는데 이해가 잘 안돼요....
 */
import java.io.*;
import java.util.StringTokenizer;

public class BJ_14722 {
    static int N, maxCnt;
    static int[][] grid;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        grid = new int[N + 1][N + 1];

        // dp 배열, dp[i][j][k] : k(딸기, 초코, 바나나)번째 맛의 우유를 마지막으로 i, j까지 먹은 최대 우유 수
        dp = new int[N + 1][N + 1][3];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++)
                grid[i][j] = Integer.parseInt(st.nextToken());
        }

        findMaxMilkCnt();

        // 최댓값 탐색
        maxCnt = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            maxCnt = Integer.max(maxCnt, dp[N][N][i]);
        }
        System.out.println(maxCnt);
    }

    static void findMaxMilkCnt() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int milkType = grid[i][j];

                if (milkType == 0) { // 딸기 우유
                    dp[i][j][0] = Math.max(dp[i][j-1][2] + 1, dp[i-1][j][2] + 1);
                }
                else {
                    dp[i][j][0] = Math.max(dp[i][j-1][0], dp[i-1][j][0]); // 딸기 우유를 먹지 않는 경우, 이전 칸 중 최대값을 그대로 지님
                }

                if (milkType == 1 && dp[i][j][0] > dp[i][j][1]) { // 초코 우유 && 딸기 우유를 먹고 온 경우
                    dp[i][j][1] = Math.max(dp[i][j-1][0] + 1, dp[i-1][j][0] + 1); // 딸기 우유를 먹고 온 경우 + 1
                }
                else {
                    dp[i][j][1] = Math.max(dp[i][j-1][1], dp[i-1][j][1]); // 초코 우유를 먹지 않는 경우
                }

                if (milkType == 2 && dp[i][j][1] > dp[i][j][2]) {	// 바나나 우유 && 초코 우유를 먹고 온 경우
                    dp[i][j][2] = Math.max(dp[i][j-1][1] + 1, dp[i-1][j][1] + 1); // 초코 우유를 먹고 온 경우 + 1
                }
                else {
                    dp[i][j][2] = Math.max(dp[i][j-1][2], dp[i-1][j][2]); // 바나나 우유를 먹지 않는 경우
                }
            }
        }
    }
}
