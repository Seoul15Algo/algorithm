package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split("");

        int n = input.length;
        for (String s : input) {
            if (s.equals("(")) {
                stack.push(s);
                continue;
            }

            if (s.equals("*") || s.equals("/")) {
                while (!stack.isEmpty()) {
                    String pop = stack.pop();
                    if (pop.equals("(") || pop.equals("+") || pop.equals("-")) {
                        stack.push(pop);
                        break;
                    }
                    sb.append(pop);
                }
                stack.push(s);
                continue;
            }

            if (s.equals("+") || s.equals("-")) {
                while (!stack.isEmpty()) {
                    String pop = stack.pop();
                    if (pop.equals("(")) {
                        stack.push(pop);
                        break;
                    }
                    sb.append(pop);
                }
                stack.push(s);
                continue;
            }

            if (s.equals(")")) {
                while (!stack.isEmpty()) {
                    String pop = stack.pop();
                    if (pop.equals("(")) {
                        break;
                    }
                    sb.append(pop);
                }
                continue;
            }

            sb.append(s);
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}
