package week25;

import java.util.*;

class 뉴스_클러스터링 {
    // 문자열에서 집합 만들기
    public static List<char[]> makeList(String str) {
        List<char[]> list = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                count++;
            } else {
                count = 0;
            }

            // 문자가 연속으로 있어야 count가 2가 되므로 원소 생성
            if (count == 2) {
                list.add(new char[]{str.charAt(i - 1), str.charAt(i)});

                count = 1;
            }
        }

        return list;
    }

    // 다중집합이기 때문에 동일 원소인 경우 구분을 위해 뒤에 번호를 붙인다.
    public static Map<String, Integer> makeMap(List<char[]> list) {
        Map<String, Integer> map = new HashMap<>();

        for (char[] chArr : list) {
            StringBuilder sb = new StringBuilder();

            for (char c : chArr) {
                sb.append(c);
            }

            if (!map.containsKey(sb.toString())) {
                map.put(sb.toString(), 1);
            } else {
                int newValue = map.get(sb.toString()) + 1;

                map.put(sb.toString(), newValue);

                sb.append(newValue);
                map.put(sb.toString(), 1);
            }
        }

        return map;
    }

    public int solution(String str1, String str2) {
        List<char[]> list1 = makeList(str1.toLowerCase());
        List<char[]> list2 = makeList(str2.toLowerCase());

        Map<String, Integer> map1 = makeMap(list1);
        Map<String, Integer> map2 = makeMap(list2);

        Set<String> set = new HashSet<>();

        for (String key : map1.keySet()) {
            set.add(key);
        }

        int interCount = 0;
        for (String key : map2.keySet()) {
            if (set.contains(key)) {
                interCount++;
            } else {
                set.add(key);
            }
        }

        int totalCount = set.size();

        if (totalCount == 0) {
            return 65536;
        }

        double answer = ((double)interCount / (double)totalCount) * (double)65536;
        return (int)answer;
    }
}
