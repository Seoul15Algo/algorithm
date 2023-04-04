package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

// 후위 표기식
public class Main_1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        HashMap<Character, Integer> operator = new HashMap<>();
        operator.put('*', 2);
        operator.put('/', 2);
        operator.put('+', 1);
        operator.put('-', 1);
        operator.put('(', 0);
        
        char[] input = br.readLine().toCharArray();

        Stack<Character> ops = new Stack<>();
        for(char now : input) {
            if ((int)now >= 65 && (int)now <= 90) {
                answer.append(now);
                continue;
            }
            
            if (now == '(') {
                ops.add(now);
                continue;
            }
            
            if (now == ')') { 
                // 닫는 괄호의 경우 
            	// 괄호 안에 있는 연산을 먼저 수행해야 하므로 여는 괄호를 만날 때까지 연산자 스택 pop
                while(!ops.isEmpty() && ops.peek() != '(') {
                    answer.append(ops.pop());
                }
                
                ops.pop();
                
                continue;
            }
            
            // 이전 연산자의 우선순위가 현재 연산자의 우선순위보다 같거나 높은 경우
            // 이전 연산을 먼저 수행해야 하므로 현재 연산자의 우선순위가 높아질 때까지 연산자 스택 pop
            while(!ops.isEmpty() && operator.get(ops.peek()) >= operator.get(now)) {
                answer.append(ops.pop());   
            }  
            
            ops.add(now);
        }
        
        // 남은 연산자 출력
        while (!ops.isEmpty()) {
            answer.append(ops.pop());
        }
        
        System.out.println(answer);
        br.close();
    }
}