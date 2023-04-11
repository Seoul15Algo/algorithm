package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2629 {
    static int N, M; //N : 추의 개수, M : 구슬개수
    static int[] chuWeights, marbles, dp;
    static int MAX_WEIGHT = 500;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chuWeights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            chuWeights[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        marbles = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            marbles[i] = Integer.parseInt(st.nextToken());
        }
        //1. dp 배열 초기화
        int offset = 30 * MAX_WEIGHT;     //음수를 처리하기 위해 offset 사용
        dp = new int[2*offset + 1];       //dp 배열을 추로 만들 수 있는 최대 합의 크기만큼 선언
        dp[chuWeights[0] + offset] = 1;   //첫 번쨰 추로 만들 수 있는 무게는 미리 초기화
        dp[chuWeights[0]*-1 + offset] = 1;

        //2. idx : 추 무게의 합, dp[idx] : 추로 무게 idx를 만들 수 있는지 여부 저장(1:가능, 0:불가능)
        for (int i = 1; i < N; i++) { //추 순회
            int[] subtractDp = Arrays.copyOf(dp, dp.length); //현재 선택한 추를
            // 추의 무게를 더하는 경우 원본 배열 사용
            for (int j = 2 * offset; j >= 0; j--) {  //연산한 값이 진행 과정에 영향을 주지 않기 위해 오른쪽부터 순회
                if (dp[j] == 0) {
                    continue;
                }
                dp[j + chuWeights[i]] = 1;
            }
            // 추의 무게를 빼는 경우 subtractDP 사용 => 위에서 추의 무게를 더한 값의 영향을 받지 않기 위해서
            for (int j = 0; j <= 2 * offset; j++) { //연산한 값이 진행 과정에 영향을 주지 않기 위해 왼쪽부터 순회
                if (dp[j] == 0) {
                    continue;
                }
                dp[j - chuWeights[i]] = 1;
            }
            dp[chuWeights[i] + offset] = 1;  //이전, 이후에 어떠한 추도 사용하지 않고 현재 보고 있는 추만 사용한 경우
            sync(dp, subtractDp);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if(marbles[i] > N * MAX_WEIGHT){ //구슬의 무게가 모든 추 무게의 합 보다 클 경우
                sb.append("N").append(" ");
                continue;
            }
            if(dp[marbles[i] + offset] == 1){ //주어진 추로 무게를 잴 수 있는 경우
                sb.append("Y").append(" ");
                continue;
            }
            sb.append("N").append(" "); //잴 수 없는 경우
        }
        System.out.println(sb);
    }

    private static void sync(int[] dp, int[] subtractDp) { //추를 더했을 경우만 고려한 배열에 뺏을 경우를 고려한 배열을 동기화
        for (int i = 0; i < dp.length; i++) {
            if(dp[i] == 0 && subtractDp[i] != 0){ //추를 더한 경우에는 가능하지 않지만 뺀 경우에는 가능한 무게가 존재하는 경우
                dp[i] = 1;
            }
        }
    }

}
