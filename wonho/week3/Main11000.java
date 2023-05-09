package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main11000 {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        List<int[]> classes = new ArrayList<>();
        Queue<Integer> pq = new PriorityQueue<>(List.of(0));

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int t = Integer.parseInt(input[1]);
            classes.add(new int[]{s, t});
        }

        classes.sort((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        for (int i = 0; i < n; i++) {
            int prevEndTime = pq.poll();
            int startTime = classes.get(i)[0];
            int endTime = classes.get(i)[1];

            if (startTime < prevEndTime) {
                pq.offer(prevEndTime);
            }
            pq.offer(endTime);
        }

        System.out.println(pq.size());
    }
}
