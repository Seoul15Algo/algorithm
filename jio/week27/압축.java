package week27;

import java.io.*;
import java.util.*;

class 압축 {
    static Map<String, Integer> words;
    static List<Integer> answer;

    public int[] solution(String msg) {
        words = new HashMap<>();
        answer = new ArrayList<>();

        makeDict();

        char[] msgArr = msg.toCharArray();
        boolean isLast = false;

        for(int i=0; i<msgArr.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(msgArr[i]);

            if(i == msgArr.length - 1) { // 시작 위치가 마지막 인덱스에 도달한 경우
                answer.add(words.get(String.valueOf(msgArr[i])));
                break;
            }

            int cnt = 0; // 늘린 글자 수
            for(int j=i+1; j<msgArr.length; j++) { // 한 글자씩 늘려가며 사전에 있는 단어인지 확인
                sb.append(msgArr[j]);
                String now = sb.toString();

                if(!words.containsKey(now)) { // 사전에 없는 단어인 경우
                    words.put(now, words.size() + 1);
                    answer.add(words.get(now.substring(0, now.length() - 1)));
                    break;
                }

                if(j == msgArr.length - 1) { // 한 글자씩 늘려가다 마지막 문자에 도달한 경우
                    isLast = true;
                }

                cnt++;
            }

            if(isLast) { // 한 글자씩 늘려가다 마지막 문자에 도달한 경우 "시작 지점 ~ 마지막 글자"를 answer에 추가
                answer.add(words.get(sb.toString()));
            }

            i += cnt; // 늘린 글자 수 만큼 다음 탐색 시작 위치 조정
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    private static void makeDict() { // 사전 초기화
        char cur = 'A';
        int idx = 1;
        while(true) {
            words.put(String.valueOf(cur), idx);
            if(cur == 'Z') {
                break;
            }
            cur++;
            idx++;
        }
    }
}