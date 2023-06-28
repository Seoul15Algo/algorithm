import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main_20442 {
	
	static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		
		//왼쪽, 오른쪽에 있는 K 개수 구하기
		ArrayList<Integer> lK = new ArrayList<>();
		ArrayList<Integer> rK = new ArrayList<>();
		
		int kCount = 0;
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == 'K') {
				kCount++;
			}else if(str.charAt(i) =='R') {
				lK.add(kCount);
			}
		}
		
		kCount = 0;
		
		for (int i = str.length()-1; i >= 0 ; i--) {
			if(str.charAt(i) == 'K') {
				kCount++;
			}else if(str.charAt(i) =='R') {
				rK.add(kCount);
			}
		}
		
		rK.sort(Comparator.reverseOrder());
		
		
		int left = 0;
		int right = lK.size() -1;
		result = 0;
		
		while(left<=right) {
			//R의 개수 + 가지고 있는 K 중 작은거 x2 
			result = Math.max(result, (right-left+1)+(2*Math.min(lK.get(left),rK.get(right))));
			
			//K의 개수가 더 작은 쪽을 움직임
			if(lK.get(left) < rK.get(right)) {
				left++;
			} else {
				right--;
			}
		}
		
		System.out.println(result);
	}

}
