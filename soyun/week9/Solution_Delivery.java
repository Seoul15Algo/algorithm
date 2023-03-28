package com.ssafy.algo230329_Random1.soyun.week9;


public class Solution_Delivery {

    int[] deliveries;
    int[] pickups;
    int cap;


    long delivery(int n){

        // TODO: 해당 지점을 방문해야 할 횟수를 구하여 distance 계산
        long distance = 0;
        // 남아있는 배송택배 / 수거가능 공간의 개수
        int leftDelivery = 0;
        int leftPickup = 0;

        for (int i = n; i >= 0; i--) {
            // 배송/수거가 필요한 지점일 경우
            if (deliveries[i] != 0 || pickups[i] != 0) {
                int times = 0;  // 해당 지점을 방문해야 하는 횟수
                // 해당 지점을 새로 방문해야 할 경우 (남아있는 배송택배 / 수거 가능 공간이 부족할 때)
                if (leftDelivery < deliveries[i] || leftPickup < pickups[i]){
                    // 몇 번 해당 지점을 방문해야 하는 지
                    times = Math.max(
                            (int)Math.ceil((double)(deliveries[i] - leftDelivery) / cap),
                            (int)Math.ceil((double)(pickups[i] - leftPickup) / cap)
                    );

                    // 지점에 방문한 만큼 배송/수거 가능 택배를 더해준다
                    leftDelivery = leftDelivery + cap * times;
                    leftPickup = leftPickup + cap * times;
                }
                //System.out.println(left[0] + " " + left[1]);
                // 해당 지점에서 배송/수거 가능 한 택배의 개수를 빼준다
                leftDelivery = leftDelivery- deliveries[i];
                leftPickup = leftPickup - pickups[i];
                // 지점에 방문한 횟수 만큼 왕복해야 하므로 distance 계산
                distance = distance + (i + 1) * 2L * times;
            }
        }

        return distance;
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        this.deliveries = deliveries;
        this.pickups = pickups;
        this.cap = cap;


        // 맨 끝 위치의 집부터 탐색
        return delivery(n - 1);
    }
    
}
