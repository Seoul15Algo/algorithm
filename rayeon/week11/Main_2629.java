package Seoul15Algo.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BJ 2629 양팔저울
public class Main_2629 {
    static final int METAL_MAX_COUNT = 30;
    static final int METAL_MAX_WEIGHT = 500;
    static boolean[] dp;

    static int metal_count;
    static int[] metals;
    static int marbel_count;
    static int[] marbles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        metal_count = Integer.parseInt(br.readLine());
        metals = new int[metal_count];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < metal_count; i++) {
            metals[i] = Integer.parseInt(st.nextToken());
        }

        marbel_count = Integer.parseInt(br.readLine());
        marbles = new int[marbel_count];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < marbel_count; i++) {
            marbles[i] = Integer.parseInt(st.nextToken());
        }

        int dpSize = METAL_MAX_COUNT * METAL_MAX_WEIGHT * 2 + 1;
        int dpZero = METAL_MAX_COUNT * METAL_MAX_WEIGHT;

        dp = new boolean[dpSize];
        dp[METAL_MAX_COUNT * METAL_MAX_WEIGHT] = true;
        for (int metal : metals) {
            for (int i = dpSize - 1 - metal; i >= 0; i--) {
                if (!dp[i]) {
                    continue;
                }

                dp[i + metal] = true;
            }

            for (int i = metal; i < dpSize; i++) {
                if (!dp[i]) {
                    continue;
                }

                dp[i - metal] = true;
            }
        }

        for (int marble : marbles) {
            if (dpZero + marble >= dpSize || !dp[dpZero + marble]) {
                sb.append("N");
            } else {
                sb.append("Y");
            }

            sb.append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}