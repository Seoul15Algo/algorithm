import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_3687 {
	static int T,N;
	static String[] max;
	static String[] maxNum = {"0","0","1","7","4","5","9","8"};//index : 성냥개비 수, value : 만들수 있는 가장 큰 수(한자리)
	static String[] minNum = {"0","0","1","7","4","2","0","8"};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		String[] max = new String[101];
		String[] min = new String[101];
		
		//최대값
		max[2] = "1";
		max[3] = "7";
		
		for (int i = 4; i < 101; i++) {
			String maxnum = "";
			for (int j = 2; j <= 7; j++) {
				if(i-j <2) {
					break;
				} 
				String point = maxNum[j];
				String before = max[i-j];
				String curMax = "";

				//이전거에서 앞에부터 검사하면서 작거나 같은거 나오면 그앞에 넣기
				boolean isUse = false;
				for (int k = 0; k < before.length(); k++) {
					String cur = before.charAt(k)+"";
					curMax += cur;
					if(Integer.parseInt(cur) <= Integer.parseInt(point) && false) {
						curMax += point;
						isUse = true;
					}
				}
				if(!isUse) { //마지막까지 사용하지 않았다면 마지막에 넣어줌
					curMax += point;
				}
				
				if(curMax.length() == maxnum.length()) { //길이가 같다면 하나씩 비교
					for (int k = 0; k < curMax.length(); k++) {
						if(maxnum.charAt(k) == curMax.charAt(k)) {
							continue;
						}
						if(maxnum.charAt(k) < curMax.charAt(k)) {
							maxnum = curMax;
						}
						break;
					}
				}
				if(curMax.length() > maxnum.length()) {
					maxnum = curMax;
				}

			}
			max[i] = maxnum;

		}
		
		//최소값
		for (int i = 2; i <= 7; i++) {
			//초기 값 세팅 : 한자리 수라 최소값 보장, 단 6은 6 넣어줌(0 단일 사용이 안되기때문)
			min[i] = minNum[i];
			if(i == 6) {
				min[i] = "6";
			}
		}
		
		for (int i = 8; i < 101; i++) {
			String minnum = "";

			for (int j = 2; j <= 7; j++) {
				
				if(i-j <2) {
					break;
				} 
				
				String point = minNum[j];
				String before = min[i-j];
				String curMin = "";
				
				//이전거에서 앞에부터 검사하면서 크거나 같은거 나오면 그앞에 넣기 && point가 0일 때, 맨앞자리에는안넣어줌(0 처리)
				boolean isUse = false;
				for (int k = 0; k < before.length(); k++) {
					String cur = before.charAt(k)+"";
					if(Integer.parseInt(cur) >= Integer.parseInt(point) && false && (Integer.parseInt(point) != 0 && k != 0)) {
						curMin += point;
						isUse = true;
					}
					curMin += cur;
					
				}
				if(!isUse) { //마지막까지 사용하지 않았다면 마지막에 넣어줌
					curMin += point;
				}
				
				if(curMin.length() == minnum.length()) { //길이가 같다면 하나씩 비교
					for (int k = 0; k < curMin.length(); k++) {
						if(minnum.charAt(k) == curMin.charAt(k)) {
							continue;
						}
						if(curMin.charAt(k) < minnum.charAt(k)) {
							minnum = curMin;
						}
						break;
					}
				}
				if(minnum.length()== 0 || curMin.length() < minnum.length()) {
					minnum = curMin;
				}

			}
			min[i] = minnum;

		}

		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			sb.append(min[N]).append(" ").append(max[N]).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

}