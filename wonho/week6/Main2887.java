package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2887 {

    static class Edge {
        private int vertex1;
        private int vertex2;
        private int distance;

        public Edge(int vertex1, int vertex2, int distance) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.distance = distance;
        }

        public int getVertex1() {
            return vertex1;
        }

        public int getVertex2() {
            return vertex2;
        }

        public int getDistance() {
            return distance;
        }
    }

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<int[]> planets = new ArrayList<>();
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            String[] xyz = br.readLine().split(" ");
            planets.add(new int[] { Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]), i });
        }

        Queue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(Edge::getDistance));

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            planets.sort(Comparator.comparingInt(v -> v[finalI]));

            for (int j = 0; j < n - 1; j++) {
                int[] vertex1 = planets.get(j);
                int[] vertex2 = planets.get(j + 1);
                int x = Math.abs(vertex1[0] - vertex2[0]);
                int y = Math.abs(vertex1[1] - vertex2[1]);
                int z = Math.abs(vertex1[2] - vertex2[2]);
                int min = Math.min(Math.min(x, y), z);
                edges.offer(new Edge(vertex1[3], vertex2[3], min));
            }
        }

        int count = 0;
        int total = 0;
        while (count < n - 1) {
            Edge edge = edges.poll();
            if (!union(edge.getVertex1(), edge.getVertex2())) {
                continue;
            }
            total += edge.getDistance();
            count++;
        }
        System.out.println(total);
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