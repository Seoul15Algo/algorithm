package baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14722 우유 도시
public class Main_14722 {

    static int N;
    static int[][] milkCity;
    static int[][] drank;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        milkCity = new int[N][N];
        drank = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                milkCity[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (milkCity[0][0] == 0) {
            drank[0][0] = 1;
        } else {
            milkCity[0][0] = -1;
        }

        // 테두리 먼저 채우기
        for (int i = 1; i < N; i++) {
            if (milkCity[i][0] == (milkCity[i - 1][0] + 1) % 3) {
                drank[i][0] = drank[i - 1][0] + 1;
            } else {
                drank[i][0] = drank[i - 1][0];
                milkCity[i][0] = milkCity[i - 1][0];
            }
            if (milkCity[0][i] == (milkCity[0][i - 1] + 1) % 3) {
                drank[0][i] = drank[0][i - 1] + 1;
            } else {
                drank[0][i] = drank[0][i - 1];
                milkCity[0][i] = milkCity[0][i - 1];
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                // 위, 왼쪽의 우유에 이어 현재 위치의 우유를 마실 수 있다고 가정 (마신 우유 개수와 직전에 마신 우유의 종류 일단 갱신)
                int up = drank[i - 1][j] + 1;
                int left = drank[i][j - 1] + 1;
                int prevUp = (milkCity[i - 1][j] + 1) % 3;
                int prevLeft = (milkCity[i][j - 1] + 1) % 3;

                // 위쪽의 우유에 이어 마실 수 없는 경우
                if (milkCity[i][j] != prevUp) {
                    // 다시 원래 값으로
                    up = drank[i - 1][j];
                    prevUp = milkCity[i - 1][j];
                }
                // 왼쪽 우유에 이어 마실 수 없는 경우
                if (milkCity[i][j] != prevLeft) {
                    // 다시 원래 값으로
                    left = drank[i][j - 1];
                    prevLeft = milkCity[i][j - 1];
                }

                // 위, 왼쪽 방향 중 어느 쪽에서 오는 것이 효율적인지
                if (up < left) {
                    drank[i][j] = left;
                    milkCity[i][j] = prevLeft;
                } else {
                    drank[i][j] = up;
                    milkCity[i][j] = prevUp;
                }
            }
        }
        System.out.println(drank[N - 1][N - 1]);
    }
}
