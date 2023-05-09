import java.util.*;
import java.io.*;
public class Main_1918 {
    static int N;
    static String str;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        N = str.length();

        makePostfix(0);

        System.out.println(sb);
    }

    // 재귀 및 스택을 이용하여 구현
    static int makePostfix(int start) {
        Stack<Character> stack = new Stack<>();
        boolean flag = false;

        for(int i = start; i < N; i++) {
            char now = str.charAt(i);

            switch(now) {
                case '+':
                case '-':
                    // 마지막 연산자가 +, - 이므로 이전까지의 연산자를 모두 append
                    while(!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    flag = false;
                    stack.push(now);
                    break;

                case '*':
                case '/':
                    // 마지막에 *, / 가 있는 경우
                    // 우선순위가 같을 경우 앞에 있는 연산자가 우선이므로 pop & append
                    if(flag) {
                        sb.append(stack.pop());
                    }
                    stack.push(now);
                    flag = true;
                    break;

                // 괄호가 시작 될 경우 재귀
                // 괄호가 끝날 때까지 진행
                case '(':
                    i = makePostfix(i + 1);
                    break;
                case ')':
                    while(!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    return i;

                // 숫자인 경우 append
                default:
                    sb.append(now);
            }
        }
        // 마지막 남은 연산자를 append
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return N;
    }
}