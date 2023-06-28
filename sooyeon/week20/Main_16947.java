import sun.awt.image.ImageWatched;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16947 {
    static int N;
    static boolean isCycle;
    static boolean visited[];
    static int distance[];
    static Queue<Integer> q = new LinkedList<>();
    static List<Integer>[] list;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        visited = new boolean[N+1];
        distance = new int[N+1];
        Arrays.fill(distance,-1); //거리를 다 -1로 초기화

        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        //사이클 찾기
        isCycle = false; //사이클을 찾았는지
        dfs(0,1); //이전역, 현재역

        bfs();

        //출력
        for (int i = 1; i <= N; i++) {
            sb.append(distance[i]).append(" ");
        }

        System.out.println(sb.toString());

    }

    private static int bfs() {
        int count = 1;
        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();

                for (int next : list[cur]) {
                    if(distance[next] != -1) {
                        continue;
                    }
                    distance[next] = count;
                    q.offer(next);
                }
            }
            count++;
        }

        return 0;
    }

    private static void dfs(int prev, int cur){
        visited[cur] = true;
        for (int next : list[cur]) {
            //방문했던 곳을 다시 방문 -> 사이클을 이룸
            if(visited[next] && next!=prev) {
                isCycle = true;
                distance[next] = 0;
                q.offer(next);
                break;
            }else if(!visited[next]) {
                dfs(cur, next);
                if(isCycle) { //사이클을 찾은 상태라면
                    if(distance[next] == 0) {
                        //이미 사이클처리를 해줌 -> 사이클 부분 다 0처리 해줬으므로 isCycle = false
                        isCycle = false;
                    }else{
                        distance[next] = 0;
                        q.offer(next);
                    }
                    return;
                }
            }
        }
    }
}
