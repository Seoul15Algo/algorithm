package week24;

import java.util.*;

public class 순위_검색 {
    static Map<String, List<Integer>> infoMap;

    static void subset(String key, int depth, String[] info) {
        if (depth == 4) {
            if (infoMap.containsKey(key)) {
                infoMap.get(key).add(Integer.parseInt(info[4]));
            } else {
                List<Integer> value = new ArrayList<>();

                value.add(Integer.parseInt(info[4]));
                infoMap.put(key, value);
            }

            return;
        }

        subset(key + "-", depth + 1, info);
        subset(key + info[depth], depth + 1, info);
    }

    static int search(String condition, int score) {
        if (!infoMap.containsKey(condition)) {
            return 0;
        }

        List<Integer> value = infoMap.get(condition);

        int L = 0;
        int R = value.size() - 1;
        while (L <= R) {
            int mid = (L + R) / 2;

            if (value.get(mid) < score) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return value.size() - L;
    }

    public int[] solution(String[] info, String[] query) {
        infoMap = new HashMap<>();

        for (int i = 0; i < info.length; i++) {
            subset("", 0, info[i].split(" "));
        }

        List<String> keys = new ArrayList<>(infoMap.keySet());
        for (int i = 0; i < keys.size(); i++) {
            List<Integer> value = infoMap.get(keys.get(i));
            Collections.sort(value);
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] temp = query[i].replace(" and ", "").split(" ");
            int score = Integer.parseInt(temp[1]);

            answer[i] = search(temp[0], score);
        }

        return answer;
    }
}