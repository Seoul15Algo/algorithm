package week25;

import java.util.*;

class 캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if (cacheSize == 0) {
            answer = cities.length * 5;
            return answer;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();

        for (String city : cities) {
            // 대소문자를 구분하지 않는다.
            city = city.toLowerCase();

            // 비어있는 경우
            if (queue.isEmpty()) {
                answer += 5;

                // 삽입
                queue.offer(city);
                map.put(city, 1);

                continue;
            }

            // 캐싱
            if (map.containsKey(city)) {
                answer += 1;

                int length = queue.size();

                // 해당 데이터를 찾아 맨 뒤에 넣는다.
                for (int i = 0; i < length; i++) {
                    if (!queue.peek().equals(city)) {
                        queue.offer(queue.poll());

                        continue;
                    }

                    queue.poll();
                }

                queue.offer(city);

                continue;
            }

            // 캐싱 X
            answer += 5;

            // 캐시사이즈보다 작은 경우
            if (queue.size() < cacheSize) {
                queue.offer(city);
                map.put(city, 1);

                continue;
            }

            // 캐시사이즈와 같은 경우
            map.remove(queue.poll());
            queue.offer(city);
            map.put(city, 1);
        }

        return answer;
    }
}