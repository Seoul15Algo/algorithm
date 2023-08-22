package 프로그래머스_ALGO.week23;

import java.util.*;

class PR_양궁대회 {
    static int[] points;
    static int N, R, isWin, maxDiff;
    static int[] answer;
        
    public int[] solution(int n, int[] info) {
        N = 11;        
        R = n;
        points = new int[R];
        answer = new int[N];
        
        combination(0, 0, info);
        
        if(isWin == 0) {
            return new int[]{-1};
        }
        
        return answer;
    }
    
    private static void combination(int cnt, int start, int[] info) { // 중복 조합
        if(cnt == R) {
            calWinner(info);
            return;
        }    
        
        for(int i=start; i<N; i++) {
            points[cnt] = i;
            combination(cnt+1, i, info);
            points[cnt] = 0;
        }
    }
    
    private static void calWinner(int[] apeachInfo) {
        int apeach = 0;
        int ryan = 0;
        
        int[] ryanInfo = new int[N]; // 라이언 점수 정보 저장
        for(int i=0; i<R; i++) {
            ryanInfo[10 - points[i]]++;
        }
        
        for(int i = 0; i<N; i++) {
            if(apeachInfo[i] == 0 && ryanInfo[i] == 0) { // 모두 못 맞춘 경우
                continue;
            }
            
            if(apeachInfo[i] >= ryanInfo[i]) { // 어피치가 점수를 획득하는 경우
                apeach += 10 - i;
                continue;
            }
            
            if(apeachInfo[i] < ryanInfo[i]) { // 라이언이 점수를 획득하는 경우
                ryan += 10 - i;
            }
        }
        
        int diff = ryan - apeach; // 둘의 점수 차이
        
        if(diff > maxDiff) { // 라이언이 이전 보다 더 큰 차이로 이긴 경우
            isWin = 1;
            answer = ryanInfo;
            maxDiff = diff;
        }
    }
    
}