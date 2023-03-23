package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main14621 {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        String[] genders = br.readLine().split(" ");
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[2]));
        for (int i = 0; i < m; i++) {
            String[] uvd = br.readLine().split(" ");
            int u = Integer.parseInt(uvd[0]);
            int v = Integer.parseInt(uvd[1]);
            int d = Integer.parseInt(uvd[2]);

            if (genders[u - 1].equals(genders[v - 1])) {
                continue;
            }
            pq.offer(new int[] { u, v, d });
        }

        int total = 0;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            if (union(poll[0], poll[1])) {
                total += poll[2];
            }
        }

        boolean notConnectionExist = false;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    continue;
                }
                if (union(i, j)) {
                    notConnectionExist = true;
                    break;
                }
            }
        }

        System.out.println(notConnectionExist ? -1 : total);
    }

    public static int getParent(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = getParent(parent[x]);
    }

    public static boolean union(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a == b) {
            return false;
        }

        if (a < b) {
            parent[b] = a;
            return true;
        }
        parent[a] = b;
        return true;
    }
}