import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < prices.length; i++) {
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]){
                int num = stack.pop();
                answer[num] = i - num;
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty()){
            int num = stack.pop();
            answer[num] = prices.length - 1 - num;
        }
        return answer;
    }
}