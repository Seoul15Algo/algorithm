import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_20210 {
	static int N;
	static String[] str;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		str = new String[N]; //n번째 문자열
		
		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}
		
		Arrays.sort(str,
			(a,b) -> {
				//길이 측정
				int sizeA = a.length();
				int sizeB = b.length();
				
				for (int i = 0; i < Math.max(sizeA, sizeB); i++) {
					
					if(i >= Math.min(sizeA, sizeB)) {
						// FO, FOO 일 경우
						return Integer.compare(sizeA, sizeB);
					}
					
					char curA = a.charAt(i);
					char curB = b.charAt(i);
					
					boolean isNumA = isNum(curA);
					boolean isNumB = isNum(curB);
					
					/*숫자 vs 문자일 때*/
					if(isNumA^isNumB) { //XOR 연산 -> 다르면 참을 반환
						
						//무조건 숫자인 쪽이 앞 -> 숫자(48~57), 문자(65~90 or 97~122)
						return Integer.compare(curA, curB);
						
					}

					
					/*숫자 vs 숫자일 때*/
					if(isNumA && isNumB) {

						//문자나올때까지 keepNum에 저장(0제외), 저장 후 비교
						String keepNumA = "";
						String keepNumB = "";
						int countZeroA = 0;
						int countZeroB = 0;
						int startPointA = i;
						int startPointB = i;
						
						//처음에 나오는 0 개수 카운트
						for (int j = i; j < sizeA; j++) {
							char c = a.charAt(j);
							if(isNum(c)) {
								if(c == '0') {
									//0개수 카운트
									countZeroA++;
									startPointA = j+1;
								}else {
									break;								
								}
							}else {
								break;
							}
						}
						
						//초반 0 빼고 문자열 저장
						for (int j = startPointA; j < sizeA; j++) {
							char c = a.charAt(j);
							if(isNum(c)) {
								keepNumA += c;	
							}else {
								break;
							}
						}
						
						//처음에 나오는 0 개수 카운트
						for (int j = i; j < sizeB; j++) {
							char c = b.charAt(j);
							if(isNum(c)) {
								if(c == '0') {
									//0개수 카운트
									countZeroB++;
									startPointB = j+1;
								}else {
									break;								
								}
							}else {
								break;
							}
						}
						
						for (int j = startPointB; j < sizeB; j++) {
							char c = b.charAt(j);
							if(isNum(c)) {
								keepNumB += c;
							}else {
								break;
							}
						}						
						
						
						//앞 0을 다 뺀숫자 -> 길이로 비교, 같으면 하나하나 비교
						if(keepNumA.length() != keepNumB.length()) {
							return Integer.compare(keepNumA.length(), keepNumB.length());
						}
						
						for (int j = 0; j < Math.min(keepNumA.length(), keepNumB.length()); j++) {
							if(keepNumA.charAt(j) == keepNumB.charAt(j)) {
								continue;
							}
							return Integer.compare(keepNumA.charAt(j), keepNumB.charAt(j));
							
						}
						
						//앞0의 개수로 비교
						if(countZeroA!= countZeroB) {
							return Integer.compare(countZeroA, countZeroB);
						}
						
					}
					
					
					/*문자 vs 문자일 때*/
					if(!isNumA && !isNumB) {
						
						if(curA == curB) continue; //같으면 패스
						
						//다른경우, 알파벳을 대문자로 바꿔서 비교
						boolean isUpperA = isUpper(curA);
						boolean isUpperB = isUpper(curB);
						
						if((isUpperA && isUpperB) || (!isUpperA && !isUpperB)) { //둘다 대문자 or 둘다 소문자일 경우
							return Integer.compare(curA, curB);
						}
						
						if(isUpperA && !isUpperB) { //A가 대문자 && B가 소문자일경우
							//B를 대문자로 바꿔서 비교
							if(curA == curB-'a' +'A') {
								return Integer.compare(curA-1,curB-'a' +'A');
								
							}
							return Integer.compare(curA, curB-'a'+'A');
						}
						
						if(!isUpperA && isUpperB) { //A가 소문자 && B가 대문자일경우
							//A를 대문자로 바꿔서 비교
							if(curA-'a' +'A' == curB) {
								return Integer.compare(curA-'a' +'A',curB-1);
							}
							return Integer.compare(curA-'a'+'A', curB);
						}
						
					}

				}
				
				return 1;
				
			}
			
		);
		
		for (int i = 0; i < N; i++) {
			sb.append(str[i]).append("\n");
		}
		System.out.println(sb.toString());

	}
	
	static boolean isUpper(char c) {
		if('A'<= c && c<='Z') return true;
		return false;
	}
	
	static boolean isNum(char c) {
		if('0'<=c && c<='9') return true;
		return false;
	}

}