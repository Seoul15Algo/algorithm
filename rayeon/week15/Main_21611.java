package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ 21611 : 마법사 상어와 블리자드
public class Main_21611 {
    static int N, M;
    static int[][] map;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] marbleCount;
    
    static void castSpells(int d, int s) {
        int r = N / 2;
        int c = N / 2;
        
        for (int i = 0; i < s; i++) {
            r += directions[d][0];
            c += directions[d][1];
                    
            map[r][c] = 0;
        }

        moveMarbles();
    }
    
    static void moveMarbles() {
        int r = N / 2;
        int c = N / 2;
        
        Queue<int[]> q = new LinkedList<>();
        // 현재 노드의 값이 0인 경우, 큐에 담고
        // 현재 노드 값이 0이 아닌 경우, 큐에 담긴 노드로 값을 옮긴 후 현재 노드를 0으로 바꾼다.
        for (int i = 0; i <= N / 2; i++) {
            if (i > 0 && map[r][c] == 0) {
                q.offer(new int[] {r, c});
            } else {
                if (q.size() > 0) {
                    int[] newPosition = q.poll();
                    
                    map[newPosition[0]][newPosition[1]] = map[r][c];
                    map[r][c] = 0;
                    q.offer(new int[] {r, c});
                }
            }
            
            for (int j = 0; j < 4; j++) {
                r += dr[j];
                c += dc[j];
                
                while (Math.abs(r - N / 2) <= i && Math.abs(c - N / 2) <= i) {
                    if (map[r][c] == 0) {
                        q.offer(new int[] {r, c});
                    } else {
                        if (q.size() > 0) {
                            int[] newPosition = q.poll();
                            
                            map[newPosition[0]][newPosition[1]] = map[r][c];
                            map[r][c] = 0;
                            q.offer(new int[] {r, c});
                        }
                    }
                    
                    r += dr[j];
                    c += dc[j];
                }
                
                if (j < 3) {
                    r -= dr[j];
                    c -= dc[j];
                }
            }
        }

        checkExplosiveMarbles();
    }
    
    // 연속된 같은 값을 가진 구슬이 4개 이상인 경우가 있는지 확인
    static void checkExplosiveMarbles() {
        int r = N / 2;
        int c = N / 2;

        boolean flag = false;
        
        // 이전 구슬의 숫자와 현재 구슬의 숫자가 같은 경우, 큐에 담는다.
        // 이전 구슬의 숫자와 현재 구슬의 숫자가 다르면서 이전 구슬의 숫자가 연속돼서 4개 이상이 된 경우,
        // 큐에 담긴 이전 구슬들을 폭발시킨 뒤, 현재 구슬를 큐에 담는다.
        Queue<int[]> q = new LinkedList<>();
        int prevMarbleNumber = 0;
        for (int i = 0; i <= N / 2; i++) {
            if (prevMarbleNumber == map[r][c]) {
                q.offer(new int[] {r, c});
            } else {
                if (q.size() < 4) {
                    q.clear();
                    prevMarbleNumber = map[r][c];
                    q.offer(new int[] {r, c});
                } else {
                    flag = true;
                    explodeMarbles(q, prevMarbleNumber);
                }
            }
            
            for (int j = 0; j < 4; j++) {
                r += dr[j];
                c += dc[j];
                
                while (Math.abs(r - N / 2) <= i && Math.abs(c - N / 2) <= i) {
                    if (prevMarbleNumber == map[r][c]) {
                        q.offer(new int[] {r, c});
                    } else {
                        if (q.size() < 4) {
                            q.clear();
                            prevMarbleNumber = map[r][c];
                            q.offer(new int[] {r, c});
                        } else {
                            flag = true;
                            explodeMarbles(q, prevMarbleNumber);
                        }
                    }
                    
                    r += dr[j];
                    c += dc[j];
                }
                
                if (j < 3) {
                    r -= dr[j];
                    c -= dc[j];
                }
            }
        }
        
        if (flag) {
            moveMarbles();
        } else {
        	makeGroup();
        }
    }
    
    static void makeGroup() {
        int r = N / 2;
        int c = N / 2;
        
        Queue<Integer> q = new LinkedList<>();
        int prevMarbleNumber = 0;
        int count = 0;
        
        // 이전 구슬의 숫자와 현재 구슬의 숫자가 같다면 count
        // 다른 경우, 큐에 이전 구슬의 숫자가 연속된 값과 숫자를 넣는다.
        for (int i = 0; i <= N / 2; i++) {
        	if (prevMarbleNumber == map[r][c]) {
    			count++;
    		} else {
    			q.offer(count);
    			q.offer(prevMarbleNumber);
    			
    			prevMarbleNumber = map[r][c];
    			count = 1;
    		}
        		
            for (int j = 0; j < 4; j++) {
                r += dr[j];
                c += dc[j];
                
                while (Math.abs(r - N / 2) <= i && Math.abs(c - N / 2) <= i) {
                	if (prevMarbleNumber == map[r][c]) {
            			count++;
            		} else {
            			q.offer(count);
            			q.offer(prevMarbleNumber);
            			
            			prevMarbleNumber = map[r][c];
            			count = 1;
            		}
                    
                    r += dr[j];
                    c += dc[j];
                }
                
                if (j < 3) {
                    r -= dr[j];
                    c -= dc[j];
                }
            }
        }

        makeMap(q);
	}
    
    static void makeMap(Queue<Integer> q) {
        int r = N / 2;
        int c = N / 2;
    	
        map[r][c] = 0;
        q.poll();
        q.poll();
        
		 for (int i = 0; i <= N / 2; i++) {
	     	if (i > 0) {
	     		if (q.isEmpty()) {
	     			map[r][c] = 0;
	     		} else {
	     			map[r][c] = q.poll();
	     		}
	     	}
	     		
	         for (int j = 0; j < 4; j++) {
	             r += dr[j];
	             c += dc[j];
	             
	             while (Math.abs(r - N / 2) <= i && Math.abs(c - N / 2) <= i) {
	            	 if (q.isEmpty()) {
	     	     			map[r][c] = 0;
	     	     	} else {
	     	     			map[r][c] = q.poll();
	     	     	}
	            	 
	                 r += dr[j];
	                 c += dc[j];
	             }
	             
	             if (j < 3) {
	                 r -= dr[j];
	                 c -= dc[j];
	             }
	         }
	     }
    }

	static void explodeMarbles(Queue<int[]> q, int marbleNumber) {
        marbleCount[marbleNumber] += q.size();
        
        while (!q.isEmpty()) {
            int[] node = q.poll();
            map[node[0]][node[1]] = 0;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        marbleCount = new int[4];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            } 
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            
            castSpells(d, s);
        }
        
        int answer = 0;
        for (int i = 1; i <= 3; i++) {
            answer += marbleCount[i] * i;
        }
        
        System.out.println(answer);
        br.close();
    }
}
