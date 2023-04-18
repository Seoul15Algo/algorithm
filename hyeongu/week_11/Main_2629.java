import java.util.*;
import java.io.*;
public class Main_2629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int max = 0;
        // 추 1개당 무게의 최댓값이 500이기 때문에
        // N개의 무게의 합의 최댓값은 500 * N
        boolean[] dp = new boolean[500 * N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());
            max = Math.max(max, now);

            // 지금까지 저장한 갯수 : i
            // 지금까지 저장한 추의 최댓값 : max
            // 따라서 현재까지 가능한 추의 무게의 최댓값 : i * max
            for(int j = i * max; j > 0; j--) {
                if(dp[j]) {
                    dp[j + now] = true;
                }
            }
            dp[now] = true;
        }

        int T = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < T; i++) {
            int now = Integer.parseInt(st.nextToken());
            boolean flag = false;
            // 구슬이 추의 최댓값 보다 큰 경우
            if(now > max * N) {
                sb.append("N ");
                continue;
            }

            // 현재 무게만큼의 추가 불가능 할 경우
            if(!dp[now]) {
                for(int j = 1; j <= max * N - now; j++) {
                    // 구슬과 추를 함께 올려 균형을 맞출 수 있는 경우
                    if(dp[j] && dp[j + now]) {
                        flag = true;
                        break;
                    }
                }
            }

            // 위에서 가능한 케이스를 찾은 경우와 현재 무게만큼의 추가 가능한 경우
            if(flag || dp[now]) {
                sb.append("Y ");
                continue;
            }
            sb.append("N ");
        }

        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}