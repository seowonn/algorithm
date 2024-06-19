import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < numbers.length - 1; i++){
            for(int j = i + 1; j < numbers.length; j++){
                int num = numbers[i] + numbers[j];
                if(set.add(num)){
                    queue.offer(num);
                }
            }
        }
        
        int[] answer = new int[set.size()];
        int idx = 0;
        while(!queue.isEmpty()){
            int num = queue.poll();
            answer[idx++] = num;
        }
        return answer;
    }
}