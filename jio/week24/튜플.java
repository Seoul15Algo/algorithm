import java.util.*;

class Solution {
    public int[] solution(String s) {
        // 1. split으로 string 배열 형태로 변환
        s = s.substring(2, s.length() - 2);
        String[] groups = s.split("\\},\\{");

        // 2. 길이가 짧은 게 먼저 오도록 정렬
        Arrays.sort(groups, (s1, s2) -> Integer.compare(s1.length(), s2.length()));

        boolean[] visited = new boolean[100001]; // 방문한 숫자인지 체크할 배열
        int[] answer = new int[groups.length];

        int index = 0;
        for(String group : groups) {
            for(String numberString : group.split(",")) {
                int number = Integer.parseInt(numberString);

                if(visited[number]) { // 방문했으면 continue
                    continue;
                }

                visited[number] = true;
                answer[index++] = number; // 정답에 추가
            }
        }

        return answer;
    }


}