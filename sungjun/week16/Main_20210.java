package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main_20210 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		String[] str = new String[N];
		
		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}
		Arrays.sort(str, new CustomComparator());

		for (int i = 0; i < N; i++) {
			sb.append(str[i]).append("\n");
		}

		System.out.println(sb);
	}

	static class CustomComparator implements Comparator<String> {
		// 문자와 숫자로 문자열 분리
		private ArrayList<String> splitString(String s) {
			ArrayList<String> subString = new ArrayList<>();
			StringBuilder tmp = new StringBuilder();
			
			boolean isNum = false;
			boolean isChar = false;
			
			if(Character.isLetter(s.charAt(0))) isChar = true;
			if(Character.isDigit(s.charAt(0))) isNum = true;

			tmp.append(s.charAt(0));
			
			for (int j = 1; j < s.length(); j++) {
				char c = s.charAt(j);
				
				if (Character.isLetter(c) && isChar) {
					tmp.append(c);
					continue;
				} 
				if (Character.isLetter(c) && !isChar) {
					subString.add(tmp.toString());
					isChar = true;
					isNum = false;
					tmp = new StringBuilder();
					tmp.append(c);
					continue;
				}
				if (Character.isDigit(c) && isNum) {
					tmp.append(c);
					continue;
				}
				if (Character.isDigit(c) && !isNum) {
					subString.add(tmp.toString());
					isNum = true;
					isChar = false;
					tmp = new StringBuilder();
					tmp.append(c);
					continue;
				}
			}
			
			if(tmp.length() > 0) {
				subString.add(tmp.toString());
			}
						
			return subString;
		}
		
		// 문자열끼리 비교
		private int compareStrings(String a, String b) {
			int len = Math.min(a.length(), b.length());
						
			for (int i = 0; i < len; i++) {
				if(a.charAt(i) == b.charAt(i)) continue;
				if(a.charAt(i) > b.charAt(i)) {
					if(a.charAt(i) >= 'A' && a.charAt(i) <= 'Z') return 1;
					if(b.charAt(i) >= 'A' && b.charAt(i) <= 'Z') {
						if(b.charAt(i)+32 <= a.charAt(i)) return 1;
						return -1;
					}
					return 1;
				}
				if(b.charAt(i) > a.charAt(i)) {
					if(b.charAt(i) >= 'A' && b.charAt(i) <= 'Z') return -1;
					if(a.charAt(i) >= 'A' && a.charAt(i) <= 'Z') {
						if(a.charAt(i)+32 <= b.charAt(i)) return -1;
						return 1;
					}
					return -1;
				}
			}
			
			return a.length() - b.length();
		}
		
		// 숫자끼리 비교
		private int countZeros(String s) {
		    int count = 0;
		    
		    for (int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '0') {
					count++;
					continue;
				}
				break;
			}
		    
		    return count;
		}
		
		private int compareNumber(String a, String b) {
			int zeros1 = countZeros(a);
			int zeros2 = countZeros(b);
			
			String s1 = a.substring(zeros1, a.length());
			String s2 = b.substring(zeros2, b.length());
			
			if(s1.length() > s2.length())
				return 1;
			if(s2.length() > s1.length())
				return -1;
			
			for(int i = 0; i < s1.length(); i++) {
				if(i > s1.length() || i > s2.length()) break;
				if(s1.charAt(i) > s2.charAt(i))
					return 1;
				else if (s1.charAt(i) < s2.charAt(i))
					return -1;
			}
			
			return a.length() - b.length();
		}
		
		@Override
		public int compare(String a, String b) {
			ArrayList<String> arrA = splitString(a);
			ArrayList<String> arrB = splitString(b);
			
			int minLen = Math.min(arrA.size(), arrB.size());
			
			for (int i = 0; i < minLen; i++) {
				if(isNum(arrA.get(i).charAt(0)) && isNum(arrB.get(i).charAt(0))) {
					String numA = arrA.get(i);
					String numB = arrB.get(i);
					
					int cmp = compareNumber(numA, numB);
					
					if(cmp == 0) continue;
					return cmp;
				}
				
				if(!isNum(arrA.get(i).charAt(0)) && !isNum(arrB.get(i).charAt(0))) {
					int cmp = compareStrings(arrA.get(i), arrB.get(i));
					if(cmp == 0) continue;
					return cmp;
				}
				
				if(isNum(arrA.get(i).charAt(0)) && !isNum(arrB.get(i).charAt(0))) {
					return -1;
				}
				
				if(!isNum(arrA.get(i).charAt(0)) && isNum(arrB.get(i).charAt(0))) {
					return 1;
				}
			}
			
			return Integer.compare(arrA.size(), arrB.size());
		}

		private boolean isNum(char c) {
			return c >= '0' && c <= '9';
		}
	}
}