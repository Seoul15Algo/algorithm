public class 양궁_대회 {
    static boolean flag;
    static int N, max;
    static int[] answer;
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        N = n;
        flag = false;
        max = 0;

        game(info, new int[11], 0, 0, N, 9);

        if(!flag){
            return new int[]{-1};
        }
        return answer;
    }

    static void game(int[] info, int[] count, int total, int apeach, int remain, int depth){
        if(depth < 0){
            if(total > apeach){
                int now = total - apeach;
                if(now > max || (now == max && answer[10] < remain)){
                    flag = true;
                    max = now;
                    for(int i = 0; i < 10; i++){
                        answer[i] = count[i];
                    }
                    answer[10] = remain;
                }
            }
            return;
        }

        if(remain > info[depth]){
            count[depth] = info[depth] + 1;
            game(info, count, total + 10 - depth, apeach, remain - count[depth], depth - 1);
            count[depth] = 0;
        }
        if(info[depth] > 0){
            apeach += 10 - depth;
        }
        game(info, count, total, apeach, remain, depth - 1);
    }
}
