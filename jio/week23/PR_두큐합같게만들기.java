package 프로그래머스_ALGO.week23;

import java.util.*;

class PR_두큐합같게만들기 {
    static Queue<Integer> que1 = new ArrayDeque();
    static Queue<Integer> que2 = new ArrayDeque();
    long sum1, sum2;
    int swapCnt; // 스왑 횟수
    
    public int solution(int[] queue1, int[] queue2) {
        
        for(int i=0; i<queue1.length; i++){
            que1.offer(queue1[i]);
            que2.offer(queue2[i]);
            
            sum1 += queue1[i]; // 첫 번째 큐의 합
            sum2 += queue2[i]; // 두 번쨰 큐의 합
        }
        
        /*
         * 큐의 합이 큰 쪽에서 꺼내 작은 쪽으로 넣어주는 걸 두 큐 합이 같아질 떄 까지 반복
         */
        while(sum1 != sum2){
            if(swapCnt > queue1.length * 2 + 1){
                swapCnt = -1;
                break;
            }
            
            if(sum1 > sum2){ // 첫 번쨰 큐의 합이 더 큰 경우
                int temp = que1.poll();
                sum1 -= temp;
                
                que2.offer(temp);
                sum2 += temp;
                
                swapCnt++;
                continue;
            }
            
            if(sum1 < sum2){ // 두 번째 큐의 합이 더 큰 경우
                int temp = que2.poll();
                sum2 -= temp;
                
                que1.offer(temp);
                sum1 += temp;
            }
            
            swapCnt++;
        }
        
        return swapCnt;
    }
}