import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> map = new HashMap<>();
        
        for (String record : records) {
            String[] info = record.split(" ");
            String time = info[0];
            int intTime = 60 * Integer.parseInt(time.substring(0, 2)) + Integer.parseInt(time.substring(3));
            
            map.putIfAbsent(info[1], 0);
            
            if (info[2].equals("IN")) {
                map.put(info[1], map.get(info[1]) - intTime);
            } else {
                map.put(info[1], map.get(info[1]) + intTime);
            }
        }
        
        int[] answer = new int[map.size()];
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int totalTime = entry.getValue();
            if (totalTime <= 0) {
                totalTime += 23 * 60 + 59;
            }
            
            int cost = fees[1];
            if (totalTime > fees[0]) {
                cost += Math.ceil((double)(totalTime - fees[0]) / fees[2]) * fees[3];
            }
            
            pq.offer(new int[]{Integer.parseInt(entry.getKey()), cost});
        }
        
        int i = 0;
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            answer[i++] = arr[1];
        }
        
        return answer;
    }
}
