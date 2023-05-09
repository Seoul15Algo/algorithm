package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11066 {
    static int T, K;
    static int NOTDECIDED = Integer.MAX_VALUE / 1000;
    static int[] nums, calSum;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            K = Integer.parseInt(br.readLine());
            nums = new int[K];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                nums[j] = Integer.parseInt(st.nextToken());
            }

            calSum = new int[K]; //i~j 까지의 합을 미리 계산해둔 배열 생성
            calSum[0] = nums[0];
            for (int i = 1; i < K; i++) {
                calSum[i] = calSum[i - 1] + nums[i];
            }

            dp = new int[K][K];
            fillDPTable();
            sb.append(dp[0][K - 1]).append("\n");
        }
        System.out.println(sb);

    }

    private static void fillDPTable() {
        //1. dp 배열 초기화
        dp = new int[K][K];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                dp[i][j] = NOTDECIDED;
            }
        }
        for (int i = 0; i < K; i++) {
            dp[i][i] = 0;
        }
        for (int i = 0; i < K - 1; i++) {
            dp[i][i+1] = nums[i] + nums[i+1];
        }

        //2. bottom up 방식으로 dp 테이블 채우기
        // dp[i][j] = i~j까지 파일을 합치는 데 드는 최소 비용
        // i~j를 고르는데 사용되는 중간비용: i~j 사이를 두 그룹으로 나누는 위치인 K를 i~j-1까지 돌며 dp[i][k] + dp[k+1][j]최소인 경우
        // i~j까지 파일을 고르는 데 드는 비용 : sum(nums[i]~nums[j])
        // dp[i][j] = min(dp[i][k] + dp[k+1][j]) + sum(nums[i]~nums[j])
        for (int i = K-1; i >= 0; i--) {
            for (int j = i; j < K; j++) {
                if(dp[i][j] != NOTDECIDED){ //초기화(1번) 과정에서 이미 값이 채워진 경우
                    continue;
                }
                int minCost = Integer.MAX_VALUE; //dp 배열에 채워질 값
                for (int k = i; k < j; k++) {    //k: 중간 값의 위치
                    minCost = Math.min(minCost, dp[i][k] + dp[k+1][j]);
                }
                minCost += calSum[j] - calSum[i] + nums[i];
                dp[i][j] = minCost;
            }
        }
    }
}