import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        Arrays.sort(score);
        int idx = 1;
        // 마지막부터 m개씩 묶어 가장 낮은 점수를 계산한다. 
        for(int i = score.length - m; i >= 0; i-=m) {
            answer += score[i] * m;
        }
        
        return answer;
    }
}