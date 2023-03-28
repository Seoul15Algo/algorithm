package com.ssafy.algo230329_Random1.soyun.week9;

/*
    호다닥 풀어서 다시 올리겠습니다ㅜㅜ
 */
public class Solution_Delivery {

    int[] deliveries;
    int[] pickups;


    int delivery(int idx, int cap, int[] houses){

        if (cap < houses[idx]){
            houses[idx] = houses[idx] - cap;
            return 0;
        }
        cap = cap - houses[idx];
        houses[idx] = 0;
        return cap;
    }


    long process(int cap, int n){
         int deliveryCap = cap;
        int pickupCap = cap;
        int reached = -1;
        long distance = 0;
        for (int i = n - 1; i >= 0; i--){
            if (deliveries[i] > 0 || pickups[i] > 0){
                reached = Math.max(reached, i);
                distance = (long)(reached + 1) * 2;
            }
            deliveryCap = delivery(i, deliveryCap, deliveries);
            pickupCap = delivery(i, pickupCap, pickups);
            if (deliveryCap == 0 && pickupCap == 0){
                distance = distance + process(cap, reached + 1);
                break;
            }
            if (i == 0) {
                distance = distance + process(cap, reached + 1);
            }
        }

        return distance;
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        this.deliveries = deliveries;
        this.pickups = pickups;

        return process(cap, n);
    }
    
}
