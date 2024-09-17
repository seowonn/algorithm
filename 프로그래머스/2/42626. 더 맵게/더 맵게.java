import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int sco : scoville) {
            pq.offer(sco);
        }
        
        while(pq.size() >= 2 && pq.peek() < K) {
            int num1 = pq.poll();
            int num2 = pq.poll();
            
            if(pq.size() == 0 && num1 + num2 * 2 < K) return -1;
            
            pq.offer(num1 + num2 * 2);
            answer++;
        }
        return answer;
    }
}