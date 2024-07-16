import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);    // 가벼운 사람은 2인승일 확률이 높으니까 
        
        int start = 0; // 맨 앞
        int end = people.length-1; // 맨 뒤
        
        while(start <= end){
            // 2명탑승
            if(people[start] + people[end] <= limit){
                start++;
                end--;
                answer++;
            // 가장 무거운사람 한명 탑승
            }else{
                end--;
                answer++;
            }
        }
        return answer;
    }
}