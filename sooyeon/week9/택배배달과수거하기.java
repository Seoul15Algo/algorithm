import java.lang.Math.*;
//프로그래머스 - 택배배달과수거하기

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int endD = -1; //제일 먼 배달 지점
        int endP = -1; //제일 먼 수거 지점
        
        for(int i = n-1; i >= 0; i--) {
            if(deliveries[i] > 0) {
                endD = i;
                break;
            }
        }
        for(int i = n-1; i >= 0; i--) {
            if(pickups[i] > 0) {
                endP = i;
                break;
            }
        }   
        
        System.out.println(endD+" "+endP);
        
        while(endD >= 0 || endP >= 0) { //배달&수거 할 택배가 없을 때 까지

            int distance = Math.max(endD,endP)+1; //제일 먼 배달 지점과 제일 먼 수거 지점 중 더 큰 값
            answer += 2*distance;
            
            //물류센터 -> 출발
            int count = cap;
            for(int i = endD; i>=0; i--) {
                if(deliveries[i] > 0 && deliveries[i]>=count) {
                    deliveries[i]-= count;
                    count = 0;
                }else if(deliveries[i] > 0 && deliveries[i] < count) {
                    count-= deliveries[i];
                    deliveries[i] = 0;
                }
                
                if(count == 0) break;
            }
            
            //도착지 -> 물류센터
            count = cap;
            for(int i = endP; i>=0; i--) {
                if(pickups[i] > 0 && pickups[i]>=count) {
                    pickups[i]-= count;
                    count = 0;
                }else if(pickups[i] > 0 && pickups[i] < count) {
                    count-= pickups[i];
                    pickups[i] = 0;
                }
                if(count == 0) break;
            }
            
            //endD, endP값 갱신
            for(int i = endD; i>=0; i--) {
                if(deliveries[i]==0) {
                    endD--;
                }
                if(deliveries[i]>0) {
                    break;
                }
            }
            
            for(int i = endP; i>=0; i--) {
                if(pickups[i]==0) {
                    endP--;
                }
                if(pickups[i]>0) {
                    break;
                }
            }
            
        }

        return answer;
    }
}