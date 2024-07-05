import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        char[] cArr = s.toCharArray();
        
        Stack<Character> stack = new Stack<>();
        
        for(char c : cArr){
            if(c == '('){
                stack.push(c);
            } else {
                if(!stack.isEmpty()){
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
}