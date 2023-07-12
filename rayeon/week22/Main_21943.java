package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 21943: 연산 최대로
public class Main_21943 {
    static int N;
    static int P, Q;
    static int[] nums;
    static int[] subset;
    static int answer;

    public static void dfs(int index) {
        if (index == N) {
            int result = 1;

            for (int i = 0; i < Q + 1; i++) {
                if (subset[i] == 0) {
                    return;
                }

                result *= subset[i];
            }

            answer = Math.max(result, answer);
            return;
        }

        for (int i = 0; i < Q + 1; i++) {
            subset[i] += nums[index];
            dfs(index + 1);
            subset[i] -= nums[index];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        subset = new int[Q + 1];

        answer = 0;
        dfs(0);

        System.out.println(answer);
        br.close();
    }

}