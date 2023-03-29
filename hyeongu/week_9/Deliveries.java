// 최소 거리로 이동해야 하기 때문에 가장 거리가 먼 집부터 처리
// 가면서 배달하고 오면서 수거하기 때문에 겹치는 경우 X

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        // 각각 배달과 수거의 마지막 집
        int last_deli = deliveries.length - 1;
        int last_pick = pickups.length - 1;
        
        // 전체 왕복
        while(last_deli > -1 || last_pick > -1) {
            // 배달과 수거 중에서 더 멀리간 집의 좌표
            int last = -1;
            
            // 수거
            int truck = 0;
            while(last_pick > -1 && truck < cap){
                if(pickups[last_pick] > 0){
                    last = Math.max(last_pick, last);
                    if(pickups[last_pick] > (cap - truck)){
                        pickups[last_pick] -= cap - truck;
                        truck = cap;
                        continue;
                    }
                    truck += pickups[last_pick];
                }
                last_pick--;
            }
            
            // 배달
            truck = 0;
            while(last_deli > -1 && truck < cap){
                if(deliveries[last_deli] > 0){
                    last = Math.max(last_deli, last);
                    if(deliveries[last_deli] > (cap - truck)){
                        deliveries[last_deli] -= cap - truck;
                        truck = cap;
                        continue;
                    }
                    truck += deliveries[last_deli];
                }
                last_deli--;
            }
            
            answer += 2 * (last+1);
        }
        return answer;
    }
}