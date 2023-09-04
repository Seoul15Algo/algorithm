import java.util.*;

public class 캐시 {
    // cacheSize: 캐시 크기, 0 <= x <= 30
    // cities: 도시 배열, len(cities) <= 100,000
    // len(cities[i]) <= 20
    // LRU
    class Data {
        private String city;
        private int time;
        
        public Data(String city) {
            this.city = city;
            this.time = 0;
        }
        
        public void initTime() {
            this.time = 0;
        }
        
        public void upTime() {
            this.time++;
        }
        
        public boolean equals(String city) {
            return this.city.equalsIgnoreCase(city);
        }
    }
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<Data> cache = new ArrayList<>();
        
        for (String city: cities) {
            int currentSize = cache.size();
            
            boolean hit = false;
            for (Data data: cache) {
                if (data.equals(city)) {
                    data.initTime();
                    answer++;
                    hit = true;
                } else {
                    data.upTime();
                }
            }
            
            if (!hit) {
                if (cache.size() >= cacheSize) {
                    int maxTime = 0;
                    Data maxData = null;
                    for (Data data: cache) {
                        if (data.time > maxTime) {
                            maxTime = data.time;
                            maxData = data;
                        }
                    }
                    cache.remove(maxData);
                }
                answer += 5;
                if (cacheSize > 0) {
                    cache.add(new Data(city));
                }
            }
        }
        
        return answer;
    }
}