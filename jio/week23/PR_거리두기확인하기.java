package 프로그래머스_ALGO.week23;

import java.util.*;

class PR_거리두기확인하기 {
    
    static int[][] grid;
    static int N;
    static List<Pair> starts;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    static class Pair{
        int x, y, person;
        
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(String[][] places) {
        N = 5;
        int[] answer = new int[N];
        
        for(int i=0; i<N; i++){
            grid = new int[N][N]; // 1: 사람, 0: 책상, -1: 파티션
            starts = new ArrayList<>();
            
            for(int j=0; j<N; j++){
                for(int k=0; k<N; k++){
                    char c = places[i][j].charAt(k);
                    
                    if(c == 'P') {
                        grid[j][k] = 1;
                        starts.add(new Pair(j, k));
                    }
                    
                    if(c == 'O') {
                        grid[j][k] = 0;
                    }
                    
                    if(c == 'X') {
                        grid[j][k] = -1;
                    }
                }
            }
            answer[i] = isValid();
        }
        
        return answer;
    }
    
    /*
    현재 사람이 위치한 곳 혹은 사람이 위치한 곳에 동서남북에 방문 표시를 한 후 재방문 한 경우가 발생하는 경우
    거리두기가 지켜지지 않은 경우이다.
    */
    private static int isValid() {
        boolean[][] visited = new boolean[N][N];
        
        for(int i=0; i<starts.size(); i++) {
            int x = starts.get(i).x;
            int y = starts.get(i).y;
            visited[x][y] = true;
            
            for(int d=0; d<4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if(!inRange(nx, ny) || grid[nx][ny] == -1) { // 격자를 벗어나거나 가로막힌 경우
                    continue;
                }
                
                if(visited[nx][ny]) { // 거리두기가 지켜지지 않은 경우
                    return 0;
                }
                
                visited[nx][ny] = true;
            }
            
        }    
        return 1;
    }
    
    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
    
}