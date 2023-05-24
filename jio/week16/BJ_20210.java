package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_20210 {
    static int N;
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words, (firstString, secondString) -> {
            int firstLength = firstString.length();
            int secondLength = secondString.length();
            int maxLength = Integer.max(firstLength, secondLength);  // 두 문자열 중 더 큰 길이
            int minLength = Integer.min(firstLength, secondLength);  // 두 문자열 중 더 작은 길이

            int idx = 0;
            while(idx < maxLength){
                if (idx == minLength) {  // 계속 비교하다가 둘 중 길이가 더 짧은 문자열을 모두 비교 했을 경우
                    return firstLength - secondLength;
                }

                char first = firstString.charAt(idx);  // 첫 글자
                char second = secondString.charAt(idx);

                boolean firstCharIsNum = isNum(first); // 첫 글자가 숫자인짐 여부
                boolean secondCharIsNum = isNum(second);

                if (firstCharIsNum && !secondCharIsNum) {  // 첫 번째 글자만 숫자인 경우
                    return -1;
                }

                if (!firstCharIsNum && secondCharIsNum) {  // 두 번째 글자만 숫자인 경우
                    return 1;
                }

                if (!firstCharIsNum && !secondCharIsNum) { // 둘 다 문자인 경우

                    if(first == second){ // 두 문자가 같을 경우
                        idx++; // 다음 글자 확인
                        continue;
                    }

                    boolean firstIsUpper = isUpper(first);  // 첫 글자가 대문자인지 여부
                    boolean secondIsUpper = isUpper(second);

                    if (firstIsUpper && secondIsUpper) { // 둘 다 대문자인 경우
                        return Character.compare(first, second);
                    }

                    if (!firstIsUpper && !secondIsUpper) { // 둘 다 소문자인 경우
                        return Character.compare(first, second);
                    }

                    if (firstIsUpper && !secondIsUpper) { // 두 번째 글자만 소문자인 경우
                        second -= 32;
                        if (first == second) { // F, f였던 경우
                            return -1;
                        }
                        return Character.compare(first, second);
                    }

                    if (!firstIsUpper && secondIsUpper) { // 첫 번째 글자만 소문자인 경우
                        first -= 32;
                        if (first == second) { // f, F였던 경우
                            return 1;
                        }
                        return Character.compare(first, second);
                    }

                }

                if (firstCharIsNum && secondCharIsNum) { // 둘 다 숫자인 경우
                    String firstNums = findNums(idx, firstLength, firstString);    // 숫자 덩어리
                    String secondNums = findNums(idx, secondLength, secondString);

                    if (firstNums.equals(secondNums)) { // 두 숫자가 leading zero 부분까지 완전히 동일한 경우
                        idx += firstNums.length();
                        continue;
                    }

                    String removedFirstNums = firstNums.replaceAll("^0+", "");  // leading zero 제거
                    String removedSecondNums = secondNums.replaceAll("^0+", "");

                    if (removedFirstNums.length() == 0 && removedSecondNums.length() != 0) { // 첫 번째 숫자 덩어리만 0으로만 구성된 경우
                        return -1;
                    }

                    if (removedFirstNums.length() != 0 && removedSecondNums.length() == 0) { // 두 번째 숫자 덩어리만 0으로만 구성된 경우
                        return 1;
                    }

                    if (removedFirstNums.length() == 0 && removedSecondNums.length() == 0) { // 두 숫자 덩어리 모두 0으로만 구성된 경우
                        return Integer.compare(firstNums.length(), secondNums.length());
                    }

                    /*
                        leading zero를 제거한 후에는 길이가 길면 무조건 값이 더 크다
                     */
                    if (removedFirstNums.length() > removedSecondNums.length()) { // 첫 번째 숫자가 더 큰 경우
                        return 1;
                    }

                    if (removedFirstNums.length() < removedSecondNums.length()) { // 두 번째 숫자가 더 큰 경우
                        return -1;
                    }

                    for (int i = 0; i < removedFirstNums.length(); i++) { // 두 숫자의 길이가 같은 경우
                        char firstNum = removedFirstNums.charAt(i);
                        char secondNum = removedSecondNums.charAt(i);

                        if (firstNum == secondNum) { // 두 숫자가 같을 경우
                            continue;
                        }
                        return Character.compare(firstNum, secondNum); // 두 숫자가 다를 경우
                    }

                    // leading zero를 제거한 두 숫자의 길이가 같은 경우 leading zero를 제거하기 전 두 숫자의 길이(leading zeor 개수)를 통해 우선순위 결정
                    return Integer.compare(firstNums.length(), secondNums.length());
                }
            }
            return 0;
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(words[i]).append("\n");
        }
        System.out.println(sb);

    }

    private static boolean isNum(char c){  // 숫자인지 판단
        if ('0' <= c && c <= '9') {
            return true;
        }
        return false;
    }

    private static boolean isUpper(char c){ // 대문자인지 판단
        if('A' <= c && c <= 'Z'){
            return true;
        }
        return false;
    }

    private static String findNums(int idx, int length, String word) { // idx 위치 부터 시작하는 숫자 덩어리를 return하는 메소드
        StringBuilder sb = new StringBuilder();
        while (idx < length) {
            char cur = word.charAt(idx);
            if (!isNum(cur)) {
                break;
            }
            sb.append(cur);
            idx++;
        }
        return sb.toString();
    }
}