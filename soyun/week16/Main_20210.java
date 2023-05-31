package baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_20210 {
    static int N;
    static String[] str;
    static PriorityQueue<String> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        str = new String[N];
        pq = new PriorityQueue<>((origin, target) -> {
            int originLen = origin.length();
            int targetLen = target.length();
            int originIdx = 0;
            int targetIdx = 0;

            // 문자열 길이를 초과하지 않는 범위 내에서 비교
            while(originIdx < originLen && targetIdx < targetLen){

                char originChar = origin.charAt(originIdx);
                char targetChar = target.charAt(targetIdx);

                // 둘 다 숫자인 경우
                if (Character.isDigit(originChar) && Character.isDigit(targetChar)) {

                    // 0의 개수를 먼저 센다
                    int originZeros = 0;
                    int targetZeros = 0;
                    while (originIdx < originLen) {
                        if (origin.charAt(originIdx) != '0'){
                            break;
                        }
                        originZeros++;
                        originIdx++;
                    }
                    while (targetIdx < targetLen) {
                        if (target.charAt(targetIdx) != '0'){
                            break;
                        }
                        targetZeros++;
                        targetIdx++;
                    }

                    // 0을 제외한 숫자
                    StringBuilder sb1 = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();
                    while (originIdx < originLen) {
                        if (!Character.isDigit(origin.charAt(originIdx))){
                            break;
                        }
                        sb1.append(origin.charAt(originIdx));
                        originIdx++;
                    }
                    while (targetIdx < targetLen) {
                        if (!Character.isDigit(target.charAt(targetIdx))){
                            break;
                        }
                        sb2.append(target.charAt(targetIdx));
                        targetIdx++;
                    }

                    // 0을 제외한 숫자열의 길이가 더 짧은 것이 높은 우선순위
                    if (sb1.length() > sb2.length())
                        return 1;
                    if (sb1.length() < sb2.length())
                        return -1;

                    // 숫자열의 길이가 같을 경우 한 자리씩 비교
                    String originNumber = sb1.toString();
                    String targetNumber = sb2.toString();
                    for (int i = 0; i < originNumber.length(); i++) {
                        if (originNumber.charAt(i) > targetNumber.charAt(i)){
                            return 1;
                        }
                        if (originNumber.charAt(i) < targetNumber.charAt(i)){
                            return -1;
                        }
                    }

                    // 숫자까지 같다면 0의 갯수가 작은순
                    if(originZeros != targetZeros){
                        return Integer.compare(originZeros, targetZeros);
                    }
                }
                // 둘 다 문자인 경우
                else if (Character.isAlphabetic(originChar) && Character.isAlphabetic(targetChar)) {

                    // 같을 경우는 그냥 넘어감
                    if (originChar == targetChar) {
                        originIdx++;
                        targetIdx++;
                        continue;
                    }
                    // true: 대문자 false: 소문자
                    char originCase = Character.toUpperCase(originChar);
                    char targetCase = Character.toUpperCase(targetChar);

                    // origin이 사전순으로 앞
                    if (originCase < targetCase){
                        return -1;
                    }
                    // origin이 사전순으로 뒤
                    if (originCase > targetCase){
                        return 1;
                    }

                    // 같다면 -> 대문자가 더 높은 우선순위
                    return Integer.compare(originChar, targetChar);
                }

                // origin만 문자
                else if (!Character.isDigit(originChar) && Character.isDigit(targetChar)) {
                    return 1;
                }
                // origin만 숫자
                else if (Character.isDigit(originChar) && !Character.isDigit(targetChar)) {
                    return -1;
                }
            }

            return Integer.compare(originLen, targetLen);
        });

        for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
            pq.offer(str[i]);
        }

        while (!pq.isEmpty()){
            result.append(pq.poll()).append("\n");
        }

        System.out.println(result);
    }
}