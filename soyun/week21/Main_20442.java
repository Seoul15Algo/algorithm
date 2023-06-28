package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// ㅋㅋ루삥봉
public class Main_20442 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        char[] kukuru = new char[input.length()];

        int rCount = 0;
        for (int i = 0; i < input.length(); i++) {
            kukuru[i] = input.charAt(i);
            if (kukuru[i] == 'R'){
                rCount++;
            }
        }

        int[] leftCount = new int[rCount];
        int[] rightCount = new int[rCount];

        // 각각의 R에 대해 왼쪽에 있는 K의 개수 저장
        int idx = 0;
        int kCount = 0;
        for (int i = 0; i < kukuru.length; i++) {
            if (kukuru[i] == 'K') {
                kCount++;
                continue;
            }
            leftCount[idx++] = kCount;
        }

        // 각각의 R에 대해 오른쪽에 있는 K의 개수 저장
        idx = rCount - 1;
        kCount = 0;
        for (int i = kukuru.length - 1; i >= 0; i--) {
            if (kukuru[i] == 'K') {
                kCount++;
                continue;
            }
            rightCount[idx--] = kCount;
        }

        // 투 포인터 -> R 개수의 범위를 줄여가며 최댓값을 찾음
        int sum = 0;
        int left = 0;
        int right = rightCount.length - 1;
        while (left <= right) {

            sum = Math.max(sum,
                    (right - left + 1) +
                            Math.min(leftCount[left], rightCount[right]) * 2
            );

            if (leftCount[left] < rightCount[right]){
                left++;
            } else {
                right--;
            }
        }

        System.out.println(sum);
    }
}
