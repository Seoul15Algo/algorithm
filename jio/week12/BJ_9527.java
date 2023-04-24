package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9527 {
    static long A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        //A~B까지의 1의 개수 = B까지의 1의 개수 - (A-1)까지의 1의개수, 누적합 사용
        System.out.println(findOneCount(B) - findOneCount(A-1));
    }

    private static long findOneCount(long num){
        if(num == 0){
            return 0;
        }
        if(num == 1){
            return 1;
        }
        int powerCount = 0; //2가 몇 번 제곱되는 지
        long poweredNum = 1; //2를 제곱한 수
        while(poweredNum * 2 <= num){ //poweredNum =  num 이하의 수들 중 최대 2^powerCount
            powerCount += 1;
            poweredNum *= 2;
        }
        //2^n-1 까지 나오는 1의 개수 : n*2^(n-1)
        //2^n <= num <= 2^n+1 사이의 1의 개수 : 1번 + 2번
        //1. 가장 앞에 있는 1의 개수 : num - 2^n+1
        //2. 가장 앞을 제외하고 등장하는 1의 개수 : findOneCount(num - 2^n)
        return powerCount * poweredNum / 2 + (num - poweredNum + 1) + findOneCount(num - poweredNum);
    }
}
