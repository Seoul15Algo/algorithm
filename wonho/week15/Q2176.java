package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2176 {
    private static final List<List<int[]>> graph = new ArrayList<>();
    private static long[] weights;
    private static int[] counts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        weights = new long[n + 1];
        counts = new int[n + 1];
        Arrays.fill(weights, Integer.MAX_VALUE);

        int m = Integer.parseInt(input[1]);
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int e1 = Integer.parseInt(edge[0]);
            int e2 = Integer.parseInt(edge[1]);
            int w = Integer.parseInt(edge[2]);
            graph.get(e1).add(new int[] { e2, w });
            graph.get(e2).add(new int[] { e1, w });
        }

        setDestWeight(new long[] { 2, 0 });

        counts[2] = 1;
        System.out.println(search(1));
    }

    public static void setDestWeight(long[] start) {
        Queue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(v -> v[1]));
        pq.offer(start);
        weights[(int) start[0]] = 0;

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int curVertex = (int) cur[0];
            long curWeight = cur[1];

            if (weights[curVertex] < curWeight) {
                continue;
            }

            for (int[] next : graph.get(curVertex)) {
                int nextVertex = next[0];
                int nextWeight = next[1];

                if (weights[nextVertex] > weights[curVertex] + nextWeight) {
                    pq.offer(new long[] { nextVertex, weights[curVertex] + nextWeight });
                    weights[nextVertex] = weights[curVertex] + nextWeight;
                }
            }
        }
    }

    public static int search(int cur) {
        if (counts[cur] != 0) {
            return counts[cur];
        }

        for (int[] next : graph.get(cur)) {
            if (weights[cur] > weights[next[0]]) {
                counts[cur] += search(next[0]);
            }
        }
        return counts[cur];
    }
}
