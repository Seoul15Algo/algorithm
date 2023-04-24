import java.util.*;
import java.io.*;
public class Main_9527 {
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        // 주어진 수를 이진수로 변환 했을 때 자릿수 + 1 만큼 dp배열 생성
        // dp배열은 해당 자리의 수까지의 1의 개수의 합
        dp = new long[(int)(Math.log(B) / Math.log(2)) + 2];
        for(int i = 1; i < dp.length; i++) {
            // dp[i - 1] : 맨앞자리가 0일 때
            // dp[i - 1] : 맨앞자리가 1일 때 맨 앞자리를 제외한 1의 개수
            // (1L << (i - 1)) : 맨 앞자리의 1의 개수
            dp[i] = dp[i - 1] * 2 + (1L << (i - 1));
        }

        System.out.println(countOne(B) - countOne(A - 1));
    }

    static long countOne(long n) {
        long result = 1L;
        long cnt = 0L;
        String binary = Long.toString(n, 2);
        int len = binary.length() - 1;

        // ex)  100100까지의 1의 개수의 합
        //  ->  100000까지 + 100100까지
        //  ->  100000에서 100100까지의 1의개수의 합은 0부터 100의 합 + 0부터 100까지 수의 개수 * 앞에 붙은 1의 개수
        for(int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i) == '0') {
                continue;
            }
            result += dp[len - i] + 1;
            result += cnt * (1L << len - i);
            cnt++;
        }
        return result;
    }
}