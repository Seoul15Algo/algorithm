package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9466 {
	static int count;
	static int[] students;
	static boolean[] visited;	// 사이클 체크
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			count = 0;
			
			N = Integer.parseInt(br.readLine());
			students = new int[N+1];
			visited = new boolean[N+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i < N+1; i++) {
				students[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i < N+1; i++) {
				// 이미 팀 구성 여부가 검사가 끝난 학생이면 패스
				if(students[i] == 0) continue;
				
				// 아니면 팀 구성 여부 검사
				dfs(i);	
			}
			
			System.out.println(N-count);
		}
	}
	
	private static void dfs(int n) {
		// 아직 검사가 되지 않은 학생이 이미 검사 완료된 학생과 이어졌을 경우에는 팀이 될 수 없다
		if(students[n] == 0) {
			return;
		}
		
		// 검사가 완료된 학생이 아니라면
		// 즉, 이번 회차에 검사를 진행한 학생이라면 사이클 생성
		if(visited[n]) {
			int temp = students[n];
			students[n] = 0;	// 검사 완료 체크
			count++;	// 사이클에 포함되었다면 팀 구성이 가능한 학생이기 때문에 카운트 증가
			
			dfs(temp);	// 이어서 사이클에 포함된 모든 구성원 체크
			return;
		}
		
		visited[n] = true;		// 사이클 체크
		dfs(students[n]);	// 이어서 체크
		students[n] = 0;	// 팀 구성이 되었든 안되었든 검사 완료
	}
	
}