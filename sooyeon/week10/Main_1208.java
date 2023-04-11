import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1208 {
	static int N,S;
	static int arr[];
	static List<Integer> left;
	static List<Integer> right;
	static long result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//왼쪽 부분집합, 오른쪽 부분집합을 만듬
		left = new ArrayList<>();
		right = new ArrayList<>();
		
		makePowerSet(0,N/2,0,left); //시작인덱스, 끝 인덱스, 합, 리스트
		makePowerSet(N/2,N,0,right);
		
		
		//정렬
		Collections.sort(left);
		Collections.sort(right);
		
		
		//투 포인터
		calc();
		
		if(S == 0) {
			System.out.println(result-1);
			return;
		}
		System.out.println(result);

	}
	static void calc() {
		int pointerL = 0;
		int pointerR = right.size()-1;
		
		while(true) {
			if(pointerL == left.size() || pointerR<0) {
				break;
			}
			
			int leftValue = left.get(pointerL);
			int rightValue = right.get(pointerR);
			
			if(leftValue+rightValue == S) { //두개의 합이 목표 숫자 일 때
				//각각의 부분집합의 값이 얼마나 있는지 확인
				long leftCount = 0;
				while(pointerL < left.size() && left.get(pointerL) == leftValue) {
					leftCount ++;
					pointerL++;
				}
				
				long rightCount = 0;
				while(0 <= pointerR && right.get(pointerR) == rightValue) {
					rightCount ++;
					pointerR--;
				}
				result += leftCount* rightCount;
			}
			
			if(leftValue + rightValue < S) { //목적 값 보다 작으면 오름차순으로 가리키는 리스트를 가리키는 포인터를 ++
				pointerL++;
			}
			if(leftValue + rightValue > S) { //목적 값 보다 크면 내림차순으로 가리키는 리스트를 가리키는 포인터를 --
				pointerR--;
			}
			
			
		}
		
		
	}
	static void makePowerSet(int start, int end, int sum, List<Integer> list) {
		if(start==end) {
			list.add(sum);
			return;
		}
		makePowerSet(start+1,end,sum,list); //선택 안했을 때
		makePowerSet(start+1,end,sum+arr[start],list); //선택했을 때
		
	}

}
