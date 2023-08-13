package week24;

import java.util.*;
public class 메뉴_리뉴얼 {
    static Map<String, Integer> map;

    static void combi (int c, char[] order, int depth, int index, char[] selected) {
        if (depth == c) {
            StringBuilder sb = new StringBuilder();

            for (char alpha : selected) {
                sb.append(alpha);
            }

            if (!map.containsKey(sb.toString())) {
                map.put(sb.toString(), 1);
            } else {
                map.put(sb.toString(), map.get(sb.toString()) + 1);
            }

            return;
        }

        if (index == order.length) {
            return;
        }

        combi(c, order, depth, index + 1, selected);
        selected[depth] = order[index];
        combi(c, order, depth + 1, index + 1, selected);
    }

    public String[] solution(String[] orders, int[] course) {
        map = new HashMap<>();

        for (int c : course) {
            for (String order : orders) {
                char[] temp = order.toCharArray();
                Arrays.sort(temp);
                combi(c, temp, 0, 0, new char[c]);
            }
        }

        List<String> keys = new ArrayList<>(map.keySet());
        keys.sort(Comparator.comparingInt(key -> map.get(key)).reversed());

        List<String> answer = new ArrayList<>();

        int[] courseMaxValue = new int[11];
        for (String key : keys) {
            if (map.get(key) < 2) {
                continue;
            }

            if (courseMaxValue[key.length()] > map.get(key)) {
                continue;
            }

            courseMaxValue[key.length()] = map.get(key);
            answer.add(key);
        }

        Collections.sort(answer);

        return answer.toArray(new String[answer.size()]);
    }
}