import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        for(String ppl : participant) {
            map.put(ppl, map.getOrDefault(ppl, 0) + 1);
        }
        
        for(String ppl : completion) {
            map.put(ppl, map.get(ppl) - 1);
        }
        
        for(Map.Entry<String, Integer> set : map.entrySet()) {
            if(set.getValue() > 0){
                answer = set.getKey();
                break;
            }
        }
        
        return answer;
    }
}