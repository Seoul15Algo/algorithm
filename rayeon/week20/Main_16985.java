package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ 16985: Maaaaaaaaaze
public class Main_16985 {
	static Map[] maps;
	static int[] mapOrder; // 판을 놓을 순서
	static int[] rotationOrder; // 각 판의 회전 횟수
	static int[][][] maze;
	static int answer;
	
	static int[][] directions = {
			{1, 0, 0}, {-1, 0, 0},
			{0, 1, 0}, {0, -1, 0},
			{0, 0, 1}, {0, 0, -1}
	};
	
	static class Map {
		int[][][] rotatedMaps;
		
		public Map() {
			rotatedMaps = new int[4][5][5];
		}
		
		public void setRotatedMaps() {
			for (int i = 0; i < 3; i++) {
				for (int row = 0; row < 5; row++) {
					for (int col = 0; col < 5; col++) {
						rotatedMaps[i+1][row][col] = rotatedMaps[i][col][4 - row];
					}
				}
			}
		}
	}
	
	public static boolean checkInMap(int x, int y, int z) {
		return (x >= 0 && x < 5 && y >= 0 && y < 5 && z >= 0 && z < 5);
	}
	
	// 판의 순서 정하기
	public static void perm(int depth, boolean[] visited) {
		if (depth == 5) {
			permWithRepetition(0);
			
			return;
		}
		
		for (int i = 0; i < 5; i++) {
			if (!visited[i]) {
				visited[i] = true;
				mapOrder[depth] = i;
				perm(depth + 1, visited);
				visited[i] = false;
			}
		}
	}
	
	// 판의 회전 횟수 정하기
	public static void permWithRepetition(int depth) {
		if (depth == 5) {
			bfs();
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			rotationOrder[depth] = i; 
			permWithRepetition(depth + 1);
		}
	}
	
	public static void bfs() {
		for (int i = 0; i < 5; i++) {
			maze[i] = maps[mapOrder[i]].rotatedMaps[rotationOrder[i]];
		}
		
		if (maze[0][0][0] == 0) {
			return;
		}
		
		boolean[][][] visited = new boolean[5][5][5];
		Queue<int[]> q = new LinkedList<>();
		
		visited[0][0][0] = true;
		q.offer(new int[] {0, 0, 0, 0});
		
		while (!q.isEmpty()) {
			int[] now = q.poll();
			
			for (int d = 0; d < 6; d++) {
				int nX = now[0] + directions[d][0];
				int nY = now[1] + directions[d][1];
				int nZ = now[2] + directions[d][2];
				
				if (!checkInMap(nX, nY, nZ) || visited[nX][nY][nZ] || maze[nX][nY][nZ] == 0) {
					continue;
				}
				
				if (now[3] + 1 >= answer) {
					return;
				}
				
				if (nX == 4 && nY == 4 && nZ == 4) {
					answer = now[3] + 1;
					return;
				}
				
				visited[nX][nY][nZ] = true;
				q.offer(new int[] {nX, nY, nZ, now[3] + 1});
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		maps = new Map[5];
		mapOrder = new int[5];
		rotationOrder = new int[5];
		maze = new int[5][5][5];
		answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < 5; i++) {
			maps[i] = new Map();
			
			for (int row = 0; row < 5; row++) {
				st = new StringTokenizer(br.readLine());
						
				for (int col = 0; col < 5; col++) {
					maps[i].rotatedMaps[0][row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			maps[i].setRotatedMaps();
		}
		
		perm(0, new boolean[5]);
		
		if (answer == Integer.MAX_VALUE) {
			answer = -1;
		}
		
		System.out.println(answer);
		br.close();
	}
}