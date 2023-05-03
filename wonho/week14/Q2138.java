package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q2138 {

    private static int n;
    private static char[] bulbs;
    private static char[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        bulbs = br.readLine().toCharArray();
        answer = br.readLine().toCharArray();

        if (n == 2) {
            if (bulbs[0] == answer[0] && bulbs[1] == answer[1]) {
                System.out.println(0);
                return;
            }

            if (bulbs[0] != answer[0] && bulbs[1] != answer[1]) {
                System.out.println(1);
                return;
            }

            System.out.println(-1);
            return;
        }

        int count = Math.min(getCount(bulbs), getCountInReverse(bulbs));

        System.out.println(count == Integer.MAX_VALUE ? -1 : count);
    }

    private static int getCount(char[] init) {
        char[] bulbs = Arrays.copyOf(init, n);

        int count = 0;
        if ((bulbs[1] != answer[1] && bulbs[2] == answer[2]) ||
                (bulbs[1] == answer[1] && bulbs[2] != answer[2])) {
            change(bulbs, 0);
            count++;
        }

        for (int i = 1; i < n; i++) {
            if (bulbs[i - 1] != answer[i - 1]) {
                change(bulbs, i);
                count++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (bulbs[i] != answer[i]) {
                return Integer.MAX_VALUE;
            }
        }
        return count;
    }

    private static int getCountInReverse(char[] init) {
        char[] bulbs = Arrays.copyOf(init, n);

        int count = 0;
        if ((bulbs[n - 1] != answer[n - 1] && bulbs[n - 2] == answer[n - 2]) ||
                (bulbs[n - 1] == answer[n - 1] && bulbs[n - 2] != answer[n - 2])) {
            change(bulbs, n - 1);
            count++;
        }

        for (int i = n - 2; i >= 0; i--) {
            if (bulbs[i + 1] != answer[i + 1]) {
                change(bulbs, i);
                count++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (bulbs[i] != answer[i]) {
                return Integer.MAX_VALUE;
            }
        }
        return count;
    }

    public static void change(char[] bulbs, int index) {
        for (int i = Math.max(index - 1, 0); i <= Math.min(index + 1, n - 1); i++) {
            bulbs[i] = (bulbs[i] == '0' ? '1' : '0');
        }
    }
}