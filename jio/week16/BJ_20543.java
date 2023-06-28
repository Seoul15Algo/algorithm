package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20543 {
    static int N, M;
    static long[][] grid;
    static long[][] bomb;
    static long[][] imos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new long[N+1][N+1];
        bomb = new long[N+1][N+1]; // 폭탄이 위치하는 배열
        imos = new long[N + M / 2 + 1][N + M / 2 + 1]; // 누적합을 빠르게 구하기 위해 사용하는 배열

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                grid[i][j] = Long.parseLong(st.nextToken());
            }
        }

        if (N == 1) {  // N이 1인 경우 별도로 처리
            System.out.println(Math.abs(grid[1][1]));
            return;
        }

        findBombs(); // 폭탄 찾기

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(bomb[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void findBombs() {
        for (int i = 1; i <= N - M + 1; i++) {
            for (int j = 1; j <= N - M + 1; j++) {
                imos[i][j] += imos[i][j - 1]; // 열을 증가시키면서 imos 배열 갱신(누적 합 갱신)
                grid[i][j] += imos[i][j]; // 처리한 폭탄 만큼 원본 격자를 복구
                long bombCnt = Math.abs(grid[i][j] + imos[i - 1][j]); // 폭탄의 개수

                int bombX = i + M / 2;     // 폭탄의 X 좌표
                int bombY = j + M / 2;     // 폭탄의 Y 좌표

                bomb[bombX][bombY] = bombCnt; // 정답 배열에 폭탄 표시

                // 네 모서리를 찍어 위에서 표시한 폭탄에 영향을 받는 구간을 지정 한다.
                imos[i][j] += bombCnt;
                imos[i + M][j + M] += bombCnt;
                imos[i + M][j] -= bombCnt;
                imos[i][j + M] -= bombCnt;
            }

            for (int j = 1; j <= N - M + 1; j++) { // 위에 행에서 처리한 누적합 반영, 이 부분은 열에 대한 누적합 갱신이 끝난 뒤 수행되어야만 한다!!
                imos[i][j] += imos[i - 1][j];
            }
        }
    }
}
