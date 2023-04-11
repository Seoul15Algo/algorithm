package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9466 {
    static int T, N;
    static boolean[] visited;
    static boolean[] isAlreadyChecked;
    static int[] edges;
    static int students;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());

            edges = new int[N+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int start = 1; start < N+1; start++) { //간선 정보 입력
                int end = Integer.parseInt(st.nextToken());
                edges[start] = end;
            }

            students = 0;
            visited = new boolean[N + 1]; //방문 여부 저장
            isAlreadyChecked = new boolean[N + 1]; //이전에 확인한 노드인지 여부 저장
            for (int i = 1; i < N+1; i++) { //연결되어 있지 않는 노드가 존재할 수 있으므로 dsfAll 수행
                if(!visited[i]){
                    visited[i] = true;
                    dfs(i);
                }
            }
            sb.append(N-students).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int start){ //Back Edge가 존재한다는 건 싸이클이 존재한다는 의미이다.
        int end = edges[start];
        if (!visited[end]) { //방문하지 않았던 곳이라면
            visited[end] = true;
            dfs(end);
        }
        if(visited[end] && !isAlreadyChecked[end]){ //이전에 확인하지 않았고 방문한 곳에 재방문 하는 경우라면 => 싸이클 발생
            calSubGroup(start, end);
        }
        isAlreadyChecked[start] = true; //dfs 순회가 끝난 뒤 돌아오면서 방문 체크
    }

    private static void calSubGroup(int start, int end) { //싸이클이 시작되는 지점 부터 끝점까지 재귀를 통해 순회하며 카운트를 세준다.
        students++;
        if(end == start){
            return;
        }
        calSubGroup(start, edges[end]);
    }

}
