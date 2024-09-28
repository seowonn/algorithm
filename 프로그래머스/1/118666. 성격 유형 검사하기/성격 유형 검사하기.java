import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        StringBuilder answer = new StringBuilder();
        Character[] personalities = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        
        Map<Character, Integer> map = new HashMap<>();
        for(Character personality : personalities) {
            map.put(personality, 0);
        }
        
        for(int i = 0; i < survey.length; i++) {
            Character target = choices[i] < 4 ? survey[i].charAt(0) : survey[i].charAt(1);
            map.put(target, map.get(target) + Math.abs(4 - choices[i]));
        }
        
        for(int i = 0; i <= personalities.length - 2; i += 2) {
            if(map.get(personalities[i]) == map.get(personalities[i + 1])) {
                answer.append((char)(Math.min(personalities[i], personalities[i + 1])));
            } else {
                answer.append(map.get(personalities[i]) > map.get(personalities[i + 1]) ? personalities[i] : personalities[i + 1]);
            }
        }
        
        return answer.toString();
    }
}