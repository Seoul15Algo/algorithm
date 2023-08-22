package week24;

import java.util.*;

public class 후보키 {
    static Map<String, List<Integer>> candidateKeys;
    static int columnSize, rowSize;
    static String[][] R;
    static int answer;

    static public void combi(int depth, int index, int n, Integer[] selected) {
        if (depth == n) {
            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < rowSize; i++) {
                StringBuilder sb = new StringBuilder();

                for (int column : selected) {
                    sb.append(R[i][column]);
                }

                if (map.containsKey(sb.toString())) {
                    return;
                } else {
                    map.put(sb.toString(), 1);
                }
            }

            StringBuilder sb = new StringBuilder();

            for (int column : selected) {
                sb.append(String.valueOf(column));
            }

            List<Integer> list = new ArrayList<>(Arrays.asList(selected));
            for (String key : candidateKeys.keySet()) {
                if (list.containsAll(candidateKeys.get(key))) {
                    return;
                }
            }

            candidateKeys.put(sb.toString(), list);

            return;
        }

        if (index == columnSize) {
            return;
        }

        combi(depth, index + 1, n, selected);
        selected[depth] = index;
        combi(depth + 1, index + 1, n, selected);
    }

    public int solution(String[][] relation) {
        answer = 0;
        R = relation;
        candidateKeys = new HashMap<>();

        columnSize = relation[0].length;
        rowSize = relation.length;

        for (int i = 1; i <= columnSize; i++) {
            combi(0, 0, i, new Integer[i]);
        }

        answer = candidateKeys.size();
        return answer;
    }
}