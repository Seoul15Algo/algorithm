package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// BOJ 20210 : 파일 탐색기
public class Main_20210 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] strArr = new String[N];
		
		for (int i = 0; i < N; i++) {
			strArr[i] = br.readLine();
		}
		
		Arrays.sort(strArr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int index1 = 0;
				int index2 = 0;
				
				while (index1 < o1.length() && index2 < o2.length()) { 
					// 둘 다 숫자인 경우
					if (Character.isDigit(o1.charAt(index1)) && Character.isDigit(o2.charAt(index2))) {
						int zeroCount1 = 0;
						int zeroCount2 = 0;
						
						while ((index1 + zeroCount1) < o1.length() && o1.charAt(index1 + zeroCount1) - '0' == 0) { // 숫자인 경우 앞에 붙은 0의 개수 카운트
							zeroCount1++;
						}
						
						while ((index2 + zeroCount2) < o2.length() && o2.charAt(index2 + zeroCount2) - '0' == 0) { // 숫자인 경우 앞에 붙은 0의 개수 카운트
							zeroCount2++;
						}
						
						index1 += zeroCount1;
						index2 += zeroCount2;
						
						if (index1 == o1.length() && index2 == o2.length()) { // 0의 개수를 세다가 두 문자열이 모두 끝난 경우
							return Integer.compare(zeroCount1, zeroCount2); // 0의 개수 비교
						} else if (index1 == o1.length()) { // 0을 세다가 o1 문자열만 끝난 경우
							return -1;
						} else if (index2 == o2.length()) { // 0을 세다가 o2 문자열만 끝난 경우
							return 1;
						}
						
						StringBuilder num1 = new StringBuilder();
						while (index1 < o1.length() && Character.isDigit(o1.charAt(index1))) {
							num1.append(o1.charAt(index1++));
						}
						
						StringBuilder num2 = new StringBuilder();
						while (index2 < o2.length() && Character.isDigit(o2.charAt(index2))) { 
							num2.append(o2.charAt(index2++));
						}
						
						if (num1 != null && num2 != null) {
							if (num1.length() > num2.length()) {
								return 1;
							} else if (num1.length() < num2.length()) {
								return -1;
							}
							
							for (int j = 0; j < num1.length(); j++) {
								if (num1.charAt(j) != num2.charAt(j)) {
									return Integer.compare(num1.charAt(j), num2.charAt(j));
								}
							}
							
							if (zeroCount1 != zeroCount2) {
								return Integer.compare(zeroCount1, zeroCount2);
							}
						}
						
						if (num1 == null && zeroCount1 > 0) {
							return -1;
						}
						
						if (num2 == null && zeroCount2 > 0) {
							return 1;
						}
					}
					
					if (index1 == o1.length()) { 
						return -1;
					} else if (index2 == o2.length()) { 
						return 1;
					}
					
					// 둘 중 하나만 숫자인 경우
					if (Character.isDigit(o1.charAt(index1))) {
						return -1;
					} else if (Character.isDigit(o2.charAt(index2))) {
						return 1;
					}
					
					// 모두 문자인 경우
					if (!Character.isDigit(o1.charAt(index1)) && !Character.isDigit(o2.charAt(index2))) {
						if (o1.charAt(index1) == o2.charAt(index2)) {
							index1++;
							index2++;
							continue;
						}
						
						if (Character.toUpperCase(o1.charAt(index1)) == Character.toUpperCase(o2.charAt(index2))) {
							return Integer.compare(o1.charAt(index1), o2.charAt(index2));
						} else {
							return Integer.compare(Character.toUpperCase(o1.charAt(index1)), Character.toUpperCase(o2.charAt(index2)));
						}
					}
				}
				
				if (index1 == o1.length()) { // o1 문자열이 더 짧은 경우
					return -1;
				} else { // o2 문자열이 더 짧은 경우
					return 1;
				}
			}
		});

		StringBuilder answer = new StringBuilder();
		for (String str : strArr) {
			answer.append(str).append("\n");
		}
		
		System.out.println(answer);
		br.close();
	}
}