package week29;

class 땅따먹기 {
    int solution(int[][] land) {
        int answer = 0;
        int[][] dp = new int[land.length][4];

        for(int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }

        for(int i = 1; i < land.length; i++) {
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 4; k++) {
                    if(k == j) continue;
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][k]+land[i][j]);
                }
            }
        }

        for(int i = 0; i < 4; i++) {
            answer = Math.max(answer, dp[land.length-1][i]);
        }

        return answer;
    }
}