import java.util.*;
import java.io.*;

public class Main_1799 {
    static int N;
    static int[] answer;
    static int[] arr;
    static boolean[] sumVisit, subVisit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        // 각각 검은색과 흰색발판 최댓값
        answer = new int[2];
        arr = new int[N * N];
        // 대각선 visit배열
        sumVisit = new boolean[2 * N - 1];
        subVisit = new boolean[2 * N - 1];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[N * i + j] = Integer.parseInt(st.nextToken());
            }
        }

        // 탐색 했던 부분을 이어서 탐색하기 위해서 i, j 2차원이 아닌 index 1차원을 이용해서 기 탐색된 부분을 이어서 탐색
        // i => index / N
        // j => index % N
        for(int i = 0; i < N * N; i++){
            if(arr[i] == 1){
                // 발판의 색
                int start = ((i / N) + (i % N)) % 2;

                visit(i, true);
                dfs(i, 1, start);
                visit(i, false);
            }
        }
        System.out.println(answer[0] + answer[1]);
    }

    public static void dfs(int index, int bishop, int start){
        answer[start] = Math.max(answer[start], bishop);

        for(int i = index + 1; i < N * N; i++){
            if(arr[i] == 1){
                int quot = i / N;
                int rem = i % N;

                // 시작 발판과 색이 다른경우
                if((quot + rem) % 2 != start){
                    continue;
                }

                if(sumVisit[quot + rem] || subVisit[rem - quot + N - 1]){
                    continue;
                }

                visit(i, true);
                dfs(i, bishop + 1, start);
                visit(i, false);
            }
        }
    }

    // visit 처리
    public static void visit(int index, boolean value){
        int quot = index / N;
        int rem = index % N;

        sumVisit[quot + rem] = value;
        subVisit[rem - quot + N - 1] = value;
    }
}