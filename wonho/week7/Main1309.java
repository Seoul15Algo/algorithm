package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1309 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n + 2];
        dp[1] = 3; // 왼쪽, 오른쪽, 없는 경우 총 3가지
        dp[2] = 7; // 1번째 경우에 더해서, 왼쪽 2가지, 오른쪽 2가지, 없는 경우 3가치 해서 총 7가지

        // 점화식: (a_(n-1) - a_(n-2)) * 2 + a_(n-2) + 3 = 2a_(n-1) + a_(n-2)

        // 전 경우에서 전전 경우를 빼서 * 2
        // -> 전 경우에서 전전 경우를 빼면 전 경우에 들어있는 경우가 남는다. 여기서 현재 칸에 있는 경우와 없는 경우

        // 전전 경우에서 * 3
        // -> 전전 경우와 현재 경우는 연속되도 상관 없기 때문에, 좌, 우, 없는 경우 해서 총 * 3
        for (int i = 3; i <= n; i++) {
            dp[i] = ((dp[i - 1] * 2) + dp[i - 2]) % 9901;
        }
        // 모듈러 합동 of modulo operation -> ((a mod n) + (b mod n)) mod n 과 (a+b) mod n 은
        // 동치이다.
        System.out.println(dp[n] % 9901);
    }
}
