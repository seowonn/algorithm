class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        String converted = "";
        
        int num = 0;
        while(converted.length() < p + m * t) {
            String convertedNum = convertNum(num++, n);
            for(String s : convertedNum.split("")){
                converted += s;
                if(converted.length() >= p + m * t){
                    break;
                }
                if((converted.length() - p) % m == 0){
                    answer += s.toUpperCase();
                } 
            }
        }
        return answer;
    }
    
    public static String convertNum(int num, int n) {
        return Integer.toString(num, n);
    }
}