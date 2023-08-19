package week24;

import java.util.*;

class 순위검색 {
    static Map<String, ArrayList<Integer>> keyword = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for(int i = 0; i < info.length; i++) {
            String[] person = info[i].split(" ");

            // 가능한 모든 쿼리 만들어서 저장
            possibleQueries(person, "", 0);
        }

        // 중복 쿼리가 있을 수 있기 때문에 미리 한번에 정렬하는게 더 효율이 좋음
        for(String key : keyword.keySet()) {
            Collections.sort(keyword.get(key));
        }

        for(int i = 0; i < query.length; i++) {
            answer[i] = solve(query[i]);
        }

        return answer;
    }

    public int solve(String query) {
        String[] conditions = query.split(" and | ");
        int match = 0;

        // 쿼리 만들기
        String key = conditions[0]+conditions[1]+conditions[2]+conditions[3];

        // 조건에 맞는 검색 결과가 없다면
        if(!keyword.containsKey(key)) return 0;

        ArrayList<Integer> values = keyword.get(key);

        int score = Integer.parseInt(conditions[4]);

        // 조건으로 주어진 점수 이상을 만족하는 사람의 수
        match = findHigherScores(values, score);

        return match;
    }

    public void possibleQueries(String[] personInfo, String query, int cnt) {
        if(cnt == 4) {
            if(!keyword.containsKey(query)) {
                ArrayList<Integer> values = new ArrayList<>();
                values.add(Integer.parseInt(personInfo[4]));
                keyword.put(query, values);
                return;
            }

            keyword.get(query).add(Integer.parseInt(personInfo[4]));
            return;
        }

        possibleQueries(personInfo, query+"-", cnt+1);
        possibleQueries(personInfo, query+personInfo[cnt], cnt+1);
    }

    // 이분탐색으로 score 이상의 값을 가지는 값들이 몇개인지 계산
    public int findHigherScores(ArrayList<Integer> values, int score) {
        int left = 0;
        int right = values.size();

        while(left < right) {
            int mid = (left + right) / 2;
            if(values.get(mid) < score) {
                left = mid+1;
                continue;
            }

            right = mid;
        }

        return values.size() - right;
    }
}