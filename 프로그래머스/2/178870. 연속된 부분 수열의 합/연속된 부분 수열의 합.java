import java.util.*;

class SubSum implements Comparable<SubSum>{
    int startIdx;
    int endIdx;
    int length;
    
    public SubSum(int start, int end) {
        this.startIdx = start;
        this.endIdx = end;
        this.length = end - start + 1;
    }
    
    public int compareTo(SubSum o){
        if(o.length == this.length) {
            if(o.startIdx > this.startIdx) {
                return -1;
            }
            return 1;
        } else if(o.length > this.length) {
            return -1;
        }
        return 1;
    }
}

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        
        int pointer1 = 0, pointer2 = 1;
        int sum = sequence[pointer1];
        
        PriorityQueue<SubSum> pq = new PriorityQueue<>();
        while(pointer1 <= pointer2 && pointer1 >= 0 && pointer2 < sequence.length) {
            if(sum == k) {
                pq.offer(new SubSum(pointer1, pointer2));
                sum += sequence[pointer2++];
            } else if (sum < k) {
                sum += sequence[pointer2++];
            } else if(sum > k) {
                sum -= sequence[pointer1++];
            }
        }
        
        while(pointer1 < sequence.length && sum > k) {
            sum -= sequence[pointer1++];
        }
        
        if(sum == k) {
            pq.offer(new SubSum(pointer1, pointer2));
        }
        
        if(!pq.isEmpty()) {
            SubSum result = pq.poll();
            answer = new int[]{result.startIdx, result.endIdx - 1};
        }
        
        return answer;
    }
}