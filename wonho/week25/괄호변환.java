import java.util.*;

public class 괄호변환 {
    public String solution(String p) {
        String answer = "";
        
        int length = p.length();
        int open = 0;
        int close = 0;
        
        for (int i = 0; i < length; i++) {
            if (p.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            
            if (open == close) {
                Stack<Character> stack = new Stack<>();
                
                boolean valid = true;
                for (int j = 0; j <= i; j++) {
                    if (p.charAt(j) == '(') {
                        stack.push(p.charAt(j));
                        continue;
                    }
                    if (stack.isEmpty()) {
                        valid = false;
                        break;
                    }
                    stack.pop();
                }
                
                if (valid && stack.isEmpty()) {
                    return p.substring(0, i+1) + recur(p.substring(i+1, length));
                }
                String temp = p.substring(1, i);
                System.out.println(temp);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < i-1; j++) {
                    sb.append(temp.charAt(j) == '(' ? ')' : '(');
                }
                return "(" + recur(p.substring(i+1, length)) + ")" + sb.toString();
            }
        }
        return answer;
    }
    
    public String recur(String s) {
        int length = s.length();
        int open = 0;
        int close = 0;

        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            
            if (open == close) {
                Stack<Character> stack = new Stack<>();
                
                boolean valid = true;
                for (int j = 0; j <= i; j++) {
                    if (s.charAt(j) == '(') {
                        stack.push(s.charAt(j));
                        continue;
                    }
                    if (stack.isEmpty()) {
                        valid = false;
                        break;
                    }
                    stack.pop();
                }
                
                if (valid && stack.isEmpty()) {
                    return s.substring(0, i+1) + recur(s.substring(i+1, length));
                }
                String temp = s.substring(1, i);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < i-1; j++) {
                    sb.append(temp.charAt(j) == '(' ? ')' : '(');
                }
                
                return "(" + recur(s.substring(i+1, length)) + ")" + sb.toString();
            }
        }
        return s;
    }
}