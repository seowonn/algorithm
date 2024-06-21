import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        Arrays.sort(phone_book);
        
        HashSet<String> totalWords = new HashSet<>();
        
        StringBuilder sb = new StringBuilder();
        for(String phone : phone_book) {
            sb.setLength(0);
            for(int i = 0; i < phone.length(); i++) {
                sb.append(phone.charAt(i));
                if(totalWords.contains(sb.toString())) {
                    return false;
                }
            }
            totalWords.add(sb.toString());
        }
        return true;
    }
}