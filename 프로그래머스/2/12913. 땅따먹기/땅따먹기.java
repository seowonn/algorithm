class Solution {
    int solution(int[][] land) {
        int answer = 0;

        int[][] dp = new int[land.length + 1][land[0].length];
        for(int i = 0; i < land.length; i++) {
            for(int j = 0; j < land[0].length; j++) {
                for(int idx = 0; idx < 4; idx++) {
                    if(j == idx) continue;
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][idx] + land[i][j]);
                }
            }
        }
        
        for(int num : dp[land.length]) {
            answer = Math.max(answer, num);
        }

        return answer;
    }
}