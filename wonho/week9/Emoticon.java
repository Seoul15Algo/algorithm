package week9;

import java.util.PriorityQueue;
import java.util.Queue;

public class Emoticon {
    private int emoLength;
    private int[] emoticons;
    private int[] sales;
    private int[][] users;
    private Queue<int[]> pq;
    
    public int[] solution(int[][] users, int[] emoticons) {
        emoLength = emoticons.length;
        sales = new int[emoLength];
        this.emoticons = emoticons;
        this.users = users;
        pq = new PriorityQueue<>((v1, v2) -> {
            if (v1[0] == v2[0]) {
                return v2[1] - v1[1];
            }
            return v2[0] - v1[0];
        });
        
        search(0);
        return pq.poll();
    }
    
    public void search(int count) {
        if (count == emoLength) {
            checkUsers();
            return;
        }
        
        for (int i = 1; i <= 4; i++) {
            sales[count] = i;
            search(count + 1);
        }
    }
    
    public void checkUsers() {
        int plusMember = 0;
        int totalPurchase = 0;
        for (int[] user : users) {
            int percent = user[0];
            int cut = user[1];
            
            int total = 0;
            for (int i = 0; i < emoLength; i++) {
                if (sales[i] * 10 >= percent) {
                    total += emoticons[i] * ((10 - sales[i]) / 10.0);
                }
            }
            if (total >= cut) {
                plusMember++;
                continue;
            }
            totalPurchase += total;
        }
        
        pq.offer(new int[]{plusMember, totalPurchase});
    }
}
