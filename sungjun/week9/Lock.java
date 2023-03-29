package week9;

import java.util.*;

class Lock {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        
        for(int k = 0; k < 4; k++) {           
            int n = lock[0].length;
            int newKeyLength = n + (n-1)*2;		// 열쇠 길이 재설정
            int[][] newKey = new int[newKeyLength][newKeyLength];
            
            // 새로운 열쇠 생성
            for(int i = 0; i < key.length; i++) {
                for(int j  = 0; j < key.length; j++) {
                    newKey[i][j] = key[i][j];
                }
            }
            
            // 열쇠를 한칸씩 옮기며 모든 경우를 체크
            for(int i = 0; i < newKeyLength-n+1; i++) {
                for(int j = 0; j < newKeyLength-n+1; j++) {
                    if(move(i, j, newKey, lock)) {                    	
                        return true;	// 자물쇠를 풀 수 있다면 결과값 반환
                    }
                }
            }
            
            // 열쇠 회전
            rotate(key);
        }
        
        // 모든 경우의 수를 고려했음에도 자물쇠가 풀리지 않는다면 false 리턴
        return answer;
    }
    
    // 열쇠를 (r, c)만큼 옮기는 함수
	private static boolean move(int r, int c, int[][] key, int[][] lock) {
		int[][] temp = new int[key.length][key.length];
		
		for (int i = 0; i < temp.length; i++) {
			temp[i] = Arrays.copyOf(key[i], temp.length);
		}
		
		if(r != 0 && c != 0) {
	        for(int i = key.length-r-1; i >= 0; i--) {
	            for(int j = key.length-c-1; j >= 0; j--) {
	                temp[i+r][j+c] = key[i][j];
	                temp[i][j] = 0;
	            }
	        }
		}
         
		// 자물쇠가 풀리는지 체크
        if(match(temp, lock)) {
            return true;
        }
        
        return false;
    }
    
	// 열쇠 회전
    private static void rotate(int[][] key) {
        int n = key[0].length;
        
        int[][] temp = new int[n][n];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                temp[j][n-1-i] = key[i][j];
            }
        }
        
        for(int i = 0; i < n; i++) {
            key[i] = Arrays.copyOf(temp[i], n);
        }
    }   
    
    // 자물쇠가 풀리는지 체크
    private static boolean match(int[][] movedKey, int[][] lock) {
        int n = lock[0].length;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
            	// 비어있는 홈이 있거나, 돌기끼리 부딪히는 경우
                if(movedKey[i+n-1][j+n-1] + lock[i][j] != 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
