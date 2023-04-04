package week10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 텀 프로젝트
public class Main_9466 {
    static int n;
    static List<Integer>[] list;
    static int[] containsTeam;

    public static int dfs(int start, int now) {
        for (int next : list[now]) {
            if (containsTeam[next] != 0) {
                continue;
            }
            
            if (start == next) { // 사이클
                return containsTeam[now] = 1;
            }

            if (dfs(start,next) == 1) { // 사이클에 now가 포함되는 경우
                return containsTeam[now] = 1;
            }
        }
        
        return containsTeam[now] = -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder answers = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testcase = 0; testcase < T; testcase++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            containsTeam = new int[n+1];
            list = new ArrayList[n+1];
            for (int i = 0; i <= n; i++) {
                list[i] = new ArrayList<>();
            }
            
            for (int i = 1; i <= n; i++) {
                int s = i;
                int e = Integer.parseInt(st.nextToken());
                
                list[e].add(s);
                
                if (s == e) { // 자기 자신과 팀을 하고 싶은 경우
                    containsTeam[s] = 1; // 팀 설정
                }
            }
            
            for (int i = 1; i <= n; i++) {
                if (list[i].size() == 0){ // 자기와 팀을 하고 싶은 사람이 없는 경우
                    containsTeam[i] = -1; // 팀 불가능
                    continue;
                }    
            }

            for (int i = 1; i <= n; i++) {
                if (containsTeam[i] != 0) {
                    continue;
                }

                dfs(i, i);
            }
            
            int answer = 0;
            for (int i = 1; i <= n; i++) {
                if (containsTeam[i] < 1)
                    answer++;
            }
            
            answers.append(answer).append("\n");
        }

        bw.write(answers.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}