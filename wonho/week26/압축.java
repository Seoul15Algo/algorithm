import java.util.*;

class 압축 {
    private Map<String, Integer> indexes = new HashMap<>();
    private int currentIndex = 1;
    
    public int[] solution(String msg) {
        initMap();

        List<Integer> result = new ArrayList<>();
        int start = 0;

        while (start < msg.length()) {
            int end = start + 1;
            while (end <= msg.length()) {
                String sub = msg.substring(start, end);
                if (indexes.get(sub) == null) {
                    indexes.put(sub, currentIndex);
                    currentIndex++;
                    result.add(indexes.get(msg.substring(start, end - 1)));
                    start = end - 1;
                    break;
                }
                end++;
            }
            if (start != end - 1) {
                start++;
                continue;
            }

            if (start == msg.length() - 1 || indexes.get(msg.substring(start)) != null) {
                result.add(indexes.get(msg.substring(start)));
                start++;
            }
        }
        if (result.size() == 0) {
            result.add(indexes.get(msg.substring(0)));
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    public void initMap() {
        for (char c = 'A'; c <= 'Z'; c++) {
            indexes.put(String.valueOf(c), currentIndex);
            currentIndex++;
        }
    }
}