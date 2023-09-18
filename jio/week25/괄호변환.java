import java.util.*;

class Solution {

    static String u, v;
    static int splitIdx;
    static StringBuilder sb = new StringBuilder();

    public String solution(String p) {
        makeUV(p);

        return sb.toString();
    }

    private static boolean isCorrect(String p) {
        Stack<Character> stack = new Stack<>();

        for(Character c : p.toCharArray()) {
            if(c == '(') {
                stack.push('(');
                continue;
            }

            if(c == ')') {
                if(stack.size() == 0) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.size() > 0 ? false : true;
    }

    private static void findIdx(String p) {
        int leftCnt = 0;
        int rightCnt = 0;

        for(int i=0; i<p.length(); i++) {
            Character c = p.charAt(i);
            if(c == '(') {
                leftCnt++;
            }

            if(c == ')') {
                rightCnt++;
            }

            if(leftCnt == rightCnt) {
                splitIdx = i + 1;
                break;
            }
        }
    }

    private static void makeUV(String p) {
        if(p.equals("")) {
            return;
        }

        findIdx(p);
        String u = p.substring(0, splitIdx);
        String v = p.substring(splitIdx, p.length());

        if(isCorrect(u)) {
            sb.append(u);
            makeUV(v);
            return;
        }

        sb.append("(");
        makeUV(v);
        sb.append(")");
        sb.append(makeReverse(u));
    }

    private static String makeReverse(String p) {
        StringBuilder temp = new StringBuilder();

        for(int i=1; i<p.length() - 1; i++) {
            Character c = p.charAt(i);
            if(c == '(') {
                temp.append(")");
                continue;
            }

            temp.append("(");
        }

        return temp.toString();
    }
}