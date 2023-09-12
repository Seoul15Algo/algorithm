package week26;

import java.util.*;

class 파일명정렬 {
    static int numberStartIdx;

    static class Word {
        String str;
        int idx;

        public Word(String str, int idx) {
            this.str = str;
            this.idx = idx;
        }
    }

    public String[] solution(String[] files) {
        PriorityQueue<Word> pque = new PriorityQueue<Word>((firstWord, secondWord) -> {
            String first = firstWord.str;
            String second = secondWord.str;

            String firstHead = findHead(first);
            int firstNumber = Integer.parseInt(findNumber(first));

            String secondHead = findHead(second);
            int secondNumber = Integer.parseInt(findNumber(second));

            if(!firstHead.equals(secondHead)) {
                return firstHead.compareTo(secondHead);
            }

            if(firstNumber != secondNumber) {
                return Integer.compare(firstNumber, secondNumber);
            }

            return Integer.compare(firstWord.idx, secondWord.idx);
        });

        for(int i=0; i<files.length; i++) {
            pque.offer(new Word(files[i], i));
        }

        String[] answer = new String[files.length];
        int idx = 0;

        while(!pque.isEmpty()) {
            answer[idx++] = pque.poll().str;
        }

        return answer;
    }

    private static String findHead(String str) {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<str.length(); i++) {
            Character cur = str.charAt(i);

            if(isNum(cur)) {
                numberStartIdx = i;
                break;
            }

            sb.append(Character.toUpperCase(cur));
        }

        return sb.toString();
    }

    private static String findNumber(String str) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        for(int i=numberStartIdx; i<str.length(); i++) {
            Character cur = str.charAt(i);

            if(!isNum(cur) || cnt == 5) { // 숫자가 아니거나 5개 초과일 경우
                break;
            }

            sb.append(cur);
            cnt++;
        }

        return sb.toString();
    }

    private static boolean isNum(Character c) {
        return c >= '0' && c <= '9';
    }

}