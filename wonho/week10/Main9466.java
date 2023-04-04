package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main9466 {
    private static BufferedReader br;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            testcase();
        }

        System.out.print(sb);
    }

    private static void testcase() throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] members = new int[n + 1];
        int[] teamCheck = new int[n + 1];
        int[] inDegree = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = n;
        for (int i = 1; i <= n; i++) {
            members[i] = Integer.parseInt(st.nextToken());
            inDegree[members[i]]++;
            if (members[i] == i) {
                teamCheck[i] = 2;
                size--;
            }
        }
        
        for (int i = 1; i <= n; i++) {
        	if (inDegree[i] == 0) {
        		teamCheck[i] = 1;
        	}
        }

        for (int i = 1; i <= n; i++) {
            int cur = i;
            int count = 0;
            if (teamCheck[i] != 0) {
                continue;
            }

            List<Integer> visited = new ArrayList<>();
            while (count < size) {
                visited.add(cur);
                if (members[cur] == i) {
                    for (int member : visited) {
                        teamCheck[member] = 2;
                    }
                    size -= visited.size();
                    break;
                }
                
                if (teamCheck[cur] != 0) {
                    for (int member: visited) {
                    	if (teamCheck[members[member]] == 0) {
                    		teamCheck[members[member]] = 1;                    		
                    	}
                    }
                    break;
                }

                cur = members[cur];
                count++;
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (teamCheck[i] != 2) {
                answer++;
            }
        }
        sb.append(answer).append("\n");
    }
}
