import java.util.*;

class Solution {
    
    // 문자 정보 담기 위한 저장소
    static char[][] map;
    
    // 시작점, 끝점, 레버 위치 변수 선언
    static int[] start;
    static int[] end;
    static int[] lever;
    
    // 4방향
    static int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    
    // bfs 재료 1. 방문 여부 저장 배열
    static boolean[][] visited;
    
    public int solution(String[] maps) {
        
        map = new char[maps.length][maps[0].length()];
        
        // 입력 받은 지도 정보 정리 (시작점, 레버점, 끝점 기록)
        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[i].length(); j++) {
                
                map[i][j] = maps[i].charAt(j);
                
                if(map[i][j] == 'S') {  // 시작점 기록
                    start = new int[]{i, j};
                } else if(map[i][j] == 'E') {   // 끝점 기록
                    end = new int[]{i, j};
                } else if(map[i][j] == 'L') {   // 레버점 기록
                    lever = new int[]{i, j};
                }
            }
        }
        
        // 시작점 기준으로 끝점까지의 최단거리를 구하기 위한 BFS 실행
        // 시작점 ~ 레버점까지의 최단 거리 구하기 
        int route1 = bfs(start, lever, 0);
        
        // 시작점 ~ 레버점이 -1이면 뒤엔 계산할 필요 X
        if(route1 == -1) {
            return route1;
        }
        
        // 레버점 ~ 끝점까지의 최단 거리 구하기
        int route2 = bfs(lever, end, 0);
        
        return route2 == -1 ? -1 : route1 + route2;
    }
    
    public static int bfs(int[] start, int[] end, int count) {

        // bfs 호출 시마다 방문 배열 초기화 -> S ~ L, L ~ E 이동시 갔던데 또 갈 수 있기 떄문
        visited = new boolean[map.length][map[0].length];
        
        // bfs 재료 2. queue
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], count});
        visited[start[0]][start[1]] = true;
        
        while(!queue.isEmpty()) {
            int[] pos = queue.poll();
            
            // 추출된 pos가 도착지점(end)와 동일한지 체크
            if(pos[0] == end[0] && pos[1] == end[1]) {
                System.out.println(pos[2]);
                return pos[2];  // 이때까지 누적된 count(pos[2])를 반환
            }
            
            // 4방향 탐색
            for(int[] dir : dirs) {
                int nextR = pos[0] + dir[0];
                int nextC = pos[1] + dir[1];
                
                // 인덱스가 배열 내부에 있는지 검사
                if(isInRange(nextR, nextC)) {
                    
                    // 방문하지 않은 위치 + 방문가능한 곳(S, E, L, O)
                    if(!visited[nextR][nextC] && map[nextR][nextC] != 'X') {
                        visited[nextR][nextC] = true;
                        System.out.println(nextR + " " + nextC + " " + pos[2]);
                        queue.offer(new int[]{nextR, nextC, pos[2] + 1});
                    }
                }
            }
        }
        return -1;
    }
    
    public static boolean isInRange(int r, int c) {
         return r >= 0 && r < map.length && c >= 0 && c < map[0].length; 
    }
}