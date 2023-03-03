import java.io.*;
import java.util.*;

public class Main_9935 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();

        String str = br.readLine();
        char[] boom = br.readLine().toCharArray();
        int len = boom.length;

        a : for(int i = 0; i < str.length(); i++){
            st.push(str.charAt(i));

            // 현재까지 들어간 문자의 길이가 폭탄보다 짧을경우 폭탄문자열 X
            if(st.size() < len){
                continue;
            }

            // 스택의 마지막 부분이 폭탄문자열인지 확인
            // 아닐경우 continue label 을 이용하여 탈출
            for(int j = 0; j < len; j++){
                if(st.get(st.size() - len + j) != boom[j]){
                    continue a;
                }
            }

            // 탈출하지 않았으면 마지막 부분이 폭탄문자열
            for(int j = 0; j < len; j++){
                st.pop();
            }
        }

        //모든 연산 종료 후
        if(st.isEmpty()){
            System.out.println("FRULA");
            return;
        }

        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        System.out.println(sb.reverse());
    }
}