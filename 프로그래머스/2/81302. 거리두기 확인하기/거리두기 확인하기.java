import java.util.*;

class Solution {
    
    static int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int LENGTH = 5;
    
    public int[] solution(String[][] places) {
        
        int[] answer = new int[LENGTH];
        
        for(int room = 0; room < LENGTH; room++) {
            boolean followed = true;
            // 전파 진원지 : 응시자 탐색
            loop1: 
            for(int i = 0; i < LENGTH; i++) {
                for(int j = 0; j < LENGTH; j++) {
                    if(places[room][i].charAt(j) == 'P'){ // 응시자 발견
                        if(!checkIfFollowed(i, j, places[room])){
                            followed = false;
                            break loop1;
                        }
                    }
                }
            }
            answer[room] = followed ? 1 : 0;
        }
        return answer;
    }
    
    public static boolean checkIfFollowed(int r, int c, String[] place) {
        
        boolean[][] visited = new boolean[place.length][place.length];
        visited[r][c] = true;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c, 0});
        
        while(!queue.isEmpty()) {
            
            int[] pos = queue.poll();
            
            // 다른 응시자가 있는 경우 : false
            if(pos[2] > 0 && pos[2] <= 2 && place[pos[0]].charAt(pos[1]) == 'P') {
                return false;
            }
            
            // 빈 테이블만 있는 경우 
            if(pos[2] >= 2) {
                continue;
            }
            
            for(int[] dir : dirs) {
                int nextR = pos[0] + dir[0];
                int nextC = pos[1] + dir[1];
                
                if(isInRange(nextR, nextC) && !visited[nextR][nextC]) {
                    
                    // 파티션이 있는 경우 : true
                    if(place[nextR].charAt(nextC) == 'X') {
                        continue;
                    }
                    
                    visited[nextR][nextC] = true;
                    queue.offer(new int[]{nextR, nextC, pos[2] + 1});
                }
            }
        }
        return true;
    }
    
    public static boolean isInRange(int r, int c) {
        return r >= 0 && r < LENGTH && c >= 0 && c < LENGTH;
    }
}