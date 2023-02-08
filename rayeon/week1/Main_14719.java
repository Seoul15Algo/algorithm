import java.util.Scanner;

public class Main_14719 {
	static int H;
	static int W;
	static int[][] g;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		H = sc.nextInt();
		W = sc.nextInt();
		g = new int[H][W];
		for (int col = 0; col < W; col++) {
			int h = sc.nextInt();
			
			for(int row = 0; row < h; row++)
				g[H-1-row][col] = 1;
		}
		
		int result = 0;
		for(int row = H-1; row >= 0; row--) {
			boolean ch = false;
			int cnt = 0;
			
			for(int col = 0; col < W; col++) {
				if(!ch && g[row][col]==1) {
					ch = true;
					continue;
				}
				
				if(ch && g[row][col]==0)
					cnt++;
				
				if(ch && g[row][col]==1) {
					result += cnt;
					cnt = 0;
				}
			}
		}
		
		System.out.println(result);
		
		sc.close();
	}

}