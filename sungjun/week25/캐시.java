package week25;

import java.util.*;

class 캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        Deque<String> cache = new ArrayDeque<>();
        Stack<String> tmpCache = new Stack<>();

        // 캐시 사이즈가 0이면 모든 조회당 5초씩 걸림
        if(cacheSize == 0) {
            return cities.length*5;
        }

        for(int i = 0; i < cities.length; i++) {
            // 찾으려는 데이터가 캐시에 존재한다면
            if(cache.contains(cities[i].toLowerCase())) {
                // 해당 데이터를 가장 최근 캐시로 업데이트
                while(true) {
                    String city = cache.poll();
                    if(city.equals(cities[i].toLowerCase())) {
                        cache.add(city);
                        answer++;
                        break;
                    }
                    tmpCache.push(city);
                }

                while(!tmpCache.isEmpty()) {
                    cache.addFirst(tmpCache.pop());
                }

                continue;
            }

            // 캐시에 데이터가 없다면 5초가 걸려 조회한 후 캐시에 추가
            if(cache.size() == cacheSize) {
                cache.poll();
            }

            cache.add(cities[i].toLowerCase());
            answer += 5;
        }

        return answer;
    }
}