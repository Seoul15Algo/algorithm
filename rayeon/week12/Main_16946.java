package week12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16946 {
	static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M;
	static int[][] map;
	static int[][] result;
	static boolean[][] visited;
	
	static boolean check(int row, int col) {
		if (row < 0 || col < 0 || row >= N || col >= M)
			return false;
		return true;
	}
	
	static void bfs(int row, int col) {
		List<int[]> l = new ArrayList<int[]>();
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {row, col});
		visited[row][col] = true;
		
		int count = 1;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			
			for (int i = 0; i <4; i++) {
				int nr = now[0] + directions[i][0];
				int nc = now[1] + directions[i][1];
				
				if (!check(nr, nc) || visited[nr][nc])
					continue;
				
				visited[nr][nc] = true;
				
				if (map[nr][nc] == 1) 
					l.add(new int[] {nr, nc});
				else {
					count++;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		
		for (int[] wall : l) {
			result[wall[0]][wall[1]] += count;
			visited[wall[0]][wall[1]] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		result = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			
			for (int j = 0; j < M; j++) {
				map[i][j] = input[j] - '0';
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) { 
				if (map[i][j] == 0 && !visited[i][j]) {
					bfs(i,j);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					result[i][j]++;
				sb.append(result[i][j] % 10);
			}
			sb.append("\n");
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}