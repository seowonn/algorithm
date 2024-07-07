class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        StringBuilder converted = new StringBuilder();
        
        int num = 0;
        while(converted.length() < p + m * t) {
            String convertedNum = Integer.toString(num++, n);
            for(String s : convertedNum.split("")){
                converted.append(s);
                if(converted.length() >= p + m * t){
                    break;
                }
                if((converted.length() - p) % m == 0){
                    answer.append(s.toUpperCase());
                } 
            }
        }
        return answer.toString();
    }
}