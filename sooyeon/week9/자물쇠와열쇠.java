import java.lang.Math.*;
//프로그래머스 - 자물쇠와열쇠

class Solution {
    static int N,M;
    static int minR,maxR,minC,maxC;
    static int ksize;
    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;

        //중앙에 자물쇠 두고, 열쇠가 자물쇠 맨 위부터 맨 밑까지 포함 가능하도록 확장
        int len = N+2*M-2;
        int[][] lock2 = new int[len][len];
        
        for(int i = M-1; i< M+N-1; i++) {
            for(int j = M-1; j <M+N-1; j++) {
                lock2[i][j] = lock[i-(M-1)][j-(M-1)];
            }
        }
        
        for(int i = 0; i< 4; i++) {
            if(check(lock2,key,N)) {
                return true;
            }
            rotate(key); //키 90도 회전
        }
        
        //모든경우 실패
        return false;
    }
    
    public static void rotate(int[][] key){
        int len = key.length;
        int[][] temp = new int[len][len];
        
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                temp[i][j] = key[len-j-1][i];
            }
        }
          
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                key[i][j] = temp[i][j];
            }
        }
        
    }
    
    public static boolean check(int[][] lock2, int[][] key, int lockLen){ //키가 자물쇠에 맞는지 check
        int keyLen = key.length;
        int lock2Len = lock2.length;
        for(int i=0; i<lock2Len-keyLen+1; i++){
            for(int j=0; j<lock2Len-keyLen+1; j++){
                
                // lock2에 key를 더하기
                for(int k=0; k<keyLen; k++){
                    for(int l=0; l<keyLen; l++){
                        lock2[i+k][j+l] += key[k][l];
                    }
                }
                
                // 자물쇠 부분이 전부 1이 됐는지
                boolean flag = true;
                for(int k=keyLen-1; k<keyLen+lockLen-1; k++){
                    for(int l=keyLen-1; l<keyLen+lockLen-1; l++){
                        if(lock2[k][l] != 1){ // 1이 아니면 홈이 안 맞는 것임
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) break;
                }
                
                if(flag) return true;   // 전부 1이였다면 true
                
                //lock2에서 key를 뺌 (map을 원상 복귀)
                for(int k=0; k<keyLen; k++){
                    for(int l=0; l<keyLen; l++){
                        lock2[i+k][j+l] -= key[k][l];
                    }
                }
                
            }
        } 
        return false;
    }
        
}