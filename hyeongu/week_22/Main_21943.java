import java.util.*;
import java.io.*;

// 정수와 연산자의 위치가 고정되어있을 때
// 곱셈을 가장 나중에 해야 계산 결과가 최대가 됨
public class Main_21943 {
    static int[] arr;
    static int answer, N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int P = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        // 곱셈의 개수 + 1개만큼 배열 생성
        // 각 배열에 정수를 더한 뒤 마지막에 모든 배열의 값을 곱하게 되면
        // 덧셈을 모두 진행하고 곱셈을 하는 것과 같은 결과를 얻을 수 있음
        // 이때 각 곱셈을 하는 숫자의 순서는 상관이 없으므로 1번째 수를 고정하고 진행
        int[] part = new int[Q + 1];
        part[0] = arr[0];
        dfs(1, part);
        System.out.println(answer);
    }

    // DFS형태로 완탐
    public static void dfs(int depth, int[] part){
        if(depth == N){
            int result = 1;

            for(int now : part){
                result *= now;
            }

            answer = Math.max(answer, result);
            return;
        }

        for(int i = 0; i < part.length; i++){
            part[i] += arr[depth];
            dfs(depth + 1, part);
            part[i] -= arr[depth];
        }
    }
}