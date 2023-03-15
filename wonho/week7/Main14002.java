package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        int[] answer = new int[n + 1];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        int max = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (numbers[i] > numbers[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        int count = max;
        int idx = n - 1;
        while (idx >= 0 && count > 0) {
            if (dp[idx] == count) {
                answer[count--] = numbers[idx];
            }
            idx--;
        }

        for (int i = 1; i <= max; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(max);
        System.out.println(sb);
    }
}
