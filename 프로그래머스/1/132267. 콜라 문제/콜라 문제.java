class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        while(n != 0) {
            if(n < a){
                break;
            }
            answer += n / a * b;
            n = n % a + n / a * b;
        }
        return answer;
    }
}