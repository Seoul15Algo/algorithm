import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Num {
	int index;
	int data;

	public Num(int index, int data) {
		this.index = index;
		this.data = data;
	}
}

public class Main_2812 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		PriorityQueue<Num> nums = new PriorityQueue<Num>(new Comparator<Num>() {
			@Override
			public int compare(Num o1, Num o2) {
				if(o1.data == o2.data)
					return o1.index - o2.index;
				else
					return -(o1.data - o2.data);
			}
		});
		
		String snum = sc.next();
		for (int i = 0; i < K+1; i++)
			nums.offer(new Num(i, snum.charAt(i)-'0'));

		int[] result = new int[N-K];
		int start = 0;
		int size = 0;
		while(size < (N-K)) {
			Num now = nums.poll();

			if (start <= now.index && now.index < (K + size + 1)) {
				result[size++] = now.data;
				start = now.index + 1;
				
				if (K+size < N)
					nums.offer(new Num(K+size, snum.charAt(K+size)-'0'));
			}
		}
		
		for (int i = 0; i < N-K; i++)
			System.out.print(result[i]);	
		
		sc.close();
	}
}