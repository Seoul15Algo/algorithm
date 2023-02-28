package week4;

import java.util.Scanner;

public class Main_9663 {
	static int N;
	static int count;
	static int[] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N];
		count = 0;
		
		dfs(0);
		System.out.println(count);
		
		sc.close();
	}
	
	private static void dfs(int cnt) {
		if(cnt == N) {
			count++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			// 2차원배열의 map[cnt][i] 위치에 퀸을 배치하는것과 동일한 작용
			map[cnt] = i;
			
			if(checkQueen(cnt)) {
				dfs(cnt+1);
			}
		}
	}

	private static boolean checkQueen(int col) {
		for (int i = 0; i < col; i++) {
			// 인덱스(열)이 아닌 값(행)을 비교, 같은 행이면 놓지 못함
			if(map[col] == map[i]) return false;
			
			// 인덱스의 차이와 값의 차이가 같을 경우(열과 행의 차이가 같은 경우) 대각선에 위치, 놓지 못함
			if(Math.abs(col - i) == Math.abs(map[col] - map[i])) return false;
		}
		
		return true;
	}
	
}
