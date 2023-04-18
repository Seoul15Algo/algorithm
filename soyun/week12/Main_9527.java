package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1의 개수 세기
public class Main_9527 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        // A == B인 경우 그냥 숫자만 세주면 됨
        if (A == B) {
            String binaryNumber = Long.toString(B, 2);
            int sum = 0;
            for (int i = 0; i < binaryNumber.length(); i++) {
                if (binaryNumber.charAt(i) == '1'){
                    sum++;
                }
            }
            System.out.println(sum);
            return;
        }

        // (A - 1)까지의 1의 합
        long resultA = calculateFrequencyOfOne(A - 1);
        //System.out.println("resultA = " + resultA);

        // B까지의 1의 합
        long resultB = calculateFrequencyOfOne(B);
        //System.out.println("resultB = " + resultB);

        // (B까지의 1의 합) - ((A - 1)까지의 1의 합)
        System.out.println(resultB - resultA);
    }

    /*  1의 개수를 센다
        ex) 12 -> 1100
        1) 1000까지의 1의 개수를 센다
        2) 100까지의 1의 개수를 센다
        3) 1)과 2)를 더한다 -> 1 ~ 12까지의 1의 개수
     */
    static long calculateFrequencyOfOne(long number){
        if (number == 0){
            return 0;
        }

        if (number == 1){
            return 1;
        }
        String binaryNumber = Long.toString(number, 2);

        // 이진수로 변환한 number보다 한 자리 수 작은 이진 수 중 가장 큰 값
        // number가 12 -> 1100, boundNumber는 111이 됨
        long boundNumber = (1L << (binaryNumber.length() - 1)) - 1;

        long boundNumberLength = Long.toString(boundNumber, 2).length();

        // 최상위 자리가 1인 개수
        long sum = number - boundNumber; // // ex) number가 12라면 -> (1100 - (1000 - 1)) = 12 - 7 = 5
        // ex) 111까지의 1의 개수는 3 * 2^2임 -> (이진수의 길이) * 2^(이진수의 길이 - 1)
        // 1, 10, 11, 100, 101, 110, 111
        sum += boundNumberLength * (1L << boundNumberLength - 1);

        // 이진수 문자열을 바로 다음 자리부터 순회하면서 1을 만나면 위의 과정 반복, 계산 결과 더함
        for (int i = 1; i < binaryNumber.length(); i++) {
           if (binaryNumber.charAt(i) == '1') {
               String innerBinaryNumber = binaryNumber.substring(i);
               sum += calculateFrequencyOfOne(Long.parseLong(innerBinaryNumber, 2));
               break;
           }
        }
        return sum;
    }
}
