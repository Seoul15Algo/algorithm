import java.util.*;

class Solution {
    static Map<String, List<Integer>> infoMap;

    public int[] solution(String[] infos, String[] querys) {
        int[] answer = new int[querys.length];

        infoMap = new HashMap<>();

        for(String info : infos) {
            makeInfoMap(info.split(" "), "", 0);
        }

        for(String key : infoMap.keySet()) { // 이분 탐색을 위해 정렬
            Collections.sort(infoMap.get(key));
        }

        for(int i=0; i<querys.length; i++) {
            String query = querys[i];
            String[] keyAndLimit = query.replaceAll(" and ", "").split(" ");
            answer[i] = findPossibleCnt(keyAndLimit[0], Integer.parseInt(keyAndLimit[1]));
        }

        return answer;
    }

    /*
     * info에서 주어진 정보들로 조합할 수 있는 가능한 모든 경우를 key로 가지고
     * value로 그 경우에 해당하는 점수들을 리스트로 지니는 해시맵을 생성
     */
    private static void makeInfoMap(String[] infos, String str, int cnt) {
        if(cnt == 4) {
            infoMap.put(str, infoMap.getOrDefault(str, new ArrayList<>()));
            infoMap.get(str).add(Integer.parseInt(infos[4]));
            return;
        }

        makeInfoMap(infos, str + infos[cnt], cnt + 1);
        makeInfoMap(infos, str + "-", cnt + 1);
    }

    private static int findPossibleCnt(String key, int limit) {
        if(!infoMap.containsKey(key)) {
            return 0;
        }

        int start = 0;
        int end = infoMap.get(key).size() - 1;

        while(start <= end) {
            int mid = (start + end) / 2;
            int cur = infoMap.get(key).get(mid);

            if(cur < limit) {
                start = mid + 1;
                continue;
            }
            end = mid - 1;
        }

        return infoMap.get(key).size() - start;
    }
}