package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1987 {
	static int count = 0;
	static int R, C;
	static int[] visited = new int[27];		// 방문 체크 배열, 알파벳+'@' 
	static int[] rv = {1, 0, -1, 0};
	static int[] cv = {0, 1, 0, -1};
	static char[][] board;
	static int maxValue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		board = new char[R+2][C+2];
		
		for (int i = 1; i < R+1; i++) {
			char[] tmp = br.readLine().toCharArray();
			
			for (int j = 1; j < C+1; j++) {
				board[i][j] = tmp[j-1];
			}
		}
		
		// 보드 외곽을 @로 감싸준다
		for (int i = 0; i < R+2; i++) {
			board[i][0] = '@';
			board[i][C+1] = '@';
		}
		
		for (int i = 0; i < C+2; i++) {
			board[0][i] = '@';
			board[R+1][i] = '@';
		}
		
		// 탐색 시작
		maxValue = 0;
		// ASCII코드 값에서 @가 A바로 직전이기 때문에 visited[0]을 1로 초기화
		visited[0] = 1;
		search(1, 1, 0);

		System.out.println(maxValue);
	}

	private static void search(int i, int j, int max) {
		// 출발 칸의 알파벳에 해당하는 방문 체크 배열 인덱스 증가
		visited[board[i][j]-64]++;
		max++;
		
		for (int k = 0; k < 4; k++) {
			// 방문하지 않은 알파벳이라면
			if(visited[board[i+rv[k]][j+cv[k]]-64] == 0) {
				search(i+rv[k], j+cv[k], max);	// 이동하여 이어서 탐색 진행
			}
		}
		
		// depth 올라오면서 마지막으로 저장한 방문 알파벳 제거
		visited[board[i][j]-64]--;
		if(maxValue < max) maxValue = max;	// 최대값 비교
	}
}
