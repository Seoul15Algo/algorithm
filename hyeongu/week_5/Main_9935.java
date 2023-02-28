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

            if(st.size() < len){
                continue;
            }

            for(int j = 0; j < len; j++){
                if(st.get(st.size() - len + j) != boom[j]){
                    continue a;
                }
            }

            for(int j = 0; j < len; j++){
                st.pop();
            }
        }

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