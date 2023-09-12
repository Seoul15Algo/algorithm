package week27;

import java.util.*;

class 압축 {
    public int[] solution(String msg) {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 27; i++) {
            dictionary.put(String.valueOf((char)('A' + i)), (i+1));
        }

        List<Integer> list = new ArrayList<>();
        int index = 0;
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        while (index < msg.length()) {
            sb.append(msg.charAt(index));

            if (dictionary.containsKey(sb.toString())) {
                sum = dictionary.get(sb.toString());
            } else {
                list.add(sum);
                dictionary.put(sb.toString(), dictionary.size());

                sum = dictionary.get(String.valueOf(msg.charAt(index)));
                sb = new StringBuilder(String.valueOf(msg.charAt(index)));
            }

            index++;
        }
        list.add(sum);

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}