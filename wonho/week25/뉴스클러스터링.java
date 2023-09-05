import java.util.*;

public class 뉴스클러스터링 {
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        Map<String, Integer> setA = new HashMap<>();
        Map<String, Integer> setB = new HashMap<>();
        Map<String, Integer> cross = new HashMap<>();
        Map<String, Integer> sum = new HashMap<>();
        
        int str1Len = str1.length();
        for (int i = 0; i < str1Len - 1; i++) {
            String substr1 = str1.substring(i, i+2);
            if (substr1.matches("^[a-zA-Z]+$")) {
                setA.put(substr1, setA.getOrDefault(substr1, 0) + 1);
            }
        }
        int str2Len = str2.length();
        for (int i = 0; i < str2Len - 1; i++) {
            String substr2 = str2.substring(i, i+2);
            if (substr2.matches("^[a-zA-Z]+$")) {
                setB.put(substr2, setB.getOrDefault(substr2, 0) + 1);
            }
        }
        
        for (Map.Entry<String, Integer> entry: setA.entrySet()) {
            sum.put(entry.getKey(), entry.getValue());
            Integer bValue = setB.get(entry.getKey());
            
            if (bValue == null) {
                continue;
            }
            cross.put(entry.getKey(), Math.min(entry.getValue(), bValue));
        }
        
        for (Map.Entry<String, Integer> entry: setB.entrySet()) {
            Integer value = sum.get(entry.getKey());
            
            if (value == null) {
                sum.put(entry.getKey(), entry.getValue());
                continue;
            }
            sum.put(entry.getKey(), Math.max(entry.getValue(), value));
        }
        
        double crossTotal = 0;
        double sumTotal = 0;
        for (Integer c : cross.values()) {
            crossTotal += c;
        }
        for (Integer s: sum.values()) {
            sumTotal += s;
        }
        
        if (sumTotal == 0) {
            return 65536;
        }
        
        return (int) ((crossTotal / sumTotal) * 65536);
    }
}