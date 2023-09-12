import java.util.Arrays;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = {};
        Arrays.sort(files,(a,b) -> {
            //HEAD 문자열 비교
            //문자열이 같다가 먼저 끝난경우 -> 먼저 끝난애가 먼저

            int lenA = a.length();
            int lenB = b.length();

            for(int i = 0; i < Math.min(lenA,lenB); i++) {
                char cA = a.charAt(i);
                char cB = b.charAt(i);

                boolean isAlphabetA = false;
                boolean isAlphabetB = false;
                if(('a'<=cA && cA<='z') || ('A'<=cA && cA<='Z')) {
                    isAlphabetA = true;
                }
                if(('a'<=cB && cB<='z') || ('A'<=cB && cB<='Z')) {
                    isAlphabetB = true;
                }

                boolean isNumA = false;
                boolean isNumB= false;
                if('0'<=cA && cA<='9') {
                    isNumA = true;
                }
                if('0'<=cB && cB<='9') {
                    isNumB = true;
                }

                //둘다 문자 - 알파벳
                if(isAlphabetA && isAlphabetB){
                    if(Character.toUpperCase(cA) == Character.toUpperCase(cB)) {
                        continue;
                    }
                    if(Character.toUpperCase(cA) < Character.toUpperCase(cB)) {
                        return -1;
                    }
                    if(Character.toUpperCase(cA) > Character.toUpperCase(cB)) {
                        return 1;
                    }
                }
                //숫자 vs 문자 인 경우 -> 문자가 이김?
                if(isNumA && !isNumB) {
                    return -1;
                }
                if(!isNumA && isNumB) {
                    return 1;
                }

                //문자인데 공백, 마침표 부호가 포함된 경우
                if(!isNumA && !isNumB && cA!=cB) {
                    return cA-cB;
                }


                //숫자 비교
                if(isNumA && isNumB) {
                    //다음 문자가 숫자가 아닐때까지 이어 붙힘
                    String numA = "";
                    String numB = "";

                    for(int j =i; j< a.length(); j++) {
                        char A = a.charAt(j);
                        if('0' <= A && A <= '9') {
                            numA += A;
                            if(numA.length() == 5) {
                                break;
                            }
                        }else {
                            break;
                        }
                    }

                    for(int j =i; j< b.length(); j++) {
                        char B = b.charAt(j);
                        if('0' <= B && B <= '9') {
                            numB += B;
                            if(numB.length() == 5) {
                                break;
                            }
                        }else {
                            break;
                        }
                    }

                    if(Integer.parseInt(numA) > Integer.parseInt(numB)) {
                        return 1;
                    }
                    if(Integer.parseInt(numA) < Integer.parseInt(numB)) {
                        return -1;
                    }
                    if(Integer.parseInt(numA) == Integer.parseInt(numB)) {
                        //숫자가 같다면 원래순서대로 리턴
                        return 0;
                    }

                }
            }
            return 0;

        });

        return files;
    }
}