package week25;

import java.util.*;

class 뉴스_클러스터링 {
    public int solution(String str1, String str2) {
        int answer = 0;

        Map<String, Integer> a = new HashMap<>();
        Map<String, Integer> b = new HashMap<>();

        double intersection = 0;
        double union = 0;

        String regex = "^[a-zA-Z]*$";

        // 문자열을 영문 쌍으로 변환하여 Map에 저장
        for(int i = 0; i < str1.length()-1; i++) {
            String s = str1.substring(i, i+2);
            if(s.matches(regex)) {
                s = s.toLowerCase();

                int n = 1;

                if(a.containsKey(s)) {
                    n = a.get(s)+1;
                }

                a.put(s, n);
                union++;
            }
        }

        // 문자열을 영문 쌍으로 변환하여 Map에 저장
        for(int i = 0; i < str2.length()-1; i++) {
            String s = str2.substring(i, i+2);
            if(s.matches(regex)) {
                s = s.toLowerCase();

                int n = 1;

                if(b.containsKey(s)) {
                    n = b.get(s)+1;
                }

                b.put(s, n);
                union++;
            }
        }

        // 중복되는 key가 있다면 a와 b중에 더 작은 value를 갖는 값만큼 전체 교집합 크기에 더해준다
        // 앞서 a와 b에 영문 쌍을 넣는 과정에서 중복으로 갯수가 카운트되었기 때문에 교집합 크기만큼 합집합 크기에서 다시 빼준다
        for(String key : b.keySet()) {
            if(a.containsKey(key)) {
                int duplicated = Math.min(a.get(key), b.get(key));
                intersection += duplicated;
                union -= duplicated;
            }
        }

        if(union == 0) return 65536;

        double j = intersection / union;
        answer = (int)(j * 65536);

        return answer;
    }
}