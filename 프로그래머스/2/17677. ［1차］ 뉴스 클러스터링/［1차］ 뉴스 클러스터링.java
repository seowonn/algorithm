import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        
        double total = 0, same = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str1.length(); i++){
            
            sb.append(str1.charAt(i));
            
            if('a' <= str1.charAt(i) && str1.charAt(i) <= 'z'){
                if(sb.length() == 2) {
                    map1.put(sb.toString(), map1.getOrDefault(sb.toString(), 0) + 1);
                    sb.delete(0, 1);  
                }
            } else {
                sb.delete(0, 2);  
            }
        }
        
        sb = new StringBuilder();
        for(int i = 0; i < str2.length(); i++){
            
            sb.append(str2.charAt(i));
            
            if('a' <= str2.charAt(i) && str2.charAt(i) <= 'z'){
                if(sb.length() == 2) {
                    map2.put(sb.toString(), map2.getOrDefault(sb.toString(), 0) + 1);
                    sb.delete(0, 1);  
                }
            } else {
                sb.delete(0, 2);  
            }
        }
        
        for(Map.Entry<String, Integer> entry : map2.entrySet()) {
            if(!map1.containsKey(entry.getKey())) {
                map1.put(entry.getKey(), entry.getValue());
            } else {
                same += Math.min(entry.getValue(), map1.get(entry.getKey()));
                map1.put(entry.getKey(), Math.max(map1.get(entry.getKey()), entry.getValue()));
            }
        }
        
        for(Map.Entry<String, Integer> entry : map1.entrySet()) {
            total += map1.get(entry.getKey());
        }
        
        return total == 0 ? 65536 : (int)(same / total * 65536);
    }
}