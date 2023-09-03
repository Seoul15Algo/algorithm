package week27;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class 압축 {
    public ArrayList<Integer> solution(String msg) {
        ArrayList<Integer> answer = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();

        // 사전 초기화
        for(int i = 0; i < 26; i++) {
            char c = (char)('A'+i);
            dict.put(Character.toString(c), i+1);
        }

        int start = 0;      // 시작 인덱스
        int idx = 27;       // 사전의 다음 인덱스

        // 메시지 압축
        for(int i = 1; i < msg.length()+1; i++) {
            // 시작 인덱스를 고정한 상태로 범위를 늘려가며 사전에 포함되어있는 문자열인지 확인
            if(dict.containsKey(msg.substring(start, i))) continue;

            // 시작 인덱스부터 현재 인덱스까지의 문자열이 사전에 없는 값이라면 직전 인덱스까지의 문자열을 answer에 추가
            // 사전에 새로운 문자열 등록
            answer.add(dict.get(msg.substring(start, i-1)));
            dict.put(msg.substring(start, i), idx++);
            start = i-1;
        }

        // 단어의 끝에 도달했을 경우 마지막 문자까지의 문자열을 따로 검사
        answer.add(dict.get(msg.substring(start, msg.length())));

        return answer;
    }
}
