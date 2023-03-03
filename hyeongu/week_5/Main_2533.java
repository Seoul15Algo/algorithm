import java.util.*;
import java.io.*;

public class Main_2533 {
    static int N;
    static int[][] dp;
    static boolean[] visit;
    static class Node{
        List<Node> list;
        int idx;
        public Node(int i){
            list = new ArrayList<>();
            this.idx = i;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Node> nodes = new ArrayList<>();
        dp = new int[N][2];         // 각 노드가 얼리어답터 일때의 최솟값과 얼리어답터가 아닐때의 최솟값을 저장하는 배열
        visit = new boolean[N];     // dfs에서 사용할 visit 배열

        for(int i = 0; i < N; i++){
            nodes.add(new Node(i));
        }
        for(int i = 0; i < N - 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            // index를 0부터 사용하기 위해 -1
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            // 노드를 양방향으로 연결
            nodes.get(u).list.add(nodes.get(v));
            nodes.get(v).list.add(nodes.get(u));
        }
        // 첫 번째 노드가 얼리어답터일 경우와 얼리어답터가 아닐 경우의 최솟값을 출력
        visit[0] = true;
        int answer = Math.min(dfs(nodes.get(0), true), dfs(nodes.get(0), false));
        System.out.println(answer);
    }

    static int dfs(Node n, boolean flag){
        // 얼리어답터가 아닐 경우
        if(!flag){
            for(Node next : n.list){
                // 해당 경로에서 이미 방문한 노드
                if(visit[next.idx]){
                    continue;
                }

                // 한번도 방문한 적이 없는 노드
                // 현재 노드가 얼리어답터가 아니므로 다음 노드는 무조건 얼리어답터여야한다.
                if(dp[next.idx][0] == 0){
                    visit[next.idx] = true;
                    dfs(next, true);
                    visit[next.idx] = false;
                }

                // 다른 경로에서 이미 방문한 적이 있는 노드
                dp[n.idx][1] += dp[next.idx][0];
            }
            return dp[n.idx][1];
        }

        //얼리어답터인 경우
        for(Node next : n.list){
            // 해당 경로에서 이미 방문한 노드
            if(visit[next.idx]){
                continue;
            }

            // 한번도 방문한 적이 없는 노드
            // 현재 노드가 얼리어답터인 경우 다음 노드는 얼리어답터여도 되고 아니어도 된다
            visit[next.idx] = true;
            if(dp[next.idx][0] == 0) {
                dfs(next, true);
            }
            if(dp[next.idx][1] == 0) {
                dfs(next, false);
            }
            visit[next.idx] = false;

            // 다른 경로에서 이미 방문한 적이 있는 노드
            // 두 경우의 최솟값을 저장
            dp[n.idx][0] += Math.min(dp[next.idx][0] , dp[next.idx][1]);
        }
        dp[n.idx][0]++;
        return dp[n.idx][0];
    }
}