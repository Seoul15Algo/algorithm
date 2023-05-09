package com.ssafy.algo230222_BruteForce.soyun.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1107 {

    static final int MAX_BUTTONS = 10;
    static int n;
    static int broken;
    static Set<String> buttons;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        broken = Integer.parseInt(br.readLine());
        buttons = new HashSet<>();
        min = Integer.MAX_VALUE;

        for (int i = 0; i < MAX_BUTTONS; i++){
            buttons.add(String.valueOf(i));
        }
        // 부서진 버튼은 buttons set에서 지움
        if (broken != 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < broken; i++){
                buttons.remove(st.nextToken());
            }
        }

        min = Math.min(min, Math.abs(100 - n));

        // 누를 수 있는 버튼이 있다면 최솟값 탐색 시작
        // 없다면 -> +,- 만으로 목표 채널에 도달해야 함
        if (!buttons.isEmpty()) {
            find("");
        }

        System.out.println(min);
    }

    static void find(String num) {

        for (String btn : buttons) {
            String temp = num + btn;

            min = Math.min(min, Math.abs(n - Integer.parseInt(temp)) + temp.length());

            // 자릿수를 하나 늘리거나 줄일 때 더 차이가 작아지는 경우가 있으므로
            // 최대 6자리까지 모두 탐색
            if (temp.length() < 6) {
                find(temp);
            }
        }
    }
}