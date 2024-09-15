import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        // 토핑의 종류가 더 중요 
        // 일일히 한 번씩 다 잘라봐야 함 => 100만번 해야함.
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        
        for(int i = 0; i < topping.length; i++) {
            if(i == 0) {
                map1.put(topping[i], 1);
            } else {
                map2.putIfAbsent(topping[i], 0);
                map2.put(topping[i], map2.get(topping[i]) + 1);
            }
        }
        
        for(int i = 1; i < topping.length - 1; i++) {
            int target = topping[i];
            if(map1.containsKey(target)) {
                map1.put(target, map1.get(target) + 1);
            } else {
                map1.put(target, 1);
            }
            
            map2.put(target, map2.get(target) - 1);
            if(map2.get(target) == 0) {
                map2.remove(target);
            }
            if(map1.size() == map2.size()) {
                answer++;
            }
        }
        return answer;
    }
}