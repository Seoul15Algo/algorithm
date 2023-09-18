import java.util.*;

class Solution {
    static List<String>[] subsets;
    static List<String> keys;

    static int index;
    static boolean isFirst = true;

    public int solution(String[][] relation) {
        int answer = 0;

        subsets = new List[relation.length];
        for(int i=0; i<subsets.length; i++) {
            subsets[i] = new ArrayList();
        }

        keys = new ArrayList<>();

        index = 0;
        for(String[] info : relation) {
            subset(info, 0, "", "");
            index++;
            isFirst = false;
        }

        List<String> candidates = new ArrayList();

        for(int i=0; i<subsets[0].size(); i++) {
            // 1. 유일성 체크
            Set<String> set = new HashSet<>();
            boolean isNotDuplicated = true;

            for(int j=0; j<subsets.length; j++) {
                String cur = subsets[j].get(i);

                if(set.contains(cur)) {
                    isNotDuplicated = false;
                    break;
                }

                set.add(cur);
            }

            if(isNotDuplicated) {
                // 2. 최소성 체크
                String key = keys.get(i);
                boolean isUnique = true;

                for(String candidate : candidates) {
                    int alreadyUse = 0;

                    for(Character c : key.toCharArray()) {
                        if(candidate.contains(Character.toString(c))) {
                            alreadyUse++;
                        }
                    }

                    if(alreadyUse == candidate.length()) { // 이미 후보키에 있는 경우
                        isUnique = false;
                        break;
                    }
                }

                if(isUnique) { // 유일성과 최소성을 모두 만족시킨 경우
                    candidates.add(key);
                    answer++;
                }
            }
        }

        return answer;
    }

    /*
     * 모든 컬럼의 조합을 탐색(모든 키 생성)
     */
    private static void subset(String[] info, int cnt, String str, String key) {
        if(cnt == info.length) {
            if(str.equals("")) {
                return;
            }
            subsets[index].add(str);

            if(isFirst) { // key의 조합은 한 번만 알면 된다.
                keys.add(key);
            }
            return;
        }

        subset(info, cnt + 1, str, key);
        subset(info, cnt + 1, str + info[cnt], key + cnt);
    }
}