package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11066 {

    private static BufferedReader br;
    private static StringBuilder sb;
    private static int[] files;
    private static long[] fileSum;
    private static long[][] dp;
    private static int n;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            testcase();
        }

        System.out.print(sb);
    }

    public static void testcase() throws IOException {
        n = Integer.parseInt(br.readLine());
        files = new int[n];
        fileSum = new long[n];
        dp = new long[n][n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            files[i] = Integer.parseInt(st.nextToken());
        }

        fileSum[0] = files[0];
        for (int i = 1; i < n; i++) {
            fileSum[i] = files[i] + fileSum[i - 1];
        }
        for (int i = 0; i < n; i++) {
            dp[i][i] = files[i];
        }

        sb.append(search(0, n - 1)).append("\n");
    }
    
    public static long search(int left, int right) {
        if (dp[left][right] != 0) {
            return dp[left][right];
        }

        if (right - left == 1) {
            return files[left] + files[right];
        }

        if (left == right) {
            return files[left];
        }

        long min = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            long leftTotal = search(left, i);
            long rightTotal = search(i + 1, right);

            long leftSum = fileSum[i] - (left == 0 ? 0 : fileSum[left - 1]);
            long rightSum = fileSum[right] - fileSum[i];

            if (i == left && i + 1 == right) {
                return leftTotal + rightTotal;
            }

            if (i == left) {
                min = Math.min(min, leftSum + rightSum + rightTotal);
                continue;
            }

            if (i + 1 == right) {
                min = Math.min(min, leftSum + rightSum + leftTotal);
                continue;
            }

            min = Math.min(min, leftSum + rightSum + leftTotal + rightTotal);
        }

        return dp[left][right] = min;
    }
}