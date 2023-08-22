import java.util.*;

class Solution {
    static List<String> infix, postfix;
    static Map<String, Integer> priority;
    static long answer;

    public long solution(String expression) {
        answer = 0;

        infix = makeInfix(expression);

        priority = new HashMap<>();

        decidePriority(expression);

        return answer;
    }

    private static void decidePriority(String expression) {
        // 1. 순열을 통해 우선순위를 먼저 결정
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    if(i ==j || i == k || j == k) {
                        continue;
                    }

                    priority.put("+", i);
                    priority.put("-", j);
                    priority.put("*", k);

                    // 2. 중위 표기식을 후위 표기식으로 변환
                    postfix = makePostfix(infix);

                    // 3. 후위 표기식 계산
                    long temp = Long.max(calPostfix(postfix), -1 * calPostfix(postfix));
                    answer = Long.max(answer, temp);
                }
            }
        }

    }

    private static List<String> makeInfix(String expression) { // string to infix
        String[] operands = expression.split("[+*-]");
        List<String> operators = new ArrayList<>();

        for(Character c : expression.toCharArray()) {
            if(c == '+' || c == '*' || c == '-') {
                operators.add(String.valueOf(c));
            }
        }

        List<String> infix = new ArrayList<>();
        infix.add(operands[0]);

        for(int i=0; i<operators.size(); i++) {
            infix.add(operators.get(i));
            infix.add(operands[i + 1]);
        }

        return infix;
    }

    private static List<String> makePostfix(List<String> infix) { // infix to postfix
        List<String> postfix = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        for(String cur : infix) {
            // 1. 연산자 일 경우
            if(cur.equals("+") || cur.equals("*") || cur.equals("-")) {
                while(!operators.isEmpty()) {
                    String top = operators.peek();
                    if(priority.get(top) < priority.get(cur)) {
                        break;
                    }
                    postfix.add(operators.pop());
                }

                operators.push(cur);
                continue;
            }
            // 2. 피연산자일 경우
            postfix.add(cur);
        }

        while(!operators.isEmpty()) {
            postfix.add(operators.pop());
        }

        return postfix;
    }

    private static long calPostfix(List<String> postfix) { // 후위 표기식 계산
        Stack<Long> result = new Stack<>();

        for(String cur : postfix) {
            if(cur.equals("+") || cur.equals("*") || cur.equals("-")) {
                long second = result.pop();
                long first = result.pop();

                switch (cur) {
                    case "+" :
                        result.push(first + second);
                        break;
                    case "*" :
                        result.push(first * second);
                        break;
                    case "-" :
                        result.push(first - second);
                        break;
                }

                continue;
            }

            result.push(Long.parseLong(cur));
        }

        return result.pop();
    }

}