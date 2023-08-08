package week24;

import java.util.*;

class 후보키 {
    static int result;
    static Set<String> candidateKey = new HashSet<>();
    static boolean[] isCandidateKey;     // 후보키 생성에 쓰일 키 조합

    public int solution(String[][] relation) {
        result = 0;
        isCandidateKey = new boolean[relation[0].length];

        for(int i = 1; i <= relation[0].length; i++) {
            combi(0, 0, i, relation);   // 가능한 모든 후보키 조합 테스트
        }

        return result;
    }

    public void combi(int cnt, int next, int target, String[][] relation) {
        if(cnt == target) {
            StringBuilder ck = new StringBuilder();

            // 유일성 검증
            if(checkDuplicate(relation)) {
                for(int i = 0; i < isCandidateKey.length; i++) {
                    if(isCandidateKey[i]) ck.append(i);  // 후보키로 활용된 컬럼의 인덱스로 후보키 생성
                }

                candidateKey.add(ck.toString());    // 후보키 리스트에 넣음 (후보키가 될 수 없어도 이후 후보키들의 최소성 검증을 위해 넣음)
                if(checkMinimality(ck.toString())) result++;       // 최소성을 만족한다면 result++
            }
            return;
        }

        for(int i = next; i < isCandidateKey.length; i++) {
            if(isCandidateKey[i]) continue;

            isCandidateKey[i] = true;
            combi(cnt+1, i+1, target, relation);
            isCandidateKey[i] = false;
        }
    }

    // 최소성 검증
    public boolean checkMinimality(String columnIndexes) {
        char[] cs = columnIndexes.toCharArray();

        for(int i = 0; i < cs.length; i++) {
            StringBuilder sb = new StringBuilder();

            // 후보키를 이루고 있는 키 중에 하나씩을 다 빼본다
            for(int j = 0; j < cs.length; j++) {
                if(i == j) continue;
                sb.append(cs[j]);
            }

            // 최소성을 만족하지 않을 경우
            if(candidateKey.contains(sb.toString())) return false;
        }

        return true;
    }

    // 유일성 검증
    public boolean checkDuplicate(String[][] relation) {
        Set<String> duplicate = new HashSet<>();

        for(int i = 0; i < relation.length; i++) {
            StringBuilder sb = new StringBuilder();

            for(int j = 0; j < relation[0].length; j++) {
                if(isCandidateKey[j]) {
                    sb.append(relation[i][j]);
                }
            }

            duplicate.add(sb.toString());
        }

        if(duplicate.size() == relation.length) return true;

        return false;
    }
}