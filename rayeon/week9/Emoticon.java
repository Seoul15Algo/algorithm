package week9;

import java.util.*;

public class Emoticon {
	static int emoticonSize;
	static int userSize;
	
    static int[][] sUsers;
    static int[] sEmoticons;
    
    static PriorityQueue<Result> results;
    
    static class Result implements Comparable<Result> {
        int totalCost; 
        int count;
        
        public Result(int totalCost, int count) {
            this.totalCost = totalCost;
            this.count = count;
        }
        
        public int compareTo(Result o) {
            if (this.count == o.count) {
                return -Integer.compare(this.totalCost, o.totalCost);
            }
            
            return -Integer.compare(this.count, o.count);
        }
    }
    
    // 할인율을 적용한 결과 계산
    static void calc(int[] discountRates) {
        double[] userCosts = new double[userSize]; // 사용자별 이모티콘 구매 비용
        int count = 0; // 이모티콘 플러스 서비스 가입자 수
        double totalCost = 0; // 이모티콘 판매액 

        for (int i = 0; i < emoticonSize; i++) {                
            double emoticonPrice = sEmoticons[i] * ((double)(100 - discountRates[i]) / 100);

            for (int j = 0; j < userSize; j++) {
                if (discountRates[i] >= sUsers[j][0]) { // 이모티콘 할인율이 사용자의 기준보다 높은 경우
                	userCosts[j] += emoticonPrice;
                }
            }
        }

        for (int j = 0; j < userSize; j++) {
            if (userCosts[j] >= sUsers[j][1]) { // 사용자의 이모티콘 구매 비용의 합이 사용자별 최대 금액 이상인 경우
            	userCosts[j] = 10300;
            	count++; // 이모티콘 플러스 서비스 가입

                continue;
            }

            totalCost += userCosts[j];
        }

        results.offer(new Result((int)totalCost, count));
    }
    
    // 이모티콘별 할인율 선택
    static void perm(int[] discountRates, int idx) {
        if (idx == emoticonSize) {
            calc(discountRates);
            return;
        }
        
        // 가능한 이모티콘 할인율 = [10, 20, 30, 40] 
        for (int discountRate = 10; discountRate <= 40; discountRate += 10) {
        	discountRates[idx] = discountRate;
            perm(discountRates, idx + 1);
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        sUsers = users;
        sEmoticons = emoticons;
        
        userSize = users.length;
        emoticonSize = emoticons.length;
        
        results = new PriorityQueue<>(); // 가능한 결과를 모두 담는 PQ
        
        perm(new int[emoticonSize], 0); // 이모티콘별 할인율 선택

        Result result = results.poll();
        
        int[] answer = new int[2];
        answer[0] = result.count;
        answer[1] = result.totalCost;
        
        return answer;
    }
}