package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11066 {

    static int K;
    static int[][] dp;
    static int[] totalSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            K = Integer.parseInt(br.readLine());
            dp = new int[K + 1][K + 1];
            totalSize = new int[K + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= K; i++) {
                int file = Integer.parseInt(st.nextToken());
                totalSize[i] = totalSize[i - 1] + file; // 파일들의 누적 합을 구한다
            }

            mergeFile();

            result.append(dp[1][K]).append("\n");
        }
        System.out.println(result);
    }

    // bottom-up
    static void mergeFile(){
        // dp[i][j]: i번째 파일부터 j번째 파일까지를 병합했을 때의 비용 최솟값
        // ex) k가 1: dp[1][2] -> dp[2][3] -> ...와 같이 첫 파일 idx와 마지막 파일 idx 사이의 차이를 의미
        // ex) k가 3: dp[1][4] -> dp[2][5] -> ...
        for (int k = 1; k < K; k++) {
            for (int i = 1; i + k <= K; i++) {
                dp[i][i + k] = Integer.MAX_VALUE;   // min값을 구할 것이므로 INF로 초기화
                /*
                    이 부분은 최솟값을 갱신하기 위한 구간
                    ex) 40 30 30 50
                        dp[1][4]를 만드는 방법은 dp[1][1] + dp[2][4], dp[1][2] + dp[3][4] 등 다양함 -> j를 통해 모든 경우의 수를 살펴봄
                        기존의 dp[1][4] 값이 320이라면 (dp[1][1] + dp[2][4] + (2번째 파일부터 4번째 파일까지를 합치는 데 드는 비용))
                        dp[1][2] + dp[3][4] + (1번째 파일부터 2번째 파일까지를 합치는 데 드는 비용) + (3번째 파일부터 4번째 파일까지를 합치는 데 드는 비용)은 300이므로
                        기존의 dp[1][4]보다 작음 -> 그러므로 갱신
                 */
                for (int j = i; j < i + k; j++) {
                    dp[i][i + k] = Math.min(dp[i][i + k], dp[i][j] + dp[j + 1][i + k] + (totalSize[i + k] - totalSize[i - 1]));
                }
            }
        }
    }
}