package algo230412.soyun.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2629 {

    static final int MAX = 15000;
    static int N, M;
    static int[] weights;
    // N개의 추를 조합하여 만들 수 있는 값일 경우 -> true
    static boolean dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        weights = new int[N + 1];
        dp = new boolean[31][MAX + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        // N개의 추로 만들 수 있는 조합들을 탐색한다.
        find(0, 0);

        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int marble = Integer.parseInt(st.nextToken());
            /*
                문제에서 제시한 추의 무게 최댓값은 500g, 추의 개수는 30개 이하
                그러므로 구슬의 무게가 15000을 넘는다면 무조건 false!
             */
            if (marble > 15000) {
                sb.append("N ");
                continue;
            }
            sb.append(dp[N][marble] ? "Y " : "N ");
        }
        System.out.println(sb);
    }

    // idx: 조합을 만드는 데에 사용한 추의 개수,
    // weight: 그 때의 무게
    public static void find(int idx, int weight) {
        // 이미 생성된 조합이라면
        if (dp[idx][weight]){
            return;
        }
        // 아니라면 조합이 생성되었음을 표시
        // ex) dp[2][7]: 2개의 추를 사용하는 경우, 무게 7인 조합이 존재한다.
        dp[idx][weight] = true;

        // 추의 최대 개수를 넘어간다면
        if (idx == N) {
            return;
        }

        // 모든 경우의 수 탐색
        // 해당 추를 사용하지 않는 경우
        find(idx + 1, weight);
        // 해당 추를 더하는 경우 (추를 구슬이 없는 저울에 올리는 경우)
        find(idx + 1, weight + weights[idx + 1]);
        // 해당 추를 빼는 경우 (추를 구슬이 있는 저울에 올리는 경우);
        find(idx + 1, Math.abs(weight - weights[idx + 1]));
    }
}