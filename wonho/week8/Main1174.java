package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main1174 {

    private static int[] parent;
    private static int[][] pos;
    private static int n;
    private static int m;
    private static double total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        parent = new int[n + 1];
        pos = new int[n + 1][2];
        total = 0.0;
        for (int i = 1; i <= n; i++) {
            String[] xy = br.readLine().split(" ");
            pos[i] = new int[] { Integer.parseInt(xy[0]), Integer.parseInt(xy[1]) };
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            union(a, b);
        }

        // kruskal idx 2: 거리
        Queue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> v[2]));
        for (int i = 1; i <= n; i++) {
            int ax = pos[i][0];
            int ay = pos[i][1];
            for (int j = i + 1; j <= n; j++) {
                int bx = pos[j][0];
                int by = pos[j][1];

                pq.offer(new double[] { i, j,
                        Math.sqrt(Math.pow(Math.abs(ax - bx), 2) + Math.pow(Math.abs(ay - by), 2)) });
            }
        }

        while (!pq.isEmpty()) {
            double[] poll = pq.poll();
            int a = (int) poll[0];
            int b = (int) poll[1];
            if (union(a, b)) {
                total += poll[2];
            }
        }

        System.out.printf("%.2f\n", total);
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