import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class Main_1655 {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> minpq = new PriorityQueue<>(); //오름차순
		PriorityQueue<Integer> maxpq = new PriorityQueue<>((a,b)-> b-a); //내림차순
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++) {
			int cur = Integer.parseInt(br.readLine());
			if(i%2 == 1) {
				maxpq.offer(cur);
			}
			if(i%2 == 0) {
				minpq.offer(cur);
			}
			//minpq에 값이 있고, minpq의 최솟값보다 maxpq의 최댓값이 더 크면 바꿔줌
			if(!minpq.isEmpty() && minpq.peek() < maxpq.peek()) {
				maxpq.offer(minpq.poll());
				minpq.offer(maxpq.poll());
			}
			sb.append(maxpq.peek()+"\n");
		}
		System.out.println(sb.toString());

	}

}