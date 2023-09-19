package com.ssafy.algo230405_Random2.sooyeon.week28;

class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200000000;

        while(true) {
            int mid = (left+right)/2;
            if(left > right) {
                return left-1;
            }

            if(isPossible(mid,stones,k)) {
                left = mid+1;
            }else {
                right = mid-1;
            }


        }
    }

    static boolean isPossible(int num, int[] stones, int k) {
        //num보다 이하인게 연속으로 k개 있으면 안된다.
        int temp = 0;
        for(int i = 0; i < stones.length; i++) {
            if(stones[i] < num) {
                temp ++;
            }else {
                temp = 0;
            }

            if(temp == k ) {
                return false;
            }
        }
        return true;
    }

}