package wonho.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main1655 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Queue<Integer> left = new PriorityQueue<>(Comparator.comparingInt(c -> -c));
        Queue<Integer> right = new PriorityQueue<>(Comparator.comparingInt(c -> c));

        int n = Integer.parseInt(br.readLine());

        int one = Integer.parseInt(br.readLine());
        left.offer(one);
        sb.append(one).append("\n");

        for (int i = 1; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            int peek = left.peek();

            if (num <= peek) {
                left.offer(num);
            } else {
                right.offer(num);
            }

            while (left.size() - right.size() > 1) {
                right.offer(left.poll());
            }

            while (right.size() - left.size() > 0) {
                left.offer(right.poll());
            }

            sb.append(Math.min(left.peek(), right.peek())).append("\n");
        }

        System.out.print(sb);
    }
}