class Solution {
    public int solution(String word) {
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};
        // 이 multiplier는 맨 앞자리부터 해당 자리 고정으로 인해 이전에 
        // 몇개의 조합들이 있었는지 그 개수를 미리 저장해 놓은 것이다. 
        int[] multiplier = {781, 156, 31, 6, 1};  // 각 자리에서의 점프 수 (5^4, 5^3, 5^2, 5^1, 5^0)
        int answer = 0;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            for (int j = 0; j < vowels.length; j++) {
                if (c == vowels[j]) {
                    answer += j * multiplier[i] + 1;
                    break;
                }
            }
        }

        return answer;
    }
}