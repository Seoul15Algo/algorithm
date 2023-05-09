import java.util.*;
import java.io.*;

public class Main_1655{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // max힙에는 중간 값보다 작은 값을 삽입
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // min힙에는 중간 값보다 큰 값을 삽입
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        int last = Integer.parseInt(br.readLine());

        // 두 번째 숫자부터 for문에서 연산
        // for문 내에서 last값은 항상 중앙 값
        for(int i = 2; i <= N; i++){
            // 항상 중앙값이기 때문에 for문을 시작할 때 마다 출력
            sb.append(last).append("\n");
            int now = Integer.parseInt(br.readLine());

            // 짝수번째와 홀수번째를 나눠서 계산
            if(i % 2 == 0){
                // 새로운 숫자와 이전 중앙값을 비교
                // 큰값을 minheap 작은값을 maxheap에 삽입
                // 짝수번째일 때 중앙값은 중앙에서 앞쪽이므로 last = maxheap.peek
                if(now > last){
                    maxHeap.offer(last);
                    minHeap.offer(now);
                    last = maxHeap.peek();
                    continue;
                }
                maxHeap.offer(now);
                minHeap.offer(last);
                last = maxHeap.peek();
                continue;
            }

            // 각각의 힙의 첫번째 숫자와 새로운 숫자를 비교
            // 위치에 맞게 새로운 값을 삽입 후 해당 힙에서 빼낸 숫자가 중앙 값
            if(maxHeap.peek() >= now){
                last = maxHeap.poll();
                maxHeap.offer(now);
                continue;
            }
            if(minHeap.peek() > now){
                last = now;
                continue;
            }
            last = minHeap.poll();
            minHeap.offer(now);
        }
        sb.append(last).append("\n");
        System.out.println(sb);
    }
}