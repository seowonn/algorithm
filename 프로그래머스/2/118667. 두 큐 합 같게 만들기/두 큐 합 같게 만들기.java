import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Long> q1 = new LinkedList<>();
        Queue<Long> q2 = new LinkedList<>();
        
        long queue1Sum = 0, queue2Sum = 0;
        for(int i = 0; i < queue1.length; i++){
            queue1Sum += queue1[i];
            q1.offer((long)queue1[i]);
            
            queue2Sum += queue2[i];
            q2.offer((long)queue2[i]);
        }
        
        if(queue1Sum == queue2Sum) {
            return 0;
        }
        
        while(!q1.isEmpty() && !q2.isEmpty() && answer <= (queue1.length + queue2.length) * 2) {
            if(queue1Sum < queue2Sum) {
                long num = q2.poll();
                q1.offer(num);
                queue2Sum -= num;
                queue1Sum += num;
            } else if(queue1Sum > queue2Sum){
                long num = q1.poll();
                q2.offer(num);
                queue1Sum -= num;
                queue2Sum += num;
            }
            answer++;
            
            if(queue1Sum == queue2Sum){
                return answer;
            }
        }
        return -1;
    }
}