package week24;

import java.util.*;

public class 튜플 {
    public int[] solution(String s) {
        List<int[]> list = new ArrayList<>();
        boolean[] check = new boolean[100001];

        for (String a : s.replace("},{", " ").replaceAll("[\\{\\}]", "").split(" ")) {
            list.add(Arrays.stream(a.split(",")).mapToInt(Integer::parseInt).toArray());
        }
        list.sort(Comparator.comparingInt(o -> o.length));

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            for (int num : list.get(i)) {
                if (!check[num]) {
                    answer[i] = num;
                    check[num] = true;
                    break;
                }
            }
        }

        return answer;
    }
}