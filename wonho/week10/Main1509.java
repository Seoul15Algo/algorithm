package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main1509 {
    private static String s;
    private static int[] dp;
    private static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        size = s.length();
        dp = new int[size];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < size; i++) {
            checkOdd(i);
            if (i < size - 1) {
                checkEven(i);
            }
        }

        System.out.println(dp[size - 1]);
    }

    public static void checkOdd(int index) {
        int count = 1;
        int left = index;
        int right = index;
        while (right < size) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }

            if (left == 0) {
                dp[right] = 1;
                break;
            }
            dp[right] = Math.min(dp[right], dp[left - 1] + 1);
            left--;
            right++;
            count++;
        }
    }

    public static void checkEven(int index) {
        int count = 1;
        int left = index;
        int right = index + 1;
        while (right < size) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }

            if (left == 0) {
                dp[right] = 1;
                break;
            }

            dp[right] = Math.min(dp[right], dp[left - 1] + 1);
            left--;
            right++;
            count++;
        }
    }
}
