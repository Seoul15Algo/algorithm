import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1943 {
	static int T = 3;
	static int N;
	static Coin[] coins;
	static boolean[] money; //dp배열
	static int sum;
	static class Coin {
		int price;
		int count;
		public Coin(int price, int count) {
			super();
			this.price = price;
			this.count = count;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			sum = 0; //동전 전부의 합
			coins = new Coin[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int price = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				coins[i] = new Coin(price, count);
				sum += price*count;
			}
			
			if(sum%2 == 1) { //홀수일 경우 
				sb.append(0).append("\n");
				continue;
			}
			
			money = new boolean[50001];
			money[0] = true; //0은 무조건 가능
			for (int i = 0; i < N; i++) {
				int p = coins[i].price;
				int c = coins[i].count;
				
				for (int j = sum/2; j >= p; j--) { //끝부터 탐색 (dp배열이 달라지므로)
					if(money[j-p]) {
						for (int k = 0; k < c && j+k*p<=50000; k++) { //c개만큼, dp배열 범위 벗어나지 않는 한에서
							money[j+k*p] = true;
						}
					}
				}

			}
			if(money[sum/2]) {
				sb.append(1).append("\n");
			}else {
				sb.append(0).append("\n");
			}

		}
		System.out.println(sb.toString());

	}

}
 