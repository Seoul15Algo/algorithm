package week9;

public class Emoticon {
	final static int[] SALES = {10, 20, 30, 40};
	static int N;
	static int[] result;
	static int[] nums;
	static int[] totalFee;
	
	public int[] solution(int[][] users, int[] emoticons) {
        N = emoticons.length;
        nums = new int[N];		// 이모티콘 할인율 
        result = new int[2];	// 최대값
        totalFee = new int[users.length];	// 사용자 별 구매금액
        
        perm(0, users, emoticons);
                
        return result;
    }
	
	// 이모티콘 할인이 가능한 모든 경우의 수 생성
	private static void perm(int cnt, int[][] users, int[] emoticons) {
		if(cnt == N) {
			totalFee = new int[users.length];
			
			purchase(nums, users, emoticons);
			
			return;
		}
		
		for (int i = 0; i < SALES.length; i++) {
			nums[cnt] = SALES[i];
			perm(cnt+1, users, emoticons);
		}
	}
	
	// 구매 로직
	private static void purchase(int[] nums, int[][] users, int[] emoticons) {
		int emoticonPlus = 0;
		
		for (int i = 0; i < users.length; i++) {
			for (int j = 0; j < emoticons.length; j++) {
				// 이모티콘 할인율이 사용자 희망 할인율 이상일때
				if(users[i][0] <= nums[j]) {
					totalFee[i] += emoticons[j] / 100 * (100-nums[j]);
				}
				
				// 누적 구매금액이 사용자가 정한 금액 이상일때
				if(totalFee[i] >= users[i][1]) {
					totalFee[i] = 0;	// 구매금액 0
					emoticonPlus++;		// 이모티콘 가입자 +1
					break;
				}
			}
		}
		
		// 이모티콘플러스 가입자가 현재 최대치 이상일때
		if(emoticonPlus >= result[0]) {
			int sum = 0;
			
			for (int i = 0; i < totalFee.length; i++) {
				sum += totalFee[i];
			}
			
			// 이모티콘플러스 가입자가 같다면
			if(emoticonPlus == result[0]) {
				// 판매금액 비교하여 갱신
				result[1] = Math.max(result[1], sum);
				return;
			}

			// 이모티콘플러스 가입자가 늘었다면 바로 갱신
			result[0] = emoticonPlus;
			result[1] = sum;
			return;
		}
	}
}
