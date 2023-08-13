package week24;

import java.util.*;

public class 수식_최대화 {
    static char[] operations = {'*', '+', '-'};

    static List<Integer> nums;
    static List<Character> ops;

    static long answer;

    static public void perm(int depth, boolean[] visited, char[] order) {
        if (depth == 3) {
            Deque<Long> numQueue = new ArrayDeque<>();
            Deque<Character> opQueue = new ArrayDeque<>();

            for (long num : nums) {
                numQueue.addLast(num);
            }

            for (char op : ops) {
                opQueue.addLast(op);
            }

            for (char op : order) {
                int size = opQueue.size();

                for (int i = 0; i < size; i++) {
                    long leftNum = numQueue.poll();
                    char o = opQueue.poll();

                    if (o == op) {
                        long rightNum = numQueue.poll();
                        long result = 0;

                        if (op == '*') {
                            result = leftNum * rightNum;
                        } else if (op == '+') {
                            result = leftNum + rightNum;
                        } else {
                            result = leftNum - rightNum;
                        }

                        numQueue.addFirst(result);
                    } else {
                        numQueue.addLast(leftNum);
                        opQueue.addLast(o);
                    }
                }

                numQueue.addLast(numQueue.poll());
            }

            answer = Math.max(answer, Math.abs(numQueue.poll()));

            return;
        }

        for (int i = 0; i < 3; i++) {
            if (visited[i]) {
                continue;
            }

            order[depth] = operations[i];
            visited[i] = true;
            perm(depth + 1, visited, order);
            visited[i] = false;
        }
    }

    public long solution(String expression) {
        answer = 0;

        nums = new ArrayList<>();
        ops = new ArrayList<>();

        int i = 0;
        int num = 0;

        while (i < expression.length()) {
            if (!Character.isDigit(expression.charAt(i))) {
                nums.add(num);
                num = 0;
                ops.add(expression.charAt(i));
            } else {
                num = num * 10 + (expression.charAt(i) - '0');
            }

            i++;
        }

        nums.add(num);

        perm(0, new boolean[3], new char[3]);

        return answer;
    }
}