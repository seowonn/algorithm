class Solution {
    
    static int count = 0;
    
    public int solution(int[] numbers, int target) {
        
        DFS(numbers, 0, target, 0);
            
        return count;
    }
    
    public void DFS(int[] numbers, int currentIdx, int target, int sum) {
        // 재귀 탈출 조건
        if(currentIdx > numbers.length - 1) {
            if(sum == target) {
                count++;
            }
            return;
        }
        
        // 덧셈으로 계산한 경우
        DFS(numbers, currentIdx + 1, target, sum + numbers[currentIdx]);
        
        // 뺄셈으로 계산한 경우
        DFS(numbers, currentIdx + 1, target, sum - numbers[currentIdx]);
    }
}