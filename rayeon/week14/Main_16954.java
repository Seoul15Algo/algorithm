package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// BJ 16954 : 움직이는 미로 탈출
public class Main_16954 {
	static final int MAP_SIZE = 8;
	static int[][] directios = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {0, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
	static char[][] map;
	static PriorityQueue<int[]> walls;
	
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < MAP_SIZE && c <MAP_SIZE;
	}
	
	static void moveWall() {
		int wallSize = walls.size();
		
		for (int i = 0; i < wallSize; i++) {
			int[] wall = walls.poll();
			
			map[wall[0]][wall[1]] = '.';
			
			if (wall[0] == MAP_SIZE - 1) 
				continue;

			map[wall[0] + 1][wall[1]] = '#';
			walls.add(new int[] {wall[0] + 1, wall[1], wall[2] + 1});
		}
	}
	
	static int bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r, c, 0});
		
		int time = 0;
		while (!q.isEmpty()) {
			int[] now = q.poll();

			if (time != now[2]) {
				moveWall();
				time++;
			}
			
			if (map[now[0]][now[1]] == '#')
				continue;
			
			if (walls.size() == 0) {
				return 1;
			}
			
			for (int i = 0; i < directios.length; i++) {
				int nr = now[0] + directios[i][0];
				int nc = now[1] + directios[i][1];
				
				if (!check(nr, nc) || map[nr][nc] == '#')
					continue;
				
				if (nr == 0) {
					return 1;
				}
				
				q.offer(new int[] {nr, nc, now[2] + 1});
			}
		}
		
		return 0;
	} 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[MAP_SIZE][MAP_SIZE];
		walls = new PriorityQueue<>( new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[2] == o2[2]) {
					return Integer.compare(o2[0], o1[0]);
				}
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		for (int i = 0; i < MAP_SIZE; i++) {
			map[i] = br.readLine().toCharArray();
			
			for (int j = 0; j < MAP_SIZE; j++) {
				if (map[i][j] == '#') {
					walls.add(new int[] {i, j, 0});
				}
			}
		}

		System.out.println(bfs(7,0));
		
		br.close();
	}

}