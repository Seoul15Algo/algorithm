class Solution {
    static int N, M;
    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        
        // 4방향
        for(int k = 0; k < 4; k++){
            // 주어진 key를 확장해서 key가 lock의 바깥으로 나가는 경우까지 확인
            int[][] now = expand(key);
            
            // 확장한 key와 lock으로 만들 수 있는 모든 경우의 수를 확인
            // 가능한 경우 return true
            for(int i = 0; i < N + M - 1; i++){
                for(int j = 0; j < N + M - 1; j++){
                    if(check(now, lock, i, j)){
                        return true;
                    }
                }
            }
            // key 회전
            key = rotate(key);
        }
        return false;
    }
    
    // 현재 상태에서 열쇠와 자물쇠가 맞는지 판단하는 함수
    static boolean check(int[][] now, int[][] lock, int x, int y){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(lock[i][j] == now[i + x][j + y]){
                    return false;
                }
            }
        }
        return true;
    }
    
    // 주어진 배열을 확장하는 함수
    static int[][] expand(int[][] arr){
        int[][] expand_arr = new int[M + 2 * (N - 1)][M + 2 * (N - 1)];
        
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                expand_arr[N - 1 + i][N - 1 + j] = arr[i][j];
            }
        }
        return expand_arr;
    }
    
    // 주어진 배열을 회전하는 함수
    static int[][] rotate(int[][] arr){
        int[][] rotate_arr = new int[M][M];
        
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                rotate_arr[j][M - 1 - i] = arr[i][j];
            }
        }
        return rotate_arr;
    }
}