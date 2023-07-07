package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_20442 {
	static ArrayList<Integer> kLeft;
	static ArrayList<Integer> kRight;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] cs = br.readLine().toCharArray();
		
		kLeft = new ArrayList<>();
		kRight = new ArrayList<>();
		
		result = 0;
		int kCount = 0;
		
		// 각 R 마다 왼쪽에 있는 k의 개수를 저장
		for (int i = 0; i < cs.length; i++) {
			if(cs[i] == 'R') {
				kLeft.add(kCount);
				continue;
			}
			
			kCount++;
		}
		
		kCount = 0;
		
		// 각 R 마다 오른쪽에 있는 k의 개수를 저장
		for (int i = cs.length-1; i >= 0; i--) {
			if(cs[i] == 'R') {
				kRight.add(kCount);
				continue;
			}
			
			kCount++;
		}
		
		int left = 0;
		int right = kRight.size()-1;
		
		// 투포인터를 활용하여 R 두개를 선택한다
		// 선택한 R들 사이에 있는 R의 개수 + 양쪽에서 쌍으로 제거가 가능한 k의 개수만큼이 ㅋㅋ루ㅋㅋ 문자열의 길이
		while(left <= right) {
			result = Math.max(result, right - left + 1 + (Math.min(kLeft.get(left), kRight.get(kRight.size() - right - 1)) * 2));
			
			if(kLeft.get(left) < kRight.get(kRight.size() - right - 1)) {
				left++;
				continue;
			}
			
			right--;
		}
		
		System.out.println(result);
	}
}
