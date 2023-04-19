package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11066 {

    private static BufferedReader br;
    private static StringBuilder sb;
    private static int[] files;
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
        int[] dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            files[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(search(0, n - 1));
        System.out.println(Arrays.toString(dp));
    }

    public static long search(int left, int right) {
        if (left > right || left >= n) {
            return Integer.MAX_VALUE;
        }

        if (left == right) {
            System.out.println("left = " + left);
            System.out.println("files = " + files[left]);
            return files[left];
        }

        System.out.println("left, right = " + left + ", " + right);
        long min = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            min = Math.min(min, search(left, i) + search(i + 1, right));
        }

        return min;
    }
}