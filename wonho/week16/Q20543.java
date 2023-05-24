package week16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q20543 {
    private static int n;
    private static int m;
    private static long[][] map;
    private static long[][] bomb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new long[n][n];
        bomb = new long[n][n];

        int mid = m / 2;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = -Long.parseLong(st.nextToken()); // 양수로 저장
            }
        }

        for (int i = mid; i < n - mid; i++) {
            for (int j = mid; j < n - mid; j++) {
                bomb[i][j] = map[i - mid][j - mid];

                if (i - mid - 1 >= 0) {
                    bomb[i][j] -= map[i - mid - 1][j - mid];
                }

                if (j - mid - 1 >= 0) {
                    bomb[i][j] -= map[i - mid][j - mid - 1];
                }

                if (i - mid - 1 >= 0 && j - mid - 1 >= 0) {
                    bomb[i][j] += map[i - mid - 1][j - mid - 1];
                }

                if (i - m >= 0) {
                    bomb[i][j] += bomb[i - m][j];
                }
                if (j - m >= 0) {
                    bomb[i][j] += bomb[i][j - m];
                }
                if (i - m >= 0 && j - m >= 0) {
                    bomb[i][j] -= bomb[i - m][j - m];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(bomb[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

}