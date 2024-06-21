import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        HashMap<String, Integer> map1 = extractValidWords(str1);
        HashMap<String, Integer> map2 = extractValidWords(str2);
        
        double total = 0, same = 0; 
        total = map1.values().stream().mapToInt(Integer::intValue).sum();
        
        if(map1.size() == 0 && map2.size() == 0) {
            return 1 * 65536;
        }
               
        for(Map.Entry<String, Integer> entry : map2.entrySet()) {
            if(!map1.containsKey(entry.getKey())) {
                total += entry.getValue();
            } else {
                same += Math.min(entry.getValue(), map1.get(entry.getKey()));
                total = total - map1.get(entry.getKey()) + Math.max(map1.get(entry.getKey()), entry.getValue());
            }
        }
        
        return (int)(same / total * 65536);
    }
    
    public HashMap<String, Integer> extractValidWords(String str) {
        
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < str.length(); i++){

            if('a' <= str.charAt(i) && str.charAt(i) <= 'z'){
                
                sb.append(str.charAt(i));
                
                if(sb.length() == 2) {
                    map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
                    sb.delete(0, 1);  
                }
                
            } else {
                sb.setLength(0);  
            }
        }
        return map;
    }
}