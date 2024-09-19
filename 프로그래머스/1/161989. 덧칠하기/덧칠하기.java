class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 1;
        // n(페인트 길이)만큼의 배열 생성
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        
        int end = section[0] + m;
        for(int i = 0; i < section.length; i++) {
            if(section[i] >= end) {
                answer++;
                end = section[i] + m;
            }
        }
        return answer;
    }
}