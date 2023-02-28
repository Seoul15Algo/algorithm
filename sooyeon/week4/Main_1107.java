package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1107 {
	static int N,M;
	static int digit; //자릿수
	static int result;
	static boolean[] button; //버튼 true : 사용가능 , false:고장
	static int minB, maxB; //제일 작은 수 버튼(0제외), 제일 큰 수 버튼
	static int[] choice; //같은 자리수 일 때, 선택한 버튼 배열
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		digit = str.length();
		choice = new int[digit];
		N = Integer.parseInt(str);
		M = Integer.parseInt(br.readLine());
		button = new boolean[10];
		Arrays.fill(button, true);
		if(M!=0) { //M이 0이 아닐 때만 입력받기
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int a = Integer.parseInt(st.nextToken());
				button[a] = false;
			}
		}		
		for (int i = 1; i <10 ; i++) { //제일 작은 버튼(0 제외)
			if(button[i] ==true) {
				minB = i;
				break;
			}
		}
		
		for (int i = 9; i >= 0; i--) { //제일 큰 버튼
			if(button[i]==true) {
				maxB = i;
				break;
			}
		}
		
		//100 ~ ++
		result = Math.abs(N-100);
		
		//digit-1 자리
		digitM();
		
		//digit
		digitE(0);
		
		//digit+1 자리
		digitP();

		System.out.println(result);
	}
	static void digitP() {
		//digit+1 자리 :최대한 작게 만들어서 비교
		if(minB == 0) {//0제외한 제일 작은 수가 0일경우 return
			if(M==10) { //모든숫자 못치는 경우
				int answer = Math.abs(N-100);
				result = Math.min(result, answer);
				return;
			}
			//0만 칠 수 있는 경우
			int answer = 1+ N;
			result = Math.min(result, answer);
			return; 
		} 
		int answer = digit+1;
		int make = 0;
		for (int i = digit; i >= 0; i--) {
			if(i!= digit && button[0]== true) {
				continue;
			}
			make += minB*Math.pow(10, i);
		}
		answer += Math.abs(N-make);
		result = Math.min(result, answer);
	}
	static void digitE(int cnt) {
		//모든 경우의 수 돌리기
		if(cnt == digit) {
			int answer = digit;
			int make = 0;
			for (int i = 0; i < digit ; i++) {
				make += choice[i]*Math.pow(10,digit-1-i);
			}
			answer += Math.abs(N-make);
			result = Math.min(result, answer);
			return;
		}
		for (int i = 0; i < button.length; i++) {
			if(button[i] == false) continue;
			choice[cnt] = i;
			digitE(cnt+1);
			choice[cnt] = 0;
		}
	}

	static void digitM() {
		//digit-1 자리 : 최대한 크게 만들어서 비교
		if(digit-1 == 0) return; //자릿수가0이면 리턴
		int answer = digit-1; //초깃값(digit-1)번 누르고 시작
		int make = 0;
		for (int i = digit-2; i >= 0; i--) {
			make += maxB*Math.pow(10, i);
		}
		answer += Math.abs(N-make);
		result = Math.min(result, answer);
	}

}
