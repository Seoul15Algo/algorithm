package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q13334 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Queue<int[]> pq = new PriorityQueue<>((v1, v2) -> {
            if (v1[1] == v2[1]) {
                return v1[0] - v2[0];
            }
            return v1[1] - v2[1];
        });

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            if (a <= b) {
                pq.offer(new int[]{a, b});
                continue;
            }
            pq.offer(new int[]{b, a});
        }
        int size = Integer.parseInt(br.readLine());

        int maxCount = 0;
        // Queue에 이전 값들을 넣고, 이전 값들의 출발점들을 오름차순으로 정렬
        // 만약 현재 출발점이 이전 출발점을 넘어 선 경우라면 뺀다. 
        Queue<int[]> prev = new PriorityQueue<>(Comparator.comparingInt(v -> v[0]));
        int count = 0;
        int end = -100_000_001;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curLeft = cur[0];
            int curRight = cur[1];

            if (curRight - curLeft > size) {
                continue;
            }

            if (curRight <= end) {
                prev.offer(new int[]{curLeft, curRight});
                count++;
                maxCount = Math.max(maxCount, count);
                continue;
            }

            while (!prev.isEmpty()) {
                int[] poll = prev.poll();
                if (curRight - poll[0] <= size) {
                    prev.offer(poll);
                    break;
                }
                count--;
            }
            end = curRight;
            prev.offer(new int[]{curLeft, curRight});
            count++;

            maxCount = Math.max(maxCount, count);
        }

        System.out.println(maxCount);
}
