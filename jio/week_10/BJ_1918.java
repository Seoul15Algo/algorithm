package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1918 {
    static Map<Character, Integer> priority;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] infix = br.readLine().toCharArray();

        //연산자 우선순위 : '(' < '+, -' < '*, /'
        priority = new HashMap<>();
        priority.put('(', 1);
        priority.put('+', 2);
        priority.put('-', 2);
        priority.put('*', 3);
        priority.put('/', 3);

        System.out.println(infixToPostfix(infix));
    }

    private static String infixToPostfix(char[] infix){
        List<Character> postfix = new ArrayList<>();
        Stack<Character> operators = new Stack<>();
        for (int i = 0; i < infix.length; i++) {
            char cur = infix[i];
            if(cur >= 65 && cur <= 90){ //피연산자인 경우
                postfix.add(cur);
                continue;
            }
            if(cur == '('){
                operators.push(cur);
                continue;
            }
            if(cur == ')'){ // ')'가 나올 때까지 pop한 연산자를 postfix에 추가
                while(true){
                    char operator = operators.pop();
                    if(operator == '('){
                        break;
                    }
                    postfix.add(operator);
                }
                continue;
            }
            while(!operators.isEmpty()){  //+, -, *, / 일 경우 스택 내 우선순위가 자신 보다 같거나 높은 연산자를 pop하고 마지막에 자신을 push
                char top = operators.peek();
                if(priority.get(top) < priority.get(cur)){ //더 낮은 우선순위에 연산자를 만나면 break
                    break;
                }
                postfix.add(operators.pop());
            }
            operators.push(cur);
        }
        while(!operators.isEmpty()){ //위 과정이 모두 끝난 후 operator 스택에 남은 연산자들을 postfix에 추가
            postfix.add(operators.pop());
        }

        StringBuilder sb = new StringBuilder(); //Character List => String
        for(char c : postfix){
            sb.append(c);
        }
        return sb.toString();
    }
}