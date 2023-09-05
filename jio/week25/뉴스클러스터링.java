import java.util.*;

class Solution {

    static Map<String, Integer> firstGroup, secondGroup;

    public int solution(String str1, String str2) {
        firstGroup = makeGroup(str1);
        secondGroup = makeGroup(str2);

        if(firstGroup.size() == 0 && secondGroup.size() == 0) { // 둘 다 공지합인 경우
            return 65536;
        }

        return calJaccard();
    }

    // 두 글자 씩 끊어 다중집합 생성
    private static Map<String, Integer> makeGroup(String str) {
        Map<String, Integer> group = new HashMap<>();

        for(int i=0; i<str.length() - 1; i++) {
            Character cur = Character.toUpperCase(str.charAt(i));
            Character next = Character.toUpperCase(str.charAt(i + 1));

            if(cur < 'A' || cur > 'Z') { // 현재 위치의 단어가 숫자 또는 특수문자인 경우
                continue;
            }

            if(next < 'A' || next > 'Z') { // 다음 위치의 단어가 숫자 또는 특수문자인 경우
                continue;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(cur);
            sb.append(next);

            String word = sb.toString();
            group.put(word, group.getOrDefault(word, 0) + 1);
        }

        return group;
    }

    private static int calJaccard() {
        double intersection = 0;
        double union = 0;

        // 1. 교집합 개수 계산
        for(String str : firstGroup.keySet()) {
            if(secondGroup.containsKey(str)) {
                intersection += Math.min(firstGroup.get(str), secondGroup.get(str));
            }
        }

        // 2. 합집합 개수 계산
        Map<String, Integer> unionCnt = new HashMap<>();

        for(String str : firstGroup.keySet()) {
            if(!secondGroup.containsKey(str)) {
                secondGroup.put(str, firstGroup.get(str));
                continue;
            }

            secondGroup.put(str, Integer.max(secondGroup.get(str), firstGroup.get(str)));
        }

        for(int cnt : secondGroup.values()) {
            union += cnt;
        }

        return (int) (intersection / union * 65536);
    }

}