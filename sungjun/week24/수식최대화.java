package week24;

import java.util.*;

class 수식최대화 {
    static long result = 0;

    public long solution(String expression) {
        // 가능한 경우 총 6가지
        // * -> + -> -
        // * -> - -> +
        // + -> * -> -
        // + -> - -> *
        // - -> * -> +
        // - -> + -> *

        char[][] opSeq = new char[][] {
                {'*', '+', '-'}, {'*', '-', '+'},
                {'+', '*', '-'}, {'+', '-', '*'},
                {'-', '+', '*'}, {'-', '*', '+'}
        };

        for(int i = 0; i < 6; i++) {
            solve(expression, opSeq[i]);
        }

        return result;
    }

    public void solve(String expression, char[] opSeq) {
        String[] nums = expression.split("\\W");

        Queue<Character> opQueue = new LinkedList<>();
        Queue<Long> numQueue = new LinkedList<>();

        for(int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if('0' <= c && c <= '9') continue;
            opQueue.add(c);
        }

        for(String num : nums) {
            numQueue.add(Long.parseLong(num));
        }

        for(int i = 0; i < 3; i++) {
            Queue<Character> opTmp = new LinkedList<>();    // 우선순위가 아닌 연산자를 담아놓을 큐
            Deque<Long> numTmp = new ArrayDeque<>();        // 계산 결과를 반영하여 담아놓을 덱
            numTmp.add(numQueue.poll());

            // 연산자를 다 계산할때까지
            while(!opQueue.isEmpty()) {
                char nowOp = opQueue.poll();
                long nowNum = numQueue.poll();

                // 연산자가 우선순위 연산자와 동일하다면 덱에서 마지막 숫자를 뽑아서 연산하여 다시 집어넣음
                if(nowOp == opSeq[i]) {
                    if(nowOp == '*') {
                        long beforeNum = numTmp.pollLast();
                        numTmp.add(beforeNum*nowNum);
                        continue;
                    }

                    if(nowOp == '+') {
                        long beforeNum = numTmp.pollLast();
                        numTmp.add(beforeNum+nowNum);
                        continue;
                    }

                    if(nowOp == '-') {
                        long beforeNum = numTmp.pollLast();
                        numTmp.add(beforeNum-nowNum);
                        continue;
                    }
                }

                // 연산자가 우선순위 연산자가 아니라면 숫자와 연산자를 그대로 집어넣음
                numTmp.add(nowNum);
                opTmp.add(nowOp);
            }

            // 덱의 크기가 1이라면 모든 연산 수행 완료
            if(numTmp.size() == 1) {
                result = Math.max(result, (long)Math.abs(numTmp.poll()));
                return;
            }

            // 덱의 숫자들을 다시 큐에 옮겨 담아준다
            while(!numTmp.isEmpty()) {
                numQueue.add(numTmp.pollFirst());
            }

            // 연산자들도 다시 큐에 옮겨 담아준다
            while(!opTmp.isEmpty()) {
                opQueue.add(opTmp.poll());
            }
        }
    }
}