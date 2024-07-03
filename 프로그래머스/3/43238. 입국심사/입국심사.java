import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = 1;
        long right = (long) times[times.length - 1] * n;
        
        while(left <= right) {
            long mid = left + (right - left) / 2;
            
            long cnt = 0;
            for(int time : times) {
                cnt += mid / time;
            }
            
            if(cnt >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }
}