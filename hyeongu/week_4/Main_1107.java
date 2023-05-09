import java.util.*;
import java.io.*;

public class Main_1107 {
    static boolean[] button;
    static int N, M, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        answer = Math.abs(N - 100);

        if(M == 0){
            int tmp = 1;
            if(N > 0){
                tmp = (int)Math.log10(N) + 1;
            }
            System.out.println(Math.min(tmp, answer));
            return;
        }
        if(M == 10){
            System.out.println(answer);
            return;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        button = new boolean[10];
        for(int i = 0; i < M; i++){
            button[Integer.parseInt(st.nextToken())] = true;
        }

        int move = 0;
        int left = N;
        int right = N;

        while(answer > move){
            if(left >= 0){
                checkNum(left, move);
            }
            checkNum(right, move);

            left--;
            right++;
            move++;

            if(left == 100 || right == 100){
                answer = move;
                break;
            }
        }

        System.out.println(answer);
    }

    static void checkNum(int num, int move){
        int cnt = 0;
        do{
            if(button[num % 10]){
                return;
            }
            num /= 10;
            cnt++;
        }while(num > 0);
        answer = Math.min(answer, move + cnt);
    }
}
