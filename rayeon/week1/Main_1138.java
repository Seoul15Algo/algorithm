import java.util.Arrays;
import java.util.Scanner;

public class Main_1138{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] prev = new int[N];
		for (int i = 0; i < N; i++) 
			prev[i] = sc.nextInt();
		
		int[] r = new int[N];
		Arrays.fill(r, N);
		
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			int idx = 0;
			
			for (int j = 0; j < N; j++) {
				if(i==(N-1) && r[j]==N) {
					idx = j;
					break;
				}
				
				if(r[j] > (i+1))
					cnt++;
				
				if(cnt > prev[i]) {
					idx = j;
					break;
				}
			}
			
			r[idx] = (i+1);
		}
		
		for(int num : r)
		    System.out.print(num + " ");
		sc.close();
	}
}