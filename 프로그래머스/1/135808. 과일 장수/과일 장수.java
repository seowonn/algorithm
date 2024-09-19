import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        Arrays.sort(score);
        int idx = 1;
        for(int i = score.length - 1; i >= 0; i--) {
            if(idx % m == 0) {
                answer += score[i] * m;
            }
            idx++;
        }
        
        return answer;
    }
}