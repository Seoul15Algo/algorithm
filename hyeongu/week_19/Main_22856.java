import java.util.*;
import java.io.*;

public class Main_22856 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];             // 오른쪽 자식노드를 저장하는 배열
        int answer = 0;

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            // 현재 노드에서 자식노드가 있을 경우 각각 경로에 2번씩 추가됨
            if(left > 0){
                answer+=2;
            }
            if(right > 0){
                // 오른쪽 자식노드를 가지고 있을 경우 해당 경로를 저장
                arr[start] = right;
                answer+=2;
            }
        }
        // 유사 중위순회가 끝나는 지점은 시작노드가 아니라
        // 시작노드부터 오른쪽 방향으로 이동하여 마지막 리프노드의 부모노드 이므로
        // 시작노드부터 오른쪽 자식으로 이동하면서 마지막으로 오른쪽 자식을 가지고 있는 노드가 된다
        // 따라서 시작노드부터 오른쪽 자식노드가 없을 때 까지 이동하면서 answer--
        int now = 1;
        while(arr[now] > 0){
            now = arr[now];
            answer--;
        }
        System.out.println(answer);
    }
}
