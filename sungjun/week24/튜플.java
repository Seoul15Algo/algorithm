package week24;

import java.util.*;

class 튜플 {
    // 배열의 길이 순으로 정렬할 Comparator 구현
    public class CustomComparator implements Comparator<String[]> {
        @Override
        public int compare(String[] s1, String[] s2) {
            return Integer.compare(s1.length, s2.length);
        }
    }

    public ArrayList<Integer> solution(String s) {
        boolean[] check = new boolean[1000001];     // 튜플의 원소는 100,000 이하의 자연수
        ArrayList<Integer> answer = new ArrayList<>();
        ArrayList<String[]> tuple = new ArrayList<>();

        boolean flag = false;
        StringBuilder sb = new StringBuilder();

        // 입력받은 문자열 파싱
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(c == '{') {
                flag = true;
                continue;
            }

            if(c == '}') {
                if(flag) {
                    tuple.add(sb.toString().split(","));
                    sb = new StringBuilder();
                    flag = false;
                }
            }

            if(flag) {
                if(c == ' ') continue;
                sb.append(s.charAt(i));
            }
        }

        // 원소 개수 순서로 정렬
        Collections.sort(tuple, new CustomComparator());

        // 1개짜리 집합은 튜플의 첫번째 원소
        // 2 이상의 n개짜리 집합부터는 새로 등장한 수가 튜플의 n번째 원소
        // 즉, 이번 집합에서 이전까지 등장하지 않은 새로운 수를 추가하면 됨
        for(int i = 0; i < tuple.size(); i++) {
            String[] nums = tuple.get(i);

            for(String num : nums) {
                int n = Integer.parseInt(num);
                if(check[n]) continue;
                answer.add(n);
                check[n] = true;
            }
        }

        return answer;
    }
}