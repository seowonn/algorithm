import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        int num = 0;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        
        while(!queue.isEmpty() ) {
            int[] current = queue.poll();
            int index = current[0];
            int sum = current[1];
            
            if(index == numbers.length) {
                if(sum == target) {
                    num++;
                }
            } else {
                queue.add(new int[]{index + 1, sum + numbers[index]});
                queue.add(new int[]{index + 1, sum - numbers[index]});
            }
        }
        
        return num;
    }

}