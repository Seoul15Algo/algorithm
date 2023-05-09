package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_1943 {
    static int N, halfValue, maxSum;
    static Map<Integer, Integer> coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            N = Integer.parseInt(br.readLine());
            coins = new HashMap<>(); // 동전의 정보를 저장

            maxSum = 0; // 동전의 총합
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int coinType = Integer.parseInt(st.nextToken());
                int coinCnt = Integer.parseInt(st.nextToken());

                maxSum += coinType * coinCnt;
                coins.put(coinType, coinCnt);
            }

            if (maxSum % 2 != 0) { // 반으로 떨어지지 않는 경우
                sb.append(0).append("\n");
                continue;
            }

            halfValue = maxSum / 2; // 동전 총합의 반절로 우리가 찾아야 할 target 값이다.

            if (isPossible()) {
                sb.append(1).append("\n");
                continue;
            }
            sb.append(0).append("\n");
        }
        System.out.println(sb);
    }

    // halfValue를 주어진 동전들로 만들 수 있다면 돈을 반으로 나눌 수 있다는 걸 의미한다.
    private static boolean isPossible(){

        // dp[i] : 주어진 동전들로 합이 i인 경우를 만족하는지 못하는지 여부를 저장
        int[] dp = new int[halfValue + 1]; // halfValue + 1 크기만큼 dp 배열 생성
        dp[0] = 1;

        for (Map.Entry<Integer, Integer> entry : coins.entrySet()) { // 동전들을 순회
            int coinType = entry.getKey();   // 동전 타입
            int coinCnt = entry.getValue();  // 동전 개수

            if (coinType > halfValue) { // 동전의 타입이 타겟값 보다 큰 경우 확인해볼 필요가 없다.
                continue;
            }

            for (int j = halfValue; j >= 0; j--) { // dp 배열을 순회

                if(j + coinType > halfValue){ // j + coinType이 타겟값 보다 큰 경우 continue(index 범위 벗어나는 걸 방지)
                    continue;
                }

                if (dp[j] == 1) { // 만들 수 있는 경우일 때

                    for (int i = 1; i <= coinCnt; i++) { // 동전의 개수 만큼 반복하며 만들 수 있는 동전의 합을 찾아준다.

                        if (j + coinType * i > halfValue) { // 만든 동전의 총합이 타겟값을 넘는 경우 continue(index 범위 벗어나는 걸 방지)
                            break;
                        }

                        dp[j + coinType * i] = 1; // 새로 만든 동전의 합
                    }

                    if(dp[halfValue] == 1){ // 새로 만든 동전의 합이 타겟값과 일치할 경우 탐색 종료
                        return true;
                    }
                }
            }
        }

        return false;
    }
}