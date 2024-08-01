import java.util.*;

class Solution {
    
    // 전역 변수 숫자 조합 저장 셋
    static HashSet<Integer> candidates = new HashSet<>();
    
    public int solution(String numbers) {
        int answer = 0;
        
        boolean[] visited = new boolean[numbers.length()];
        
        // 순열 조합으로 만들 수 있는 모든 숫자를 구한다. 
        permutation(numbers, 0, visited, 0);
        
        // 소수 개수 count
        Iterator<Integer> it = candidates.iterator();
        while(it.hasNext()) {
            if(isPrime(it.next())) {
                answer++;
            }
        }

        return answer;
    }
    
    public static void permutation(String numbers, int current, boolean[] visited, int digit){
        
        // 재귀 탈출 조건 : 자릿수가 numbers 길이 만큼 다 차고 +1 됐을 때
        if(digit == numbers.length()) {
            return;    
        }
        
        // numbers의 원소들을 가지고 digit = 0 (1의 자릿수) 부터 하나씩 숫자 조합을 구하면서
        // candidates 셋에 추가하면서 numbers.length()까지 지속한다. 
        // 따라서 이전까지 만든 숫자를 기억해야 함으로 current 변수를 추가해야 한다. 
        // 구현 형태 : 맨 앞자리부터 숫자를 고정하면서 1의 자리부터 numbers 길이까지 채우는 것을 반복하는 것.
        // 즉 맨 앞자리 기준으로 끝까지 한바퀴, 다시 새로운 맨 앞자리를 기준으로 한 바퀴 도는 것!
        for(int i = 0; i < numbers.length(); i++){
            // 방문하지 않은 부분의 숫자만 탐색한다. 
            if(visited[i]) continue;
            
            // 자릿수(digit)에 맞는 숫자를 1개 만든다.
            int newValue = current + (int)((numbers.charAt(i) - '0') * Math.pow(10, digit));
            // 숫자 후보 모음 셋에 추가
            candidates.add(newValue);
            
            // 방문 배열 true로 설정
            visited[i] = true;
            // 자릿수를 하나 늘려서 또 구한다. 
            permutation(numbers, newValue, visited, digit + 1);
            // 재귀로 갔다가 백트래킹하면 다시 원래 상태로 복구
            visited[i] = false;
        }
    }
    
    public static boolean isPrime(int n) {
        if(n < 2) return false;
        
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
}