import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> list = new ArrayList<>(); //출력 리스트
        Map<String,Integer> map = new HashMap<>();

        //A~Z 맵에 넣기
        for(int i = 'A'; i <= 'Z'; i ++) {
            map.put(String.valueOf((char)i), i-64);
        }
        int number = 27; //Z다음값

        for(int i = 0; i < msg.length(); i++) {
            //없는 문자열까지 찾기
            String str = String.valueOf(msg.charAt(i));

            for(int j = i+1; j < msg.length(); j++) {
                str+= msg.charAt(j);
                if(map.get(str) == null) {
                    map.put(str, number++);
                    break;
                }
                i++;
            }
            //없는문자열까지 찾았으므로 한글자 잘라줌
            String str1 = str.substring(0,str.length()-1);

            //마지막이 아닌데 자른 문자열이 빈게 아닌경우
            if(i != msg.length()-1 && !str1.equals("")) {
                list.add(map.get(str1));
            }

            //마지막인데 자른 문자열이 빈게 아닌경우
            if(i == msg.length()-1 && !str.equals("")) {
                list.add(map.get(str));
            }

        }


        int[] answer = new int[list.size()];
        for(int i = 0; i<list.size(); i ++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}