package com.ssafy.algo230228_DataStructure.soyun.week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main_9935 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] input = br.readLine().toCharArray();
        char[] explode = br.readLine().toCharArray();

        Stack<Character> origin = new Stack<>();
        List<Integer> indexes = new ArrayList<>();  // 121212ababab 처럼 폭발 문자열 속에 폭발 문자열이 있는 경우를 처리하기 위함

        // input 문자열을 차례대로 stack 에 넣음
        int idx = 0;    // 가리키고 있는 input 문자열의 index
        int cnt = 0;    // 가리키고 있는 explode 문자열의 index

        // input 문자열을 모두 읽었다면 loop 빠져나옴
        while(idx < input.length){

            // 문자 하나 push
            origin.push(input[idx++]);

            // 만약 폭발 문자열의 해당 인덱스 단어와 같다면
            if (origin.peek() == explode[cnt]){
                // 폭발 문자열의 인덱스는 다음을 가리킴 -> 계속 push 되는 다른 문자도 폭발 문자열에 속함을 검증하기 위해
                cnt++;
                // 폭발 문자열과 완전히 일치한다면
                if (cnt == explode.length){
                    // 그만큼 pop
                    for (int i = 0; i < explode.length; i++) {
                        origin.pop();
                    }
                    // indexes 가 비어있지 않다면 -> 이전에 검증하다 말았던 예비 폭발 문자열들이 있다는 뜻
                    // 계속해서 검증하기 위해 cnt 를 그때의 index 로 갱신해줌
                    if (!indexes.isEmpty()){
                        cnt = indexes.get(indexes.size() - 1);
                        indexes.remove(indexes.size() - 1);
                        continue;
                    }
                    // indexes 가 비어있다면 -> 앞으로의 문자들을 검증함
                    cnt = 0;
                }
                continue;
            }

            // 1212abab 을 예시로 들자면
            // 12를 검증하다 말고 1이 들어옴 -> 폭발 문자열의 시작 문자이기 때문에 폭발될 가능성이 있음
            // 이전의 12는 예비 폭발 문자열이 됨
            if (origin.peek() == explode[0]){
                indexes.add(cnt);
                cnt = 1;
                continue;
            }

            // 그 외의 경우라면 폭발 문자열이 더 이상 만들어질 수 없는 경우임
            // 예비 폭발 문자열들은 더 이상 검증할 필요가 없으므로 clear()
            indexes.clear();
            cnt = 0;
        }

        if (origin.size() == 0){
            System.out.println("FRULA");
            return;
        }

        StringBuilder sb = new StringBuilder();
        while(!origin.isEmpty()){
            sb.append(origin.pop());
        }
        bw.write(sb.reverse().toString());
        bw.flush();
        br.close();
        bw.close();
    }
}