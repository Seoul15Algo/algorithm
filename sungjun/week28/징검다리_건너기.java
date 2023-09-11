package week28;

public class 징검다리_건너기 {
    public int solution(int[] stones, int k) {
        int answer = 0;

        int left = 1;
        int right = 200000000;

        // 이분탐색으로 몇명까지 건너갈수 있는지 확인
        while(left <= right) {
            int mid = (left + right) / 2;

            boolean isPossible = isPossibleFriends(mid, stones, k);

            if(isPossible) {
                left = mid+1;
                continue;
            }

            right = mid-1;
        }

        answer = left-1;

        return answer;
    }

    // n명이 건너는 것이 가능한지 체크
    public boolean isPossibleFriends(int n, int[] stones, int k) {
        int interval = 0;

        for(int i = 0; i < stones.length; i++) {
            if(stones[i]-n < 0) {
                interval++;
                if(interval == k) return false;
                continue;
            }

            interval = 0;
        }

        return true;
    }
}
