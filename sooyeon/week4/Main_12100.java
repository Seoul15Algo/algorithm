package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12100 {
	static int N;
	static int moveCnt = 5;
	static int[][] board; //초기값, 수정X
	static int[][] copy1;
	static int[][] copy2;
	static int[] move; //0:위 , 1:오 , 2:아, 3:왼
	static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		copy1 = new int[N][N];
		copy2 = new int[N][N];
		move = new int[moveCnt];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		perm(0);
		System.out.println(result);

	}
	static void perm(int cnt) {
		if(cnt== moveCnt) {
			//board -> copy1 복사
			for (int i = 0; i < N; i++) {
				System.arraycopy(board[i], 0, copy1[i], 0, board[0].length);
			}

			for (int i = 0; i < moveCnt; i++) {
				//copy1 -> copy2 복사
				for (int j = 0; j < N; j++) {
					System.arraycopy(copy1[j], 0, copy2[j], 0, copy1[0].length);
				}
				
				if(move[i] == 0)
					moveUp();
				if(move[i] ==1)
					moveRight();
				if(move[i] ==2)
					moveDown();
				if(move[i] ==3)
					moveLeft();
				
			}
			//다 돌리고 최댓값 계산
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					result = Math.max(result, copy1[i][j]);
				}
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			move[cnt] = i;
			perm(cnt+1);
			move[cnt] = 0;
		}
		
	}
	static void moveLeft() {
		for (int i = 0; i < N; i++) {
			int index = 0;
			for (int j = 0; j < N; j++) {
				if(copy2[i][j] == 0) continue;
				boolean isSame = false;
				
				for (int k = j+1; k < N; k++) {
					if(copy2[i][k] == 0) continue;
					if(copy2[i][j] == copy2[i][k]) {
						copy1[i][index++] = 2*copy2[i][j];
						j = k;
						isSame = true;
						break;
					}
					//다른걸 만났을 경우
					break;
				}
				if(isSame == false) {
					copy1[i][index++] = copy2[i][j];
				}
			}
			for (int j = index; j < N; j++) {
				copy1[i][j] = 0;
			}
		}
	}
	
	static void moveRight() {
		for (int i = 0; i < N; i++) {
			int index = N-1;
			for (int j = N-1; j >= 0; j--) {
				if(copy2[i][j] == 0) continue;
				boolean isSame = false;
				
				for (int k = j-1; k >= 0; k--) {
					if(copy2[i][k] == 0) continue;
					if(copy2[i][j] == copy2[i][k]) {
						copy1[i][index--] = 2*copy2[i][j];
						j = k;
						isSame = true;
						break;
					}
					break;
				}
				if(isSame == false) {
					copy1[i][index--] = copy2[i][j];
				}
			}
			for (int j = 0; j < index+1; j++) {
				copy1[i][j] = 0;
			}
		}
		
	}
	static void moveDown() {
		for (int i = 0; i < N; i++) {
			int index = N-1;
			for (int j = N-1; j >= 0; j--) {
				if(copy2[j][i] == 0) continue;
				boolean isSame = false;
				
				for (int k = j-1; k >=0 ; k--) {
					if(copy2[k][i] == 0) continue;
					if(copy2[j][i] == copy2[k][i]) {
						copy1[index--][i] = 2*copy2[j][i];
						j = k;
						isSame = true;
						break;
					}
					//다른걸 만났을 경우
					break;
				}
				if(isSame == false) {
					copy1[index--][i] = copy2[j][i];
				}
			}
			for (int j = 0; j < index+1; j++) {
				copy1[j][i] = 0;
			}
		}
	}
	static void moveUp() {
		for (int i = 0; i < N; i++) {
			int index = 0;
			for (int j = 0; j < N; j++) {
				if(copy2[j][i] == 0) continue;
				boolean isSame = false;
				
				for (int k = j+1; k <N ; k++) {
					if(copy2[k][i] == 0) continue;
					if(copy2[j][i] == copy2[k][i]) {
						copy1[index++][i] = 2*copy2[j][i];
						j = k;
						isSame = true;
						break;
					}
					//다른걸 만났을 경우
					break;
				}
				if(isSame == false) {
					copy1[index++][i] = copy2[j][i];
				}
			}
			for (int j = index; j < N; j++) {
				copy1[j][i] = 0;
			}
		}
	}

}
