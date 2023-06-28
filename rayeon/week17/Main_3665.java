package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 3665 : 최종 순위
public class Main_3665 {
	static int n;
	static int[][] map; // 행 팀과 열 팀의 상대적인 순위를 나타내는 배열. 행 팀이 열 팀보다 순위가 높으면 -1, 낮으면 1
	
	public static void changeRank(int team1, int team2) {
		map[team1][team2] *= (-1);
		map[team2][team1] *= (-1);
	}
	
	// 각 팀의 순위를 찾기
	public static int[] calcRank() {
		int[] result = new int[n];
		
		for (int row = 0; row < n; row++) {
			int rank = 0;
			
			for (int col = 0; col < n; col++) {
				// 행에서의 1의 개수는 해당 팀보다 순위가 높은 팀의 개수
				if (map[row][col] == 1) {
					rank++;
				}
			}
			
			// 해당 순위에 이미 다른 팀이 있다면 데이터에 일관성이 없는 경우
			if (result[rank] > 0) {
				return null;
			}
			
			result[rank] = (row + 1);
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 0; testcase < T; testcase++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			int[] t = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				t[i] = Integer.parseInt(st.nextToken());
			}
			
			/* 행 팀의 순위가 열 팀의 순위보다 높으면 -1, 낮으면 1
			ex) t = {2, 3, 4, 5, 1}
			
			    1  2  3  4  5 
			 1  0  1  1  1  1
			 2 -1  0 -1 -1 -1
			 3 -1  1  0 -1 -1
			 4 -1  1  1  0 -1
			 5 -1  1  1  1  0
			*/
			for (int team : t) {
				for (int row = 0; row < n; row++) {
					// map 배열에 0이 아닌 값이 들어있으면 앞선 순위에서 이미 값이 할당된 것이므로 넘긴다.
					if (team - 1 == row || map[row][team - 1] != 0) {
						continue;
					}
					
					map[row][team - 1] = 1;
				}
				
				for (int col = 0; col < n; col++) {
					if (team - 1 == col || map[team - 1][col] != 0) {
						continue;
					}
					
					map[team - 1][col] = -1;
				}
			}
			
			int m = Integer.parseInt(br.readLine());
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int team1 = Integer.parseInt(st.nextToken()) - 1;
				int team2 = Integer.parseInt(st.nextToken()) - 1;
				
				changeRank(team1, team2);
			}
			
			int[] result = calcRank();
			if (result == null) {
				answer.append("IMPOSSIBLE");
			} else {
				for (int team : result) {
					answer.append(team).append(" ");
				}
			}
			
			answer.append("\n");
		}
		
		System.out.println(answer);
		br.close();
	}
}
