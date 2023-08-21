package com.ssafy.algo230405_Random2.sooyeon.week25;

import java.util.*;

class 캐시 {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) {
            return 5*cities.length;
        }
        int answer = 0;
        HashMap<String,Integer> map = new HashMap<>();
        PriorityQueue<City> pq = new PriorityQueue<>();

        for(int i = 0; i< cities.length; i ++) {
            //현재 문자열
            String cur = cities[i].toUpperCase();

            if(map.size() < cacheSize) { //맵에 들어있는 개수가 캐시 사이즈보다 작은 경우 -> map과 pq에 넣기
                if(map.containsKey(cur)) { //이미 들어있는 값이면 +1만해줌
                    answer +=1;
                    map.put(cur,i);
                    pq.offer(new City(cur,i));
                    continue;
                }
                map.put(cur,i);
                pq.offer(new City(cur,i));
                answer += 5;
                continue;
            }

            //캐시에 있는지 검사 -> 있으면 answer+1과 pq에 값 넣고 map에 업데이트
            if(map.containsKey(cur)) {
                map.put(cur, i);
                pq.offer(new City(cur,i));
                answer += 1;
                continue;
            }

            //없으면 -> map과 pq에 값 업데이트 하고 pq 중에 현재와 값 맞는것까지 빼주고 map에서도 삭제, answer+5
            map.put(cur, i);
            pq.offer(new City(cur,i));
            answer += 5;
            while(true) {
                City now = pq.poll();
                if(map.get(now.city) == now.seq) {
                    map.remove(now.city);
                    break;
                }
            }

        }

        return answer;
    }
    static class City implements Comparable<City>{
        String city;
        int seq;
        public City(String city, int seq) {
            this.city = city;
            this.seq = seq;
        }
        @Override
        public int compareTo(City o) { //작은거 -> 큰거 순 정렬
            return Integer.compare(this.seq, o.seq);
        }
    }
}
