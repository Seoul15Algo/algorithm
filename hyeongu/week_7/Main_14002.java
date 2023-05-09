import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 1;
        int index = 0;
        dp[0] = 1;
        for(int i = 1; i < N; i++){
            // 1로 초기화 하는 작업과 동일
            dp[i]++;

            // 현재 위치보다 앞에 있는 값 중에서 자신보다 작은것의 수 + 1
            // 그 중에서 max를 저장
            for(int j = i - 1; j >= 0; j--){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            // max 값 교체
            if(max < dp[i]){
                max = dp[i];
                index = i;
            }
        }

        int[] answer = new int[max];

        // max값을 줄여가면서 가장 마지막으로 나온 max 값을 찾음
        // 해당 값이 가장 마지막으로 나온 경우는 무조건 정답에 포함될 수 있기 때문에
        for(int i = index; i >= 0; i--){
            if(dp[i] == max){
                max--;
                answer[max] = arr[i];
            }
            if(max < 0) break;
        }

        sb.append(answer.length).append("\n");
        for(int i = 0; i < answer.length; i++){
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
