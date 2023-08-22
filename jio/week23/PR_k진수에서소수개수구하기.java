package 프로그래머스_ALGO.week23;

import java.io.*;
import java.util.*;

class PR_k진수에서소수개수구하기 {
    static int N, K;
    static String changedNum;
    
    public int solution(int n, int k) {
        N = n;
        K = k;
        
        changedNum = Integer.toString(N, K); // K진수로 변환
        String[] splitNums = changedNum.split("0"); // 조건에 맞는 덩어리들로 split
        
        int answer = 0;
        for(String num : splitNums){
            if(num.equals("")){
                continue;
            }
            
            long temp = Long.parseLong(num); // long 타입 주의
            
            if(isPrime(temp)){
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean isPrime(long num){ // 소수 여부 확인
        if(num == 1){
            return false;
        }
        if(num == 2){
            return true;
        }
        for(int i=2; i<=Math.sqrt(num); i++){ // num / 2로 하면 시간초과 납니다..
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}