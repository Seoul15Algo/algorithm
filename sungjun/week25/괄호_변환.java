package week25;

class 괄호_변환 {
    StringBuilder sb = new StringBuilder();

    public String solution(String p) {
        solve(p);

        return sb.toString();
    }

    // 주어진 조건에 따라 재귀적으로 풀이
    public void solve(String s) {
        String[] uv = splitString(s);

        if(isProper(uv[0])) {
            sb.append(uv[0]);
            if(uv[1].length() > 0) solve(uv[1]);
            return;
        }

        sb.append("(");
        solve(uv[1]);
        sb.append(")");

        for(int i = 1; i < uv[0].length()-1; i++) {
            if(uv[0].charAt(i) == '(') {
                sb.append(")");
                continue;
            }

            sb.append("(");
        }
    }

    // 문자열을 균형잡힌 괄호 문자열 두 개로 변환
    public String[] splitString(String s) {
        String u = "";
        String v = "";

        int open = 0;
        int close = 0;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') open++;
            if(s.charAt(i) == ')') close++;

            if(open == close) {
                u = s.substring(0, i+1);
                v = s.substring(i+1, s.length());
                break;
            }
        }

        return new String[] {u, v};
    }

    // 올바른 괄호 문자열인지 판별
    public boolean isProper(String s) {
        int open = 0;
        int close = 0;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') open++;
            if(s.charAt(i) == ')') close++;

            if(close > open) return false;
        }

        return true;
    }
}