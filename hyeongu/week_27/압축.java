import java.util.*;

class 압축 {
    public int[] solution(String msg) {
        ArrayList<Integer> al = new ArrayList<>();
        HashMap<String, Integer> hm = new HashMap<>();
        int count = 1;
        // A ~ Z 맵 초기화
        for(char c = 'A'; c <= 'Z'; c++) {
            hm.put(c + "", count++);
        }

        String tmp = msg;
        while(tmp.length()>0) {
            int i;
            for(i = 1; i < tmp.length(); i++) {
                // 사전에 없는 단어가 될 때까지 탐색
                // 없는 단어일 경우 리스트와 맵에 추가
                if(!hm.containsKey(tmp.substring(0, i + 1))) {
                    al.add(hm.get(tmp.substring(0, i)));
                    hm.put(tmp.substring(0, i + 1), count++);
                    break;
                }
            }
            if(i == tmp.length()) {
                al.add(hm.get(tmp));
            }
            tmp = tmp.substring(i);
        }

        int[] answer = new int[al.size()];
        for(int j = 0; j < al.size(); j++) {
            answer[j] = al.get(j);
        }
        return answer;
    }
}