import java.util.*;

class Solution {
    
    static final int LENGTH = 5;
    
    // 4방향
    static int[][] dirs = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
        
    public int[] solution(String[][] places) {
        
        // 답안 배열
        int[] answer = new int[LENGTH];
        
        // 배열 길이 만큼 반복
        for(int idx = 0; idx < LENGTH; idx++) {
            
            // 규칙 이행 여부
            boolean correct = true;
            
            // 현재 대기실 맵
            String[] place = places[idx];
            
            // 응시자 주변 맨해튼 거리 2까지 탐색해서 규칙 이행 여부를 구한다. 
            outerLoop:
            for(int i = 0; i < LENGTH; i++) {
                for(int j = 0; j < LENGTH; j++) {
                    // 최단 거리 탐색 BFS 이용
                    if(place[i].charAt(j) == 'P') { // 응시자 위치 발견
                        // 응시자 위치 기준 2만 탐색하면 되서 응시자 위치만 넘겨줌.
                        if(!bfs(i, j, place)) {
                            correct = false;
                            break outerLoop;
                        }
                    }
                }
            }
            answer[idx] = correct ? 1 : 0;
        }
        return answer;
    }
    
    public static boolean bfs(int r, int c, String[] map) {
        
        // bfs 재료 1. 방문여부 저장 배열
        boolean[][] visited = new boolean[LENGTH][LENGTH];
        
        // bfs 재료 2. queue
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{r, c, 0});
        visited[r][c] = true;
        
        while(!queue.isEmpty()) {
            int[] pos = queue.poll();
            int currentR = pos[0], currentC = pos[1], dist = pos[2];
            
            // 현재 위치(출발 지점 제외)에 응시자가 존재. 맨해튼 거리가 2이하인 경우 : 규칙 위반
            if(dist> 0 && dist <= 2 && map[currentR].charAt(currentC) == 'P') {
                return false;
            }
            
            // 거리 2까지만 계속 탐색
            if(dist < 2) {
                // 4방향 탐색
                for(int[] dir : dirs) {
                    int nextR = currentR + dir[0];
                    int nextC = currentC + dir[1];

                    // 인덱스 범위 검사
                    if(isInRange(nextR, nextC) && !visited[nextR][nextC]) {
                        if(map[nextR].charAt(nextC) == 'X') {  // 파티션 통과 불가. 탐색 중지
                            continue;
                        }    
                        visited[nextR][nextC] = true;
                        queue.offer(new int[]{nextR, nextC, dist + 1});
                    }
                }
            }
        }
        return true;    // 거리 두기 규칙 준수 
    }
    
    public static boolean isInRange(int r, int c) {
        return r >= 0 && r < LENGTH && c >= 0 && c < LENGTH;
    }
}