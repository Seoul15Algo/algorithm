import java.io.*;
import java.util.*;

public class Main_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        List<int[]> al = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[2];
            arr[0] = Integer.parseInt(st.nextToken());
            arr[1] = Integer.parseInt(st.nextToken());

            al.add(arr);
        }
        Collections.sort(al, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] == o2[1] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });

        for(int[] now : al){
            while(!pq.isEmpty()){
                if(pq.peek() < now[1]){
                    break;
                }
                pq.poll();
            }
            pq.add(now[0]);
            answer = Math.max(answer, pq.size());
        }
        System.out.println(answer);
    }
}