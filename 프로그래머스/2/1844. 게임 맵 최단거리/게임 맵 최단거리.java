import java.util.*;

class Solution {
    
    static int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int[][] board;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] maps) {
        
        board = maps;
        
        // bfs를 통해 상대 진영까지의 최단 거리를 탐색한다. 
        bfs(0, 0);
        return answer;
    }
    
    private void bfs(int r, int c) {
        
        // ! 배열의 길이는 .length이다. (괄호가 붙지 않는다.)
        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[r][c] = true;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c, 0});
        
        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            
            if(point[0] == board.length - 1 && point[1] == board[0].length - 1) {
                answer = Math.min(answer, point[2] + 1);
                return;
            }
            
            for(int[] dir : dirs) {
                int nextR = point[0] + dir[0];
                int nextC = point[1] + dir[1];
                
                if(isInRange(nextR, nextC)) {
                    if(!visited[nextR][nextC] && board[nextR][nextC] == 1) { // 이미 방문한 위치는 가면 안됨. 최단 거리 만족 X
                        visited[nextR][nextC] = true;
                        queue.offer(new int[]{nextR, nextC, point[2] + 1});
                    }
                }
            }
        }
        answer = -1;
    }
    
    private boolean isInRange(int r, int c) {
        return r >= 0 && r < board.length && c >= 0 && c < board[0].length;
    }
}