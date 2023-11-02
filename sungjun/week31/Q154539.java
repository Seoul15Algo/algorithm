package week31;

import java.util.*;

class Q154539 {
    public int[] solution(int[] numbers) {
        int[] result = new int[numbers.length];
        Stack<int[]> num = new Stack<>();

        num.add(new int[] {0, numbers[0]});

        for(int i = 1; i < numbers.length; i++) {
            while(!num.isEmpty() && num.peek()[1] < numbers[i]) {
                int[] last = num.pop();
                result[last[0]] = numbers[i];
            }

            num.push(new int[] {i, numbers[i]});
        }

        while(!num.isEmpty()) {
            int[] last = num.pop();
            result[last[0]] = -1;
        }

        return result;
    }
}