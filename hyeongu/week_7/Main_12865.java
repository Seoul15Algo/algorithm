import java.io.*;
import java.util.*;

public class Main_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N + 1][2];
        // 가방에 담을 수 있는 무게 K까지 모든 경우의수를 dp로 계산
        // 1차원 dp로 구현 하는 것이 더 효율적
        int[][] dp = new int[N + 1][K + 1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        // 무게를 기준으로 오름차순 해야한다고 생각해서 했는데 쓸데없는 짓이었습니다.
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));

        for(int i = 1; i <= N; i++){
            int weight = arr[i][0];
            // 배열이 오름차순이기 때문에 현재 무게가 K보다 클 경우 break
            if(weight > K){
                N = i - 1;
                break;
            }

            // 현재 무게보다 적은 결과에는 영향을 주지 못함
            for(int j = 1; j < weight; j++){
                dp[i][j] = dp[i - 1][j];
            }

            // 현재 물건을 넣는 경우와 넣지 않는 경우의 최댓값을 저장
            for(int j = weight; j <= K; j++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight] + arr[i][1]);
            }
        }
        System.out.println(dp[N][K]);
    }
}