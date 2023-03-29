package week9;

public class LockAndKey {
	static int N, M, mapSize;
    static int[][] map;
    
    public int[][] rotateKey(int[][] key) {
        int[][] newKey = new int[M][M];
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                newKey[i][j] = key[(M - 1) - j][i];
            }
        }
        
        return newKey;
    }
    
    public boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	// map 내 자물쇠 영역의 값이 모두 1이면 열 수 있다.
                if (map[M - 1 + i][M - 1 + j] != 1) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public void open(int[][] key, int mapRow, int mapCol) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                map[mapRow + i][mapCol + j] += key[i][j];
            }
        }
    }
    
    public void close(int[][] key, int mapRow, int mapCol) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                map[mapRow + i][mapCol + j] -= key[i][j];
            }
        }
    }
    
    public boolean moveKey(int[][] key) { // 열쇠 이동
        for (int row = 0; row <= (mapSize - M); row++) {
            for (int col = 0; col <= (mapSize - M); col++) {
                open(key, row, col);
                
                if (check()) { // 자물쇠가 풀렸는지 확인
                    return true;
                }
                
                close(key, row, col);
            }
        }
    
        return false;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        
        N = lock.length;
        M = key.length;
        mapSize = N + 2 * (M - 1);
        
        /* ex) 
           key :
		   [[0, 0, 0], [1, 0, 0], [0, 1, 1]]	

           lock :
           [[1, 1, 1], [1, 1, 0], [1, 0, 1]]
         	
           map :
           0 0 0 0 0 0 0
		   0 0 0 0 0 0 0
		   0 0 1 1 1 0 0
		   0 0 1 1 0 0 0
		   0 0 1 0 1 0 0
		   0 0 0 0 0 0 0
		   0 0 0 0 0 0 0
         */
        // 열쇠의 일부만으로도 자물쇠가 풀릴 수 있으므로 자물쇠에서 사방으로 (열쇠의 크기 - 1)만큼 확장한 map 생성
        map = new int[mapSize][mapSize];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i + M - 1][j + M - 1] = lock[i][j];
            }
        }
        
        for (int i = 0; i < 4; i++) {
            if (moveKey(key)) {
                answer = true;
                break;
            }
            
            key = rotateKey(key);  // 열쇠 회전
        }

        return answer;
    }
}
