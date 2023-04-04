package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1208 {
    static int N, S;
    static int[] nums;
    static long[] dp;
    static int offset = 100000; //음수 값을 처리하기 위해 offset 설정
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dp = new long[2*N*offset + 1];
        for (int i = 0; i < N; i++) { //수열 순회
            if(nums[i] >= 0){ //값이 양수일 때는 dp 배열의 오른 쪽부터 왼쪽으로 순회
                for (int j = 2 * N * offset; j >= 0; j--) {
                    if (dp[j] == 0) { //가능하지 않은 경우
                        continue;
                    }
                    dp[j + nums[i]] += dp[j];
                }
            }else{ //값이 음수일 때는 dp 배열의 왼쪽부터 오른쪽으로 순회
                for (int j = 0; j < 2 * N * offset + 1; j++) {
                    if (dp[j] == 0) {
                        continue;
                    }
                    dp[j + nums[i]] += dp[j];
                }
            }
            dp[nums[i] + N*offset] += 1; //offset을 고려한 자기 자신의 카운트 1 증가
        }
        System.out.println(dp[S + N * offset]);
    }
}
