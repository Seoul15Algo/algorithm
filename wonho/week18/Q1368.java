package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1368 {

    private static int n;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[2]));

        for (int i = 1; i <= n; i++) {
            pq.offer(new int[] { i, 0, Integer.parseInt(br.readLine()) });
        }

        for (int i = 1; i <= n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = i + 1; j <= n; j++) {
                pq.offer(new int[] { i, j, Integer.parseInt(row[j - 1]) });
            }
        }

        int total = 0;
        int count = 0;
        while (count < n && !pq.isEmpty()) {
            int[] poll = pq.poll();

            if (union(poll[0], poll[1])) {
                total += poll[2];
                count++;
            }
        }
        System.out.println(total);
    }

    public static int getParent(int x) {
        if (x == parents[x]) {
            return x;
        }

        return parents[x] = getParent(parents[x]);
    }

    public static boolean union(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a == b) {
            return false;
        }

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
        return true;
    }
}