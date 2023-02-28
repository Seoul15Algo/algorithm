import java.util.*;
import java.io.*;

public class Main_1655{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        int last = Integer.parseInt(br.readLine());

        for(int i = 2; i <= N; i++){
            sb.append(last).append("\n");
            int now = Integer.parseInt(br.readLine());

            if(i % 2 == 0){
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