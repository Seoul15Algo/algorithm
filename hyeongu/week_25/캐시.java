import java.util.*;

class 캐시 {
    public int solution(int cacheSize, String[] cities) {
        List<String> al = new ArrayList<>();
        int answer = cities.length * 5;

        for(int i = 0; i < cities.length; i++) {
            String now = cities[i].toLowerCase();

            if(al.contains(now)){
                al.remove(now);
                answer -= 4;
            }
            al.add(now);

            if(al.size() > cacheSize){
                al.remove(0);
            }
        }

        return answer;
    }
}