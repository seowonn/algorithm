import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < name.length; i++) {
            map.put(name[i], yearning[i]);
        }
        
        // 시간 복잡도 : 100 * 100 * 100 = 100백만으로 시간초과 X
        // 100번 실행
        for(int j = 0; j < photo.length; j++) {
            // 100번 1명 검사 시  * 100명
            int sum = 0;
            for(int i = 0; i < photo[j].length; i++) {
                if(map.containsKey(photo[j][i])) {
                    sum += map.get(photo[j][i]);
                }
            }
            answer[j] = sum;
        }
        return answer;
    }
}