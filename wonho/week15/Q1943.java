package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1943 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 3; i++) {
            System.out.println(distribute() ? 1 : 0);
        }
    }

    private static boolean distribute() throws IOException {
        int[] coins = new int[100001];

        int n = Integer.parseInt(br.readLine());
        int total = 0;
        for (int i = 1; i <= n; i++) {
            String[] input = br.readLine().split(" ");
            int coin = Integer.parseInt(input[0]);
            int count = Integer.parseInt(input[1]);
            for (int j = 0; j <= count; j++) {
                for (int k = total; k >= 0; k--) {
                    if (k + (coin * j) > 100000) {
                        break;
                    }

                    if (coins[k] > 0) {
                        coins[k + (coin * j)] += 1;
                    }
                }
            }
            for (int j = 0; j <= count; j++) {
                coins[coin * j] += 1;
            }
            total += coin * count;
        }

        if (total % 2 != 0) {
            return false;
        }

        return coins[total / 2] != 0;
    }
}