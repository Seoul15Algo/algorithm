package baekjoon.algo03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2812 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String[] inputs = br.readLine().split("");

        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < inputs.length; i++){
            int cur = Integer.parseInt(inputs[i]);

            while(true){

                if (stk.isEmpty() || k <= 0) break;
                else if (stk.peek() < cur) {
                    stk.pop();
                    k--;
                }
                else break;
            }
            stk.push(cur);
        }

        while(k > 0){
            stk.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        System.out.println(sb.reverse());
    }
}