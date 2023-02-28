import java.util.*;
import java.io.*;

public class Main_9663 {
    static int N, answer;
    static int[] queen;
    static boolean[] col;

    public static void main(String[] args) throws IOException{
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queen = new int[N];
        col = new boolean[N];
        answer = 0;

        dfs(0);

        System.out.println(answer);
    }

    static void dfs(int depth){
        if(depth == N){
            answer++;
            return;
        }

        for(int i = 0; i < N; i++){
            if(col[i]) continue;
            queen[depth] = i;

            if(check(depth)){
                col[i] = true;
                dfs(depth+1);
                col[i] = false;
            }
        }
    }

    static boolean check(int depth){
        for(int i = 0; i < depth; i++){
            if(depth - i == Math.abs(queen[depth] - queen[i])){
                return false;
            }
        }
        return true;
    }
}
