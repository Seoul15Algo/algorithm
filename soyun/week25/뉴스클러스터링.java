import java.util.*;

class Solution {

    private Map<String, Integer> intersection = new HashMap<>();
    private int intersectionSize = 0;
    private int combinationSize = 0;


    public int solution(String str1, String str2) {

        List<String> divided1 = divide(str1);
        List<String> divided2 = divide(str2);

        return getJaccardSimilarity(divided1, divided2);
    }

    public List<String> divide(String str) {
        List<String> result = new ArrayList<>();
        str = str.toUpperCase();
        for (int i = 0; i < str.length() - 1; i++) {
            String sub = str.substring(i, i + 2);
            // 특수문자 컷
            if (hasSpecialCharacter(sub)){
                continue;
            }
            result.add(sub);
        }
        return result;
    }

    public boolean hasSpecialCharacter(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isAlphabetic(c)){
                return true;
            }
        }
        return false;
    }

    public int getJaccardSimilarity(List<String> divided1, List<String> divided2) {
        Map<String, Integer> str1 = addToMap(divided1);
        Map<String, Integer> str2 = addToMap(divided2);

        calculateSet(str1, str2);

        if (intersectionSize == 0 && combinationSize == 0) {
            return 65536;
        }

        int result = (int)((double) intersectionSize / combinationSize * 65536);

        return result;
    }

    public Map<String, Integer> addToMap(List<String> divided) {
        Map<String, Integer> map = new HashMap<>();

        for (String s : divided) {
            map.merge(s, 1, Integer::sum);
        }

        return map;
    }

    // 교집합 합집합 개수 추출
    public void calculateSet(Map<String, Integer> str1, Map<String, Integer> str2) {

        // 일단 str1 순회하면서
        for (String key : str1.keySet()) {
            // 교집합에 집어넣음 (최솟값이라는 전제)
            intersection.put(key, str1.get(key));
            // 합집합 합에 더함
            combinationSize += str1.get(key);
        }

        // str2를 순회
        for (String key : str2.keySet()) {
            // 만약 공통된 원소가 있다면
            if (intersection.containsKey(key)) {
                int num1 = intersection.get(key);
                int num2 = str2.get(key);

                if (num1 > num2) {  // 기존의 교집합이 더 큰 경우
                    // 교집합 갱신
                    intersection.put(key, num2);
                    // 교집합 합 갱신
                    intersectionSize += num2;
                } else {    // 기존의 교집합이 더 작은 경우
                    // 교집합 합 갱신
                    intersectionSize += num1;
                    // 합집합 합에 더했던 기존의 교집합 값을 빼줌
                    combinationSize -= num1;
                    // 합집합 합에 새로운 교집합 값을 빼줌
                    combinationSize += num2;
                }
                continue;
            }
            // 만약 공통된 원소가 없다면 -> 합집합에 추가, 교집합에서 제외
            combinationSize += str2.get(key);
            intersection.remove(key);
        }
    }
}