import java.util.*;

class 뉴스클러스터링 {
    public static final int HEXADECA = 65536;

    public int solution(String str1, String str2) {
        Map<String, Integer> firstHashMap = createStringToMap(str1);
        Map<String, Integer> secondHashMap = createStringToMap(str2);

        if(firstHashMap.size() + secondHashMap.size() == 0){
            return HEXADECA;
        }

        double union = 0;
        double intersection = 0;

        for(String key : firstHashMap.keySet()){
            int first = firstHashMap.get(key);

            if(!secondHashMap.containsKey(key)){
                union += first;
                continue;
            }

            int second = secondHashMap.remove(key);

            union += Math.max(first, second);
            intersection += Math.min(first, second);
        }

        for(String key : secondHashMap.keySet()){
            int second = secondHashMap.get(key);

            union += second;
        }

        return (int)(intersection / union * HEXADECA);
    }

    public Map<String, Integer> createStringToMap (String str){
        Map<String, Integer> hm = new HashMap<>();

        for(int i = 0; i < str.length() - 1; i++){
            if(!isAlphabet(str.charAt(i))){
                continue;
            }

            if(!isAlphabet(str.charAt(i + 1))){
                i++;
                continue;
            }

            String tmp = str.substring(i, i + 2).toLowerCase();
            hm.put(tmp, hm.getOrDefault(tmp, 0) + 1);
        }

        return hm;
    }

    public boolean isAlphabet (char c){
        return (c >= 'a' && c <= 'z') ||  (c >= 'A' && c <= 'Z');
    }
}