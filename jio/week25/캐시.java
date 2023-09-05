import java.util.*;

class Solution {

    static Deque<String> deque;
    static int HIT = 1;
    static int MISS = 5;

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if(cacheSize == 0) {
            return MISS * cities.length;
        }

        deque = new ArrayDeque<>();

        for(String city : cities) {
            city = city.toUpperCase();

            if(!deque.contains(city) && deque.size() < cacheSize) { // 처음 캐시를 채우는 경우
                deque.offer(city);
                answer += MISS;
                continue;
            }

            if(deque.contains(city)) { // 캐시 히트
                cacheHit(city);
                answer += HIT;
                continue;
            }

            deque.removeFirst(); // 캐시 미스
            deque.offer(city);
            answer += MISS;
        }

        return answer;
    }

    /*
     * 캐시 히트일 경우 적중한 도시를 큐의 가장 뒤로 옮겨준다.
     */
    private static void cacheHit(String city) {
        Deque<String> temp = new ArrayDeque<>();

        while(!deque.isEmpty()) {
            String cur = deque.poll();

            if(cur.equals(city)) {
                continue;
            }

            temp.offer(cur);
        }

        temp.offer(city);
        deque = temp;
    }
}