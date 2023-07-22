package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1939 {
    static int N, M, start, end, maxWeight;
    static List<Node>[] graph;
    static class Node {
        int num, weightLimit;

        public Node(int num, int weightLimit) {
            this.num = num;
            this.weightLimit = weightLimit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        findMaxWeight();

        System.out.println(maxWeight);
    }

    private static void findMaxWeight() {
        int left = 0;
        int right = 1000000000;

        while (left <= right) { // 이분 탐색을 진행하며 중량을 조절해가며 최대 중량을 탐색
            int mid = (left + right) / 2;

            if (bfs(mid)) { // 현재 중량 값으로 도착지까지 갈 수 있는 경우
                left = mid + 1;
                maxWeight = Integer.max(maxWeight, mid);
                continue;
            }

            right = mid - 1; // 현재 mid 값으로 도착지까지 갈 수 없는 경우
        }
    }

    private static boolean bfs(int weight) {
        Queue<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        visited[start] = true;
        que.add(start);

        while (!que.isEmpty()) {
            int cur = que.poll();

            if (cur == end) {
                return true;
            }

            for (Node next : graph[cur]) {
                if (next.weightLimit < weight) { // 중량 제한을 초과할 경우
                    continue;
                }

                if (visited[next.num]) {
                    continue;
                }

                visited[next.num] = true;
                que.add(next.num);
            }
        }

        return false;
    }
}
