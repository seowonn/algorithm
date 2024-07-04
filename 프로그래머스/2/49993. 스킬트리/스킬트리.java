import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        Set<Character> skillSet = new HashSet<>();
        for(Character str : skill.toCharArray()){
            skillSet.add(str);
        }
        
        for(String skill_tree : skill_trees) {
            int idx = 0;
            boolean possible = true;
            for(char c : skill_tree.toCharArray()) {
                if(idx == skill.length()) {
                    break;
                }
                if(skillSet.contains(c) && (c != skill.charAt(idx))) {
                    possible = false;
                    break;
                }
                if(c == skill.charAt(idx)) {
                    idx++;
                }
            }
            
            if(possible) {
                answer++;
            }
        }
        return answer;
    }
}