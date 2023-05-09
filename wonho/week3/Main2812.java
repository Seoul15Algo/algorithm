package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main2812 {
    private static int n;
    private static int k;
    private static String[] numbers;
    private static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");
        n = Integer.parseInt(nk[0]);
        k = Integer.parseInt(nk[1]);

        numbers = br.readLine().split("");
        stack = new Stack<>();

        int subCount = 0;
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(numbers[i]);

            while (!stack.isEmpty() && stack.peek() < number && subCount < k) {
                stack.pop();
                subCount++;
            }

            if (subCount >= k) {
                pushRemains(i);
                break;
            }

            stack.push(number);
        }

        while (subCount < k) {
            stack.pop();
            subCount++;
        }

        stack.forEach(System.out::print);
    }

    private static void pushRemains(int start) {
        for (int j = start; j < n; j++) {
            stack.push(Integer.parseInt(numbers[j]));
        }
    }
}
