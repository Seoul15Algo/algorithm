package week28;

import java.util.*;

class 전력망을_둘로_나누기 {
    static ArrayList<Integer>[] towers;
    static boolean[] visited;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        towers = new ArrayList[n+1];

        for(int i = 1; i < n+1; i++) {
            towers[i] = new ArrayList<>();
        }

        for(int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            towers[a].add(b);
            towers[b].add(a);
        }

        for(int i = 1; i < n+1; i++) {
            for(int linkedTower : towers[i]) {
                visited = new boolean[n+1];
                visited[linkedTower] = true;

                int countTower = bfs(i);

                answer = Integer.min(answer, Math.abs((n-countTower) - countTower));
            }
        }

        return answer;
    }

    public int bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);

        visited[n] = true;

        int count = 1;

        while(!q.isEmpty()) {
            int now = q.poll();

            for(int next : towers[now]) {
                if(visited[next]) continue;
                count++;
                visited[next] = true;
                q.add(next);
            }
        }

        return count;
    }
}