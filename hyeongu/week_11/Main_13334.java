import java.util.*;
import java.io.*;
public class Main_13334 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 반대로 입력받는 경우 스왑
            if(start > end) {
                int tmp = start;
                start = end;
                end = tmp;
            }

            arr[i][0] = start;
            arr[i][1] = end;
        }

        int d = Integer.parseInt(br.readLine());
        int answer = 0;

        // 도착지(큰 값)를 오름차순으로 정렬
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        for(int i = 0; i < N; i++) {
            // 출발지와 도착지의 거리가 d보다 긴 경우
            if(arr[i][1] - arr[i][0] > d) {
                continue;
            }

            // 도착지를 기준으로 정렬한 후 우선순위 큐에 출발지를 삽입
            // 우선순위 큐 맨 앞에는 가장 작은 출발지가 위치
            // 남은 경우에서 가장 작은 도착지와 비교하여 차이가 d 이하라면 큐에 삽입
            // 우선순위 큐의 사이즈가 정답에 포함되는 경우
            while(!pq.isEmpty() && pq.peek() + d < arr[i][1]) {
                pq.poll();
            }

            pq.offer(arr[i][0]);
            answer = Math.max(answer, pq.size());
        }
        System.out.println(answer);
    }
}