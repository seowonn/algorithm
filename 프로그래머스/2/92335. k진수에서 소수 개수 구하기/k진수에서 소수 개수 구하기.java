import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String convertedNum = Integer.toString(n, k);
        
        int idx = convertedNum.indexOf("0");
        
        if(idx != -1){
            for(String s : convertedNum.split("0")){
                if(!s.equals("") && isPrime(s)) {
                    answer++;
                }
            }
        } else {
            if(isPrime(convertedNum)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public static boolean isPrime(String s) {
        
        double num = Double.parseDouble(s);
        if(num == 1) return false;
        
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}