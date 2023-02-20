package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node{
	int x;
	int y;
	int num;
	
	public Node(int x, int y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}
}

public class Main_12100 {
	static int[] order = new int[5];
	
	static int N;
	static int[][] originMap;
	static int[][] copyMap;

	static int result = Integer.MIN_VALUE;
	
	static void copy() {
		for (int i = 0; i < N; i++)
			copyMap[i] = Arrays.copyOf(originMap[i], N);
	}

	static void moveUp() {
		for (int c = 0; c < N; c++) {
			Node top = null;
			int empty = 0;
			
			for (int r = 0; r < N; r++) {
				if (copyMap[r][c] == 0)
					continue;

				int now = copyMap[r][c];
				copyMap[r][c] = 0;
				
				if (top == null) {
					copyMap[empty][c] = now;
					
					top = new Node(empty, c, now);
					continue;
				}

				if (top.num == now) {
					now *= 2;
					copyMap[top.x][c] = now;
					
					empty = top.x + 1;
					top = null;
					
					continue;
				}
								
				copyMap[top.x + 1][c] = now;
				top = new Node(top.x + 1, c, now);
			}
		}
	}
	
	static void moveDown() {
		for (int c = 0; c < N; c++) {
			Node top = null;
			int empty = N-1;
			
			for (int r = N-1; r >= 0; r--) {
				if (copyMap[r][c] == 0)
					continue;

				int now = copyMap[r][c];
				copyMap[r][c] = 0;
				
				if (top == null) {
					copyMap[empty][c] = now;
					top = new Node(empty, c, now);
					
					continue;
				}

				if (top.num == now) {
					now *= 2;
					copyMap[top.x][c] = now;
					
					empty = top.x - 1;
					top = null;
					
					continue;
				}

				copyMap[top.x - 1][c] = now;
				top = new Node(top.x - 1, c, now);
			}
		}
	}
	
	static void moveLeft() {	
		for (int r = 0; r < N; r++) {
			Node top = null;
			int empty = 0;

			for (int c = 0; c < N; c++) {
				if (copyMap[r][c] == 0)
					continue;
				
				int now = copyMap[r][c];
				copyMap[r][c] = 0;
				
				if (top == null) {
					copyMap[r][empty] = now;
					top = new Node(r, empty, now);
					
					continue;
				}
				
				if(top.num == now) {
					now *= 2;
					copyMap[r][top.y] = now;
					
					empty = top.y + 1;
					top = null;
					
					continue;
				}

				copyMap[r][top.y + 1] = now;
				top = new Node(r, top.y + 1, now);
			}
		}
	}
	
	static void moveRight() {
		for (int r = 0; r < N; r++) {
			Node top = null;
			int empty = N-1;

			for (int c = N-1; c >= 0; c--) {
				if (copyMap[r][c] == 0)
					continue;
				
				int now = copyMap[r][c];
				copyMap[r][c] = 0;
				
				if (top == null) {
					copyMap[r][empty] = now;
					top = new Node(r, empty, now);
					
					continue;
				}
				
				if(top.num == now) {
					now *= 2;
					copyMap[r][top.y] = now;
					
					empty = top.y - 1;
					top = null;
					
					continue;
				}

				copyMap[r][top.y - 1] = now;
				top = new Node(r, top.y - 1, now);
			}
		}
	}
	
	static void perm(int cnt) {
		if (cnt == 5) {
			copy();
			
			for (int i = 0; i < 5; i++) {
				switch (order[i]) {
				case 0:
					moveUp();
					break;
				case 1:
					moveDown();
					break;
				case 2:
					moveLeft();
					break;
				case 3:
					moveRight();
					break;
					
				default:
					break;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (copyMap[i][j] > result)
						result = copyMap[i][j];
				}
			}
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			order[cnt] = i;
			
			perm(cnt + 1);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		originMap = new int[N][N];
		copyMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) 
				originMap[i][j] = Integer.parseInt(st.nextToken());
		}
		
		perm(0);

		System.out.println(result);
	}
}