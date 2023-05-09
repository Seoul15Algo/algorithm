package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BJ 20061 : 모노미노도미노2
public class Main_20061 {
	static final int BOARD_SIZE = 4;
	static int score = 0;
	static int[][] cols = {{0}, {0, 1}, {0}};
	
	static void removeRow(int[][] board, int row) {
		for (int i = row; i > 0; i--) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = board[i-1][j];
			}
		}
		
		Arrays.fill(board[0], 0);
	}
	
	static int getMaximumRow(int[][] board, int col) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][col] != 0) {
				return (i - 1);
			}
		}
		
		return BOARD_SIZE - 1;
	}
	
	static void moveBlock(int[][] board, int x, int y) {
		board[x][y] = 1;
	}
	
	static boolean checkRow(int[][] board, int row) {
		for (int col = 0; col < BOARD_SIZE; col++) {
			if (board[row][col] == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	static void process(int[][] board, int t, int x, int y) {
		int minimumRow = BOARD_SIZE - 1;
		for (int col : cols[t-1]) {
			minimumRow = Math.min(getMaximumRow(board, col + y), minimumRow);
		}

		if (minimumRow < 0) {
			removeRow(board, BOARD_SIZE - 1);
			minimumRow = 0;
		}

		moveBlock(board, minimumRow, y);
		if (t == 2) {
			moveBlock(board, minimumRow, y + 1);
		}
		
		if (checkRow(board, minimumRow)) {
			score++;
			removeRow(board, minimumRow);
		}
	}
	
	static int countBlock(int[][] board) {
		int count = 0;
		
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] > 0) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] green = new int[BOARD_SIZE][BOARD_SIZE];
		int[][] blue = new int[BOARD_SIZE][BOARD_SIZE];
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			process(green, t, x, y);
			
			if (t == 1) {
				process(blue, 1, y, x);
			}
			if (t == 2) {
				process(blue, 3, y, x);
				process(blue, 3, y, x);
			}
			if (t == 3) {
				process(green, t, x, y);
				process(blue, 2, y, x);
			}
		}
		
		System.out.println(score);
		System.out.println(countBlock(blue) + countBlock(green));
		
		br.close();
	}

}