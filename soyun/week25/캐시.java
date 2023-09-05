import java.util.*;

class Solution {

    private static final int HIT = 1;
    private static final int MISS = 5;

    private Queue<String> q;

    public int solution(int cacheSize, String[] cities) {

        q = new ArrayDeque<>();
        // 도시 이름들을 모두 대문자로 변환
        for (int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toUpperCase();
        }

        int answer = getExecuteTime(cacheSize, cities);
        return answer;
    }

    public int getExecuteTime(int cacheSize, String[] cities) {

        int executeTime = 0;

        for (String city : cities) {
            // 캐시 적중
            if (q.contains(city)) {
                // 적중한 도시를 맨 처음으로
                q.remove(city);
                q.offer(city);
                executeTime += HIT;
                continue;
            }
            // 캐시 미스
            q.offer(city);
            executeTime += MISS;
            if (q.size() > cacheSize) {
                q.poll();
            }
        }

        return executeTime;
    }
}