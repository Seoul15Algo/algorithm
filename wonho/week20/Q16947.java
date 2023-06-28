package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q16947 {

    private static int n;

    private static List<List<Integer>> graph;
    private static boolean[] visited;
    private static int[] cycle;
    private static boolean[] check;
    private static int[] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        graph = new ArrayList<>();

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        cycle = new int[n + 1];
        check = new boolean[n + 1];
        distances = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        cycle[1] = 1;
        visited[1] = true;
        search(1);

        for (int i = 1; i <= n; i++) {
            if (!check[i]) {
                findDistance(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            sb.append(distances[i]).append(" ");
        }
        sb.append(distances[n]);
        System.out.println(sb);
    }

    public static void findDistance(int start) {
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        dq.offerLast(new int[] { start, 0 });

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();

            for (int next : graph.get(cur[0])) {
                if (visited[next]) {
                    continue;
                }

                if (check[next]) {
                    distances[start] = cur[1] + 1;
                    return;
                }

                visited[next] = true;
                dq.offerLast(new int[] { next, cur[1] + 1 });
            }
        }
    }

    public static int search(int cur) {
        int result = -1;
        for (int next : graph.get(cur)) {
            if (next == cycle[cur]) {
                continue;
            }
            if (visited[next]) {
                check[cur] = true;
                return next;
            }
            cycle[next] = cur;
            visited[next] = true;
            int temp = search(next);
            if (temp != -1) {
                result = temp;
                if (result == cur) {
                    check[cur] = true;
                    return -1;
                }
                check[cur] = true;
            }
        }
        return result;
    }
}