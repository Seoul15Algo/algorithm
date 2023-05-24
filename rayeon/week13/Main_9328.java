package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_9328 {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int h, w;
    static char[][] map;
    static Map<Character, Integer> keys;
    static int answer;
    static boolean[][] visited;

    static boolean check(int r, int c) {
        if (r < 0 || c < 0 || r >= h || c >= w)
            return false;
        return true;
    }

    static int bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		
		int count = 0;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = now[0] + directions[i][0];
				int nc = now[1] + directions[i][1];
				
				if (!check(nr, nc) || map[nr][nc] == '*' || visited[nr][nc])
					continue;
				
				visited[nr][nc] = true;
				
				if (map[nr][nc] == '$') {
					answer++;
				}
				
				if (map[nr][nc] >= 'a' && map[nr][nc] <= 'z') {
					if (!keys.containsKey(map[nr][nc])) {
                  		keys.put(map[nr][nc], 1);
                  		count++;
                  	}
				}
				
				if (map[nr][nc] >= 'A' && map[nr][nc] <= 'Z') {
					if (!keys.containsKey((char)(map[nr][nc] + 32))) {
						continue;
					}
				}
				
				map[nr][nc] = '.';
				q.offer(new int[] {nr, nc});
			}
		}
    	
		return count;
	}
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer answers = new StringBuffer();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testcase = 0; testcase < T; testcase++) {
            st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
            }

            keys = new HashMap<>();
            for (char key : br.readLine().toCharArray()) {
                if (key == '0')
                    break;

                keys.put(key, 1);
            }

            answer = 0;
            
            List<int[]> startList = new ArrayList<>();
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (j > 0 && j < w - 1) {
                        if (i != 0 && i != h - 1)
                            continue;
                    }
                    
                    if (map[i][j] == '*')
                    	continue;

                    if (map[i][j] == '$') {
                        answer++;
                        map[i][j] = '.';
                    }

                    if (map[i][j] >= 'a' && map[i][j] <= 'z') { // 열쇠인 경우
                    	keys.put(map[i][j], 1);
                        map[i][j] = '.';
                    }
                    
                    startList.add(new int[] {i, j});
                }
            }
            
            int count = 1;
            while (count > 0) {
            	count = 0;
            	
            	visited = new boolean[h][w];
            	for (int[] start : startList) {
                	if (map[start[0]][start[1]] != '.') {
                		if (!keys.containsKey((char)(map[start[0]][start[1]] + 32))) {
                			continue;
                		}
                		
                		map[start[0]][start[1]] = '.';
                	}
                	
                	count += bfs(start[0], start[1]);
                }
            }
            
            answers.append(answer).append("\n");
        }

        System.out.println(answers);
        br.close();
    }
}