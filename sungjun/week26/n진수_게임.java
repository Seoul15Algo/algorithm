package week26;

class n진수_게임 {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();

        // 최대 순번
        int maxNum = p + (m * t);

        StringBuilder sb = new StringBuilder();
        int num = 0;

        // 최대 순번까지 말해야 하는 숫자를 기록
        while(sb.length() < maxNum) {
            sb.append(Integer.toString(num, n));
            num++;
        }

        String fullSequence = sb.toString();

        // 튜브의 순서
        int idx = p-1;

        for(int i = 0; i < t; i++) {
            answer.append(fullSequence.charAt(idx+(i*m)));
        }

        return answer.toString().toUpperCase();     // 알파벳 대문자로
    }
}