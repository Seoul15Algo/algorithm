import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = makeRightString(p);
        return answer;
    }

    public String makeRightString(String w) {

        // 1. 입력이 빈 문자열인 경우, 빈 문자열 반환
        if (w.isEmpty()) {
            return w;
        }

        // 2. 문자열 w를 균형잡힌 괄호 문자열 u, v로 분리
        String[] divided = divide(w);

        // 3. 문자열 u가 올바른 괄호 문자열이라면
        if (isRightString(divided[0])) {
            // 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환
            return new StringBuilder(divided[0])
                    .append(makeRightString(divided[1])).toString();
        }
        // 4. 문자열 u가 올바른 괄호 문자열이 아니라면
        return tryToMakeRightString(divided[0], divided[1]);
    }

    public String[] divide(String w) {

        int idx = 0;
        int left = 0;
        int right = 0;
        for (char c : w.toCharArray()) {
            if (c == '(') {
                left++;
            }
            if (c == ')') {
                right++;
            }
            idx++;
            if (left == right) {
                break;
            }
        }
        String u = w.substring(0, idx);
        String v = w.substring(idx, w.length());

        return new String[]{u, v};
    }

    public boolean isRightString(String u) {
        Stack<Character> stk = new Stack<>();
        for (char bracket : u.toCharArray()) {
            if (stk.isEmpty()) {
                stk.push(bracket);
                continue;
            }
            if (stk.peek() == '(' && bracket == ')') {
                stk.pop();
                continue;
            }
            stk.push(bracket);
        }
        if (stk.isEmpty()) {
            return true;
        }
        return false;
    }

    public String tryToMakeRightString(String u, String v) {
        StringBuilder result = new StringBuilder();
        // 4-1. 빈 문자열에 첫번째 문자로 '('을 이어붙임
        result.append("(");
        // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어붙임
        result.append(makeRightString(v));
        // 4-3. ')'을 다시 붙임
        result.append(")");

        // 4-4. u의 첫번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향 뒤집어서 이어붙임
        String[] subResult = u.substring(1, u.length() - 1).split("");
        for (String s : subResult) {
            if (s.equals("(")){
                result.append(")");
            }
            if (s.equals(")")){
                result.append("(");
            }
        }

        // 4-5. 생성된 문자열을 반환
        return result.toString();
    }
}