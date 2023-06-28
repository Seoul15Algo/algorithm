import java.util.*;
import java.io.*;

public class Main_1943 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 0; t < 3; t++){
            int N = Integer.parseInt(br.readLine());
            boolean[] dp = new boolean[100001];
            dp[0] = true;
            int sum = 0;

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int money = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());

                // 지금까지 더한 최댓값 까지만 확인
                // 50000이 넘어가는 경우는 확인할 필요 X
                int range = Math.min(sum, 50000);
                for(int j = range; j >= 0 ; j--){
                    if(dp[j]){
                        // 현재 동전으로 가능한 경우를 모두 저장
                        for(int k = 1; k <= count; k++){
                            dp[j + money * k] = true;
                        }
                    }
                }
                sum += money * count;
            }

            int answer = 0;
            if(sum % 2 == 0 && dp[sum/2]){
                answer = 1;
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}