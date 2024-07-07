class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        StringBuilder sb;
        
        for(int j = 0; j < arr1.length; j++) {
            sb = new StringBuilder();
            for(int i = n - 1; i >= 0; i--){
                if((arr1[j] / (int)Math.pow(2, i)) > 0 || (arr2[j] / (int)Math.pow(2, i)) > 0){
                    sb.append("#");
                    arr1[j] = arr1[j] % (int)Math.pow(2, i);
                    arr2[j] = arr2[j] % (int)Math.pow(2, i);
                } else {
                    sb.append(" ");
                }
            }
            answer[j] = sb.toString();
        }
        return answer;
    }
}