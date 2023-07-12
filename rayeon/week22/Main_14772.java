package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 14772: 우유 도시
public class Main_14772 {
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[N + 1][N + 1][3];
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < N + 1; i++) {
                dp[i][0][k] = -1;
                dp[0][i][k] = -1;
            }
        }

        dp[0][1][2] = 0;
        dp[1][0][2] = 0;

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i][j - 1][k]);
                }

                if (dp[i][j][(map[i - 1][j - 1] + 2) % 3] >= 0) {
                    dp[i][j][map[i - 1][j - 1]] = dp[i][j][(map[i - 1][j - 1] + 2) % 3] + 1;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 3; i++) {
            answer = Math.max(answer, dp[N][N][i]);
        }

        System.out.println(answer);
        br.close();
    }
}