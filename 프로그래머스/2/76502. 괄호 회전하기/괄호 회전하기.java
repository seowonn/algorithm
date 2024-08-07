import java.util.*;

class Solution {
    
    // 괄호 짝 세트
    static Map<String, String> map = new HashMap<>();
    
    public int solution(String s) {
        
        map.put(")", "(");
        map.put("]", "[");
        map.put("}", "{");
        
        int answer = 0;
        ArrayDeque<String> deque = new ArrayDeque<>();
        
        // deque에 s 삽입
        for(String str : s.split("")){
            deque.addLast(str);
        }
        
        // deque로 순환하며 문자 생성
        for(int i = 0; i < s.length(); i++) {
            String last = deque.pollLast();
            deque.addFirst(last);
            if(checkIfRight(deque)) {
                answer++;
            }
        }
        return answer;
    }
    
    private boolean checkIfRight(ArrayDeque<String> deque) {
        
        Stack<String> stack = new Stack<>();
        
        for(String str : deque) {
            // 우측 괄호는 스택에 못들어감
            if(map.containsKey(str)) {
                if(stack.isEmpty()) {   // 처음부터 우측괄호가 나온 경우
                    return false;
                }
                if(map.get(str).equals(stack.peek())) { // peek한 애랑 짝이 맞은 경우
                    stack.pop();
                } else {    // peek랑 짝이 안맞는 경우
                    return false;
                }
            } else {    // 좌측 괄호는 무조건 push
                stack.push(str);
            }
        }
        
        return stack.isEmpty();
    }
}