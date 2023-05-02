package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9527 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] ab = br.readLine().split(" ");
        long a = Long.parseLong(ab[0]);
        long b = Long.parseLong(ab[1]);
        String aBinary = Long.toBinaryString(a - 1);
        String bBinary = Long.toBinaryString(b);
        int aLength = aBinary.length();
        int bLength = bBinary.length();
        long[] dp = new long[55];
        dp[1] = 1;

        for (int i = 2; i < 55; i++) {
            dp[i] = (2 * dp[i - 1]) + (long) Math.pow(2, i - 1);
        }

        long aTotal = 0;
        long aOneCount = 0;
        for (int i = 0; i < aLength; i++) {
            if (aBinary.charAt(i) == '1') {
                aTotal += dp[aLength - i - 1] + (long) (Math.pow(2, aLength - 1 - i) * aOneCount) + 1;
                aOneCount++;
            }
        } // 1101 -> 12 + (2^3 * 0) + 1 -> 4 + (2^2 * 1) + 1

        long bTotal = 0;
        long bOneCount = 0;
        for (int i = 0; i < bLength; i++) {
            if (bBinary.charAt(i) == '1') {
                bTotal += dp[bLength - i - 1] + (long) (Math.pow(2, bLength - 1 - i) * bOneCount) + 1;
                bOneCount++;
            }
        }

        System.out.println(bTotal - aTotal);
    }
}