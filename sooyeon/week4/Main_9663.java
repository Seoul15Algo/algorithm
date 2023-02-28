package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9663 {
	static int N;
	static int[] chessCol;
	static int result; //결과값
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		chessCol = new int[N]; //인덱스 : 행, 값: 열
		result = 0;
		
		queen(0);
		
		System.out.println(result);

	}
	static void queen(int depth) {
		//모두 탐색했을 경우
		if(depth == N) {
			result++;
			return;
		}
		
		//해당 열의 모든 경우를 탐색 & 놓을 수 있으면 다음 열로
		for (int i = 0; i < N; i++) {
			chessCol[depth] = i;
			if(isPossible(depth))
				queen(depth+1);
		}
	}
	static boolean isPossible(int col) {
		for (int i = 0; i < col; i++) {
			//해당 열과 i열의 행이 일치 -> false(세로 겹침)
			if(chessCol[col] == chessCol[i])
				return false;
			//대각선(기울기 절댓값이  1인 경우)
			if(Math.abs(col-i) == Math.abs(chessCol[col]-chessCol[i]))
				return false;
		}
		//어느 부분도 겹치지 않으면
		return true;
	}

}