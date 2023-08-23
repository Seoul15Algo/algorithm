package com.ssafy.algo230405_Random2.sooyeon.week25;

class 괄호_변환 {
    static String answer = "";
    public String solution(String p) {

        divide(p);

        return answer;
    }
    static void divide(String str){
        if(str.equals("")) { //빈 문자열이면 리턴
            return;
        }
        boolean leftC = false; //왼쪽 문자열이 올바른 괄호 문자열인지

        int point = 0;
        int same = 0; //왼괄호,오른괄호 갯수 -: 왼괄호 많음 , 0:같음, +: 오른괄호 많음

        if(str.charAt(0)=='(') { //왼괄호 시작일 때
            leftC = true;
        }

        for(int i = 0; i < str.length(); i ++) {
            if(str.charAt(i) == '(') { //왼괄호인 경우
                same-=1;
            }
            if(str.charAt(i) == ')') { //오른괄호인 경우
                same+=1;
            }
            if(same == 0 ) { //왼괄호와 오른괄호가 같아졌다면
                point = i;
                break;
            }
        }

        if(leftC) {
            answer += str.substring(0,point+1);
            divide(str.substring(point+1,str.length()));
        }

        if(!leftC) {
            answer += '(';
            divide(str.substring(point+1,str.length()));
            answer += ')';

            // 시작, 끝 빼고 괄호 반대방향으로 뒤집기
            for(int j = 1; j < point; j++) {
                if(str.charAt(j) == '(') {
                    answer += ')';
                }
                if(str.charAt(j) == ')') {
                    answer += '(';
                }
            }
        }
    }
}