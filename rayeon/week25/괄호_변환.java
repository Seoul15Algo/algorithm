package week25;

import java.util.*;

class 괄호_변환 {
    // 올바른 괄호 문자열 판별
    public static boolean isBalanced(String u) {
        int count = 0;

        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }

            /*
                count가 음수라면,
                지금까지 조회한 문자열에서 )의 개수가 (의 개수보다 많은 것이므로
                올바른 괄호 문자열이 아니다.
            */
            if (count < 0) {
                return false;
            }
        }

        return true;
    }

    public static String make(String w) {
        if (w.length() == 0) { // 빈 문자열인 경우
            return "";
        }

        // 올바른 괄호 문자열이 아닌 경우, 균형 잡힌 문자열 u,v로 나누기
        int count = 0;
        int length = 0;

        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }

            // 균형 잡힌 괄호 문자열 완성
            if (count == 0) {
                length = (i + 1);
                break;
            }
        }

        String u = w.substring(0, length);
        String v = w.substring(length, w.length());

        // u가 올바른 괄호 문자열이라면 문자열 v에 대해 1단계부터 재실행
        if (isBalanced(u)) {
            // v 수행 결과 문자열을 u에 이어 붙인 후 반환
            return u + make(v);
        }

        // u가 올바른 괄호 문자열이 아닌 경우
        String uu = u.substring(1, u.length() - 1);
        String uuu = uu.replace("(", "/").replace(")", "(").replace("/", ")");

        return "(" + make(v) + ")" + uuu;
    }

    public String solution(String p) {
        return make(p);
    }
}